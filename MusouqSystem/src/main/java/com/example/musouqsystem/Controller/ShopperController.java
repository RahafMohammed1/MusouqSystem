package com.example.musouqsystem.Controller;

import com.example.musouqsystem.Api.ApiResponse;
import com.example.musouqsystem.DTO.ShopperDTO;
import com.example.musouqsystem.DTO.SupplierDTO;
import com.example.musouqsystem.Model.Shopper;
import com.example.musouqsystem.Model.Supplier;
import com.example.musouqsystem.Model.User;
import com.example.musouqsystem.Service.ShopperService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/shopper")
@RequiredArgsConstructor
public class ShopperController {
    private final ShopperService shopperService;

    // marketer view all his shopper
    @GetMapping("/marketerGetShoppers")
    public ResponseEntity getAllShoppers(@AuthenticationPrincipal User user) {
        return ResponseEntity.status(200).body(shopperService.getAllShopper(user.getId()));
    }

    @PostMapping("/completeProfile")
    public ResponseEntity completeProfile(@AuthenticationPrincipal User user, @RequestBody @Valid ShopperDTO shopperDTO){
        shopperService.completeShopperProfile(user.getId(),shopperDTO);
        return ResponseEntity.status(200).body(new ApiResponse("your profile completed"));
    }

    @PutMapping("/updateProfile/{shopper_id}")
    public ResponseEntity updateProfile(@AuthenticationPrincipal User user,@PathVariable Integer shopper_id, @RequestBody @Valid ShopperDTO shopperDTO) {
        shopperService.updateShopperProfile(user.getId(), shopper_id, shopperDTO);
        return ResponseEntity.status(200).body(new ApiResponse("your profile updated successfully"));
    }

    @DeleteMapping("/deleteAccount/{shopper_id}")
    public ResponseEntity deleteAccount(@AuthenticationPrincipal User user,@PathVariable Integer shopper_id) {
        shopperService.deleteShopperAccount(user.getId(),shopper_id);
        return ResponseEntity.status(200).body(new ApiResponse("your account deleted successfully"));
    }
    @PutMapping("/selectMarketer/{marketer_id}")
    public ResponseEntity shopperSelectMarketerController(@AuthenticationPrincipal User user, @PathVariable Integer marketer_id){
        shopperService.ShopperSelectMarketer(user.getId(), marketer_id);
        return ResponseEntity.status(200).body(new ApiResponse("the marketer selected successfully"));
    }
}
