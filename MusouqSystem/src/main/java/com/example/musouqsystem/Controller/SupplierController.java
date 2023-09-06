package com.example.musouqsystem.Controller;

import com.example.musouqsystem.Api.ApiResponse;
import com.example.musouqsystem.DTO.SupplierDTO;
import com.example.musouqsystem.Model.Supplier;
import com.example.musouqsystem.Service.SupplierService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/supplier")
public class SupplierController {
    private final SupplierService supplierService;

    @GetMapping("/getAllSupplier")
    public ResponseEntity marketerGetAllSuppliers() {
        return ResponseEntity.status(200).body(supplierService.marketerGetAllSuppliers());
    }

    @PostMapping("/completeProfile")
    public ResponseEntity completeProfile(@RequestBody @Valid Supplier supplier){
        supplierService.completeProfile(supplier);
        return ResponseEntity.status(200).body(new ApiResponse("your profile completed"));
    }

    @PutMapping("/updateProfile/{supplier_id}")
    public ResponseEntity updateProfile(@PathVariable Integer supplier_id, @RequestBody @Valid SupplierDTO supplierDTO) {
        supplierService.updateProfile(supplier_id, supplierDTO);
        return ResponseEntity.status(200).body(new ApiResponse("your profile updated successfully"));
    }

    @DeleteMapping("/deleteAccount/{supplier_id}")
    public ResponseEntity deleteAccount(@PathVariable Integer supplier_id) {
        supplierService.deleteAccount(supplier_id);
        return ResponseEntity.status(200).body(new ApiResponse("your account deleted successfully"));
    }
}
