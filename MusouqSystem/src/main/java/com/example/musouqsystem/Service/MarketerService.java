package com.example.musouqsystem.Service;

import com.example.musouqsystem.Api.ApiException;
import com.example.musouqsystem.DTO.MarketerDTO;
import com.example.musouqsystem.Model.*;
import com.example.musouqsystem.Repository.AuthRepository;
import com.example.musouqsystem.Repository.MarketerRepository;
import com.example.musouqsystem.Repository.ProductRepository;
import com.example.musouqsystem.Repository.SupplierRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MarketerService {
    private final MarketerRepository marketerRepository;
    private final AuthRepository authRepository;
    private final ProductRepository productRepository;
    private final SupplierRepository supplierRepository;



    public List<Marketer> shopperGetAllMarketer() {
        return marketerRepository.findAll();
    }

    public List<Marketer> supplierGetAllHisMarketers(Integer supplier_id)
    {
        Supplier supplier = supplierRepository.findSupplierById(supplier_id);
        if (supplier == null)
            throw new ApiException("you should complete your profile first");
        List<Marketer> marketers=marketerRepository.findMarketersBySupplier(supplier);
        if(marketers.isEmpty())
            throw new ApiException("you don't have any marketers yet");
        return marketers;
    }

    public void completeProfile(Integer user_id ,MarketerDTO marketerDTO) {
        User user=authRepository.findUserById(user_id);
        Marketer marketer=marketerRepository.findMarketerById(user_id);
        if (marketer!=null) throw new ApiException("you already complete your profile");
        Marketer marketer1 = new Marketer(null, marketerDTO.getName(), marketerDTO.getPhone(),null,null,null,null,user,null,null,null,null,null,null,null);
        marketerRepository.save(marketer1);
    }

    public void updateProfile(Integer marketer_id, MarketerDTO marketerDTO) {
        Marketer marketer = marketerRepository.findMarketerById(marketer_id);
        if(marketer == null)
            throw new ApiException("you should complete your profile first");

        marketer.setName(marketerDTO.getName());
        marketer.setPhone(marketerDTO.getPhone());
        marketerRepository.save(marketer);

    }
    public void deleteProfile(Integer marketer_id) {
        User user=authRepository.findUserById(marketer_id);
        Marketer marketer = marketerRepository.findMarketerById(marketer_id);

        if(marketer == null){ authRepository.delete(user);}
        else
        {
            if (marketer.getDues()==null)
                marketer.setDues(0.0);
            if (marketer.getDues() != 0.0)
                throw new ApiException("You cannot delete your account because you have unpaid dues");
            marketerRepository.delete(marketer);
            authRepository.delete(user);
        }
    }
    public void assignProductToMarketer(Integer marketer_id, Integer product_id) {
        Marketer marketer = marketerRepository.findMarketerById(marketer_id);
        Product product = productRepository.findProductById(product_id);
        if (marketer == null || product == null) throw new ApiException("cannot assign");

        product.getMarketers().add(marketer);
        marketer.getProducts().add(product);

        productRepository.save(product);
        marketerRepository.save(marketer);
    }
    public void marketerSelectSupplier(Integer marketer_id, Integer supplier_id) {
        Marketer marketer = marketerRepository.findMarketerById(marketer_id);
        if(marketer == null)
            throw new ApiException("you should complete your profile first");
        if (marketer.getSupplier()!=null)
            throw new ApiException("You have already chosen a supplier and your request has been accepted by him. Delete it first if you want to change it");

        Supplier supplier = supplierRepository.findSupplierById(supplier_id);
        if (supplier == null)
            throw new ApiException("please enter correct supplier id");

        marketer.setSupplierSelectedId(supplier.getId());
        marketerRepository.save(marketer);
    }

    public void marketerDeleteSupplier(Integer marketer_id)
    {
        Marketer marketer=marketerRepository.findMarketerById(marketer_id);
        if(marketer == null)
            throw new ApiException("you should complete your profile first");
        if (marketer.getSupplier()==null)
            throw new ApiException("you don't have supplier to deleted");
        if (marketer.getDues()!=0.0)
            throw new ApiException("You cannot delete your supplier because there are duse to you that have not yet been paid");
        marketer.setSupplier(null);
    }

}
