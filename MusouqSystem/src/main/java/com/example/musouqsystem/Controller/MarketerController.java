package com.example.musouqsystem.Controller;

import com.example.musouqsystem.Api.ApiResponse;
import com.example.musouqsystem.DTO.MarketerDTO;
import com.example.musouqsystem.Model.User;
import com.example.musouqsystem.Service.MarketerService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/marketer")
@RequiredArgsConstructor
public class MarketerController {
    private final MarketerService marketerService;

    @GetMapping("/shopper-get-all-marketer")
    public ResponseEntity shopperGetAllMarketer() {
        return ResponseEntity.status(200).body(marketerService.shopperGetAllMarketer());
    }

    @GetMapping("/supplier-get-all-marketer")
    public ResponseEntity supplierGetAllHisMarketers(@AuthenticationPrincipal User user) {
        return ResponseEntity.status(200).body(marketerService.supplierGetAllHisMarketers(user.getId()));
    }


    @PostMapping("/complete-profile")
    public ResponseEntity completeProfile(@AuthenticationPrincipal User user ,@RequestBody@Valid MarketerDTO marketerDTO ) {
        marketerService.completeProfile(user.getId(),marketerDTO);
        return ResponseEntity.status(200).body(new ApiResponse("your profile is completed"));
    }

    @PutMapping("/update-profile")
    public ResponseEntity updateProfile(@AuthenticationPrincipal User user, @RequestBody @Valid MarketerDTO marketerDTO) {
        marketerService.updateProfile(user.getId(),marketerDTO);
        return ResponseEntity.status(200).body(new ApiResponse("your profile is updated"));
    }

    @DeleteMapping("/delete-profile")
    public ResponseEntity deleteProfile(@AuthenticationPrincipal User user) {
        marketerService.deleteProfile(user.getId());
        return ResponseEntity.status(200).body(new ApiResponse("your profile is deleted"));
    }

    @PutMapping("/select/supplier/{supplier_id}")
    public ResponseEntity selectSupplier(@AuthenticationPrincipal User user,@PathVariable Integer supplier_id) {
        marketerService.marketerSelectSupplier(user.getId(), supplier_id);
        return ResponseEntity.status(200).body(new ApiResponse("your selection is done successfully"));
    }

    @PutMapping("/delete-supplier")
    public ResponseEntity MarketerDeleteSupplierToChangeIt(@AuthenticationPrincipal User user) {
    marketerService.marketerDeleteSupplier(user.getId());
        return ResponseEntity.status(200).body(new ApiResponse("your supplier are deleted"));
    }
}
