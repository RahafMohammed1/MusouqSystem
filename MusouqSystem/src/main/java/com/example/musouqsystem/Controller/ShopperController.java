package com.example.musouqsystem.Controller;

import com.example.musouqsystem.Api.ApiResponse;
import com.example.musouqsystem.DTO.SupplierDTO;
import com.example.musouqsystem.Model.Shopper;
import com.example.musouqsystem.Model.Supplier;
import com.example.musouqsystem.Service.ShopperService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/shopper")
@RequiredArgsConstructor
public class ShopperController {
    private final ShopperService shopperService;

    @GetMapping("/get")
    public ResponseEntity getAllShoppers() {
        return ResponseEntity.status(200).body(shopperService.getAllShopper());
    }

    @PostMapping("/completeProfile")
    public ResponseEntity completeProfile(@RequestBody @Valid Shopper shopper){
        shopperService.completeShopperProfile(shopper);
        return ResponseEntity.status(200).body(new ApiResponse("your profile completed"));
    }

    @PutMapping("/updateProfile/{shopper_id}")
    public ResponseEntity updateProfile(@PathVariable Integer shopper_id, @RequestBody @Valid Shopper shopper) {
        shopperService.updateShopperProfile(shopper_id, shopper);
        return ResponseEntity.status(200).body(new ApiResponse("your profile updated successfully"));
    }

    @DeleteMapping("/deleteAccount/{shopper_id}")
    public ResponseEntity deleteAccount(@PathVariable Integer shopper_id) {
        shopperService.deleteShopperAccount(shopper_id);
        return ResponseEntity.status(200).body(new ApiResponse("your account deleted successfully"));
    }
    @PutMapping("/selectMarketer/{shopper_id}/{maarketer_id}")
    public ResponseEntity shopperSelectMarketerController(@PathVariable Integer shopper_id , @PathVariable Integer maarketer_id){
        shopperService.ShopperSelectMarketer(shopper_id, maarketer_id);
        return ResponseEntity.status(200).body(new ApiResponse("the marketer selected successfully"));
    }
}
