package com.example.musouqsystem.Service;

import com.example.musouqsystem.Api.ApiException;
import com.example.musouqsystem.DTO.SupplierDTO;
import com.example.musouqsystem.Model.Request;
import com.example.musouqsystem.Model.Supplier;
import com.example.musouqsystem.Repository.SupplierRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SupplierService {
    private final SupplierRepository supplierRepository;


    public List<Supplier> marketerGetAllSuppliers() {
        return supplierRepository.findAll();
    }

    public void completeProfile(Supplier supplier) {
//        Supplier supplier = new Supplier(null, supplierDTO.getName(), supplierDTO.getPhone(),null,null,null,null);
        supplierRepository.save(supplier);
    }

    public void updateProfile(Integer supplier_id, SupplierDTO supplierDTO) {
        Supplier supplier = supplierRepository.findSupplierById(supplier_id);

        if (supplier == null) throw new ApiException("supplier not exist");

        supplier.setName(supplierDTO.getName());
        supplier.setPhone(supplierDTO.getPhone());

        supplierRepository.save(supplier);
    }

    public void deleteAccount(Integer supplier_id) {
        Supplier supplier = supplierRepository.findSupplierById(supplier_id);

        if (supplier == null) throw new ApiException("supplier not exist");

        if (supplier.getMarketers().isEmpty() || supplier.getOrders().isEmpty())
            supplierRepository.delete(supplier);
        else throw new ApiException("you cannot delete your account while there are marketing request & orders");
    }



}
