package com.example.musouqsystem.Service;

import com.example.musouqsystem.Api.ApiException;
import com.example.musouqsystem.DTO.MarketerDTO;
import com.example.musouqsystem.DTO.MarketerEditProfileDTO;
import com.example.musouqsystem.DTO.SupplierDTO;
import com.example.musouqsystem.Model.*;
import com.example.musouqsystem.Repository.AuthRepository;
import com.example.musouqsystem.Repository.MarketerRepository;
import com.example.musouqsystem.Repository.ProductRepository;
import com.example.musouqsystem.Repository.SupplierRepository;
import jakarta.persistence.criteria.CriteriaBuilder;
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

    public void completeProfile(Marketer marketer) {
        marketerRepository.save(marketer);
    }

    public void updateProfile(Integer marketer_id, MarketerDTO marketerDTO) {
        Marketer marketer = marketerRepository.findMarketerById(marketer_id);
        if (marketer == null) throw new ApiException("marketer not exist");
        marketer.setName(marketerDTO.getName());
        marketer.setPhone(marketerDTO.getPhone());
        marketerRepository.save(marketer);
    }
    public void deleteProfile(Integer marketer_id) {
        Marketer marketer = marketerRepository.findMarketerById(marketer_id);
        if (marketer == null) throw new ApiException("the marketer not found");
        marketerRepository.delete(marketer);
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
        if (marketer == null) throw new ApiException("marketer not found");
        Supplier supplier = supplierRepository.findSupplierById(supplier_id);
        if (supplier == null) throw new ApiException("please enter correct supplier id");

        marketer.setSupplierSelectedId(supplier.getId());
        marketerRepository.save(marketer);
    }

    public void marketerDeleteSupplier(Integer marketer_id)
    {
        Marketer marketer=marketerRepository.findMarketerById(marketer_id);
        if (marketer == null) throw new ApiException("marketer not found");
        if (marketer.getSupplier()==null)
            throw new ApiException("you don't have supplier to deleted");
        if (marketer.getDues()!=0)
            throw new ApiException("You cannot delete your supplier because there are duse to you that have not yet been paid");
        marketer.setSupplier(null);
    }

}
