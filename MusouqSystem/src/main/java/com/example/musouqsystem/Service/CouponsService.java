package com.example.musouqsystem.Service;

import com.example.musouqsystem.Api.ApiException;
import com.example.musouqsystem.DTO.CouponsDTO;
import com.example.musouqsystem.Model.Coupons;
import com.example.musouqsystem.Model.Marketer;
import com.example.musouqsystem.Repository.CouponsRepository;
import com.example.musouqsystem.Repository.MarketerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class CouponsService {
    private final CouponsRepository couponsRepository;
    private final MarketerRepository marketerRepository;

    public Set<Coupons> MarketerViewHisCoupons(Integer marketer_id) {
        Marketer marketer = marketerRepository.findMarketerById(marketer_id);
        if (marketer == null) throw new ApiException("marketer not exist");
        if (marketer.getCoupons() == null) throw new ApiException("you have not any coupon yet");
        return marketer.getCoupons();
    }

    public void marketerAddCouponForAllUsers(Integer marketer_id, CouponsDTO couponsDTO) {
        Marketer marketer = marketerRepository.findMarketerById(marketer_id);
        if (marketer == null) throw new ApiException("marketer not exist");

        Coupons coupon = new Coupons();
        coupon.setMarketer(marketer);
        coupon.setCode(couponsDTO.getCode());
        coupon.setPercentage(couponsDTO.getPercentage());

        couponsRepository.save(coupon);
    }

    public void marketerUpdateCoupon(Integer marketer_id, Integer coupon_id, CouponsDTO couponsDTO) {
        Marketer marketer = marketerRepository.findMarketerById(marketer_id);
        if (marketer == null) throw new ApiException("marketer not exist");
        if (marketer.getCoupons() == null) throw new ApiException("you have not any coupon yet");
        Coupons coupons = couponsRepository.findCouponsById(coupon_id);
        if (coupons.getMarketer().getId() != marketer_id) throw new ApiException("the coupon not belong to you");
        coupons.setCode(couponsDTO.getCode());
        coupons.setPercentage(couponsDTO.getPercentage());
        couponsRepository.save(coupons);
    }

    public void marketerDeleteCoupon(Integer marketer_id, Integer coupon_id) {
        Marketer marketer = marketerRepository.findMarketerById(marketer_id);
        if (marketer == null) throw new ApiException("marketer not exist");
        if (marketer.getCoupons() == null) throw new ApiException("you have not any coupon yet");
        Coupons coupons = couponsRepository.findCouponsById(coupon_id);
        if (coupons.getMarketer().getId() != marketer_id) throw new ApiException("the coupon not belong to you");
        couponsRepository.delete(coupons);
    }

    //marketerAddCouponForSpecificShoppers
    // marketerChangeCouponStatus

}
