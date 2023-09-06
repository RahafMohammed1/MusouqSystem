package com.example.musouqsystem.Controller;

import com.example.musouqsystem.Api.ApiResponse;
import com.example.musouqsystem.DTO.CouponsDTO;
import com.example.musouqsystem.DTO.MarketerDTO;
import com.example.musouqsystem.Model.Marketer;
import com.example.musouqsystem.Service.CouponsService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/coupons")
@RequiredArgsConstructor
public class CouponsController {
    private final CouponsService couponsService;

    @GetMapping("/get-all/{marketer_id}")
    public ResponseEntity MarketerViewHisCoupons(@PathVariable Integer marketer_id)
    {
        return ResponseEntity.status(200).body(couponsService.MarketerViewHisCoupons(marketer_id));
    }

    @PostMapping("/add/{marketer_id}")
    public ResponseEntity marketerAddCouponForAllUsers(@PathVariable Integer marketer_id,@RequestBody @Valid CouponsDTO couponsDTO)
    {
       couponsService.marketerAddCouponForAllUsers(marketer_id, couponsDTO);
        return ResponseEntity.status(200).body(new ApiResponse("the coupon is added"));
    }

    @PutMapping("/update/{marketer_id}/{coupon_id}")
    public ResponseEntity marketerUpdateCoupon(@PathVariable Integer marketer_id,@PathVariable Integer coupon_id,@RequestBody @Valid CouponsDTO couponsDTO)
    {
        couponsService.marketerUpdateCoupon(marketer_id, coupon_id, couponsDTO);
        return ResponseEntity.status(200).body(new ApiResponse("the coupon  is updated"));
    }

    @DeleteMapping("/delete/{marketer_id}/{coupon_id}")
    public ResponseEntity deleteProfile(@PathVariable Integer marketer_id,@PathVariable Integer coupon_id)
    {
        couponsService.marketerDeleteCoupon(marketer_id, coupon_id);
        return ResponseEntity.status(200).body(new ApiResponse("your coupon is deleted"));
    }

}
