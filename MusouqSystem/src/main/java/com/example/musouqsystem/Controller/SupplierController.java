package com.example.musouqsystem.Controller;

import com.example.musouqsystem.Api.ApiResponse;
import com.example.musouqsystem.DTO.SupplierDTO;
import com.example.musouqsystem.Model.Supplier;
import com.example.musouqsystem.Model.User;
import com.example.musouqsystem.Service.SupplierService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/supplier")
public class SupplierController {
    private final SupplierService supplierService;

    @GetMapping("/getAllSuppliers")
    public ResponseEntity marketerGetAllSuppliers() {
        return ResponseEntity.status(200).body(supplierService.marketerGetAllSuppliers());
    }

    @PostMapping("/completeProfile")
    public ResponseEntity completeProfile(@AuthenticationPrincipal User user, @RequestBody @Valid SupplierDTO supplierDTO){
        supplierService.completeProfile(user.getId(), supplierDTO);
        return ResponseEntity.status(200).body(new ApiResponse("your profile completed"));
    }

    @PutMapping("/updateProfile")
    public ResponseEntity updateProfile(@AuthenticationPrincipal User user, @RequestBody @Valid SupplierDTO supplierDTO) {
        supplierService.updateProfile(user.getId(), supplierDTO);
        return ResponseEntity.status(200).body(new ApiResponse("your profile updated successfully"));
    }

    @PutMapping("/shipOrder/{order_id}")
    public ResponseEntity supplierShippedOrder(@AuthenticationPrincipal User user, @PathVariable Integer order_id) {
        supplierService.supplierShippedOrder(user.getId(), order_id);
        return ResponseEntity.status(200).body(new ApiResponse("order shipped successfully"));
    }


    @DeleteMapping("/deleteAccount")
    public ResponseEntity deleteAccount(@AuthenticationPrincipal User user) {
        supplierService.deleteAccount(user.getId());
        return ResponseEntity.status(200).body(new ApiResponse("your account deleted successfully"));
    }
}
