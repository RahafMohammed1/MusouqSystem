package com.example.musouqsystem.Controller;

import com.example.musouqsystem.Api.ApiResponse;
import com.example.musouqsystem.DTO.CouponsDTO;
import com.example.musouqsystem.DTO.MarketerDTO;
import com.example.musouqsystem.Model.Coupons;
import com.example.musouqsystem.Model.Marketer;
import com.example.musouqsystem.Model.User;
import com.example.musouqsystem.Service.CouponsService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/coupons")
@RequiredArgsConstructor
public class CouponsController {
    private final CouponsService couponsService;

    @GetMapping("/get-all")
    public ResponseEntity MarketerViewHisCoupons(@AuthenticationPrincipal User user)
    {
        return ResponseEntity.status(200).body(couponsService.MarketerViewHisCoupons(user.getId()));
    }

    @PostMapping("/add-general")
    public ResponseEntity marketerAddCouponForAllUsers(@AuthenticationPrincipal User user,@RequestBody @Valid CouponsDTO couponsDTO)
    {
       couponsService.marketerAddCouponForAllUsers(user.getId(),couponsDTO);
        return ResponseEntity.status(200).body(new ApiResponse("the general coupon is added"));
    }

    @PostMapping("/add-special")
    public ResponseEntity marketerAddCouponForSpecialShoppers(@AuthenticationPrincipal User user,@RequestBody @Valid CouponsDTO couponsDTO)
    {
        couponsService.marketerAddCouponForSpecialShoppers(user.getId(), couponsDTO);
        return ResponseEntity.status(200).body(new ApiResponse("the special coupon is added"));
    }

    @PutMapping("/update/{coupon_id}")
    public ResponseEntity marketerUpdateCoupon(@AuthenticationPrincipal User user,@PathVariable Integer coupon_id,@RequestBody @Valid Coupons coupons)
    {
        couponsService.marketerUpdateCoupon(user.getId(), coupon_id, coupons);
        return ResponseEntity.status(200).body(new ApiResponse("the coupon  is updated"));
    }

    @DeleteMapping("/delete/{coupon_id}")
    public ResponseEntity deleteProfile(@AuthenticationPrincipal User user,@PathVariable Integer coupon_id)
    {
        couponsService.marketerDeleteCoupon(user.getId(), coupon_id);
        return ResponseEntity.status(200).body(new ApiResponse("your coupon is deleted"));
    }

    @PutMapping("/marketer-activate-coupon/{coupon_id}")
    public ResponseEntity marketerActivateCoupon(@AuthenticationPrincipal User user,@PathVariable Integer coupon_id)
    {
        couponsService.marketerActivateCoupon(user.getId(), coupon_id);
        return ResponseEntity.status(200).body(new ApiResponse("the coupon  is activated"));
    }
    @PutMapping("/marketer-deactivate-coupon/{coupon_id}")
    public ResponseEntity marketerDeactivateCoupon(@AuthenticationPrincipal User user,@PathVariable Integer coupon_id)
    {
        couponsService.marketerDeactivateCoupon(user.getId(), coupon_id);
        return ResponseEntity.status(200).body(new ApiResponse("the coupon  is deactivated"));
    }
    @PutMapping("/apply-coupon/{order_id}/{code}")
    public ResponseEntity applyCouponOnOrder(@AuthenticationPrincipal User user,@PathVariable Integer order_id,@PathVariable String code)
    {
        couponsService.applyCouponOnOrder(user.getId(), order_id,code);
        return ResponseEntity.status(200).body(new ApiResponse("the coupon  is applied"));
    }

}
