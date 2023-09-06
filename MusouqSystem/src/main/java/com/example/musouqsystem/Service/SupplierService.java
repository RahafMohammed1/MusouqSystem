package com.example.musouqsystem.Service;

import com.example.musouqsystem.Api.ApiException;
import com.example.musouqsystem.DTO.SupplierDTO;
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

    public void completeProfile(SupplierDTO supplierDTO) {
        Supplier supplier = new Supplier(null, supplierDTO.getName(), supplierDTO.getPhone(),null,null,null);
        supplierRepository.save(supplier);
    }

    public void updateProfile(Integer supplier_id, SupplierDTO supplierDTO) {
        Supplier supplier = supplierRepository.findSupplierById(supplier_id);

        if (supplier == null) throw new ApiException("supplier not exist");

        supplier.setName(supplierDTO.getName());
        supplier.setPhone(supplierDTO.getPhone());

        supplierRepository.save(supplier);
    }


    // TODO: 9/6/2023   add conditional  about order status & req status.
    public void deleteAccount(Integer supplier_id) {
        Supplier supplier = supplierRepository.findSupplierById(supplier_id);

        if (supplier == null) throw new ApiException("supplier not exist");

        supplierRepository.delete(supplier);
    }



}
