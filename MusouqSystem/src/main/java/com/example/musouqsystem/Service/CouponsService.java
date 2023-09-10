package com.example.musouqsystem.Service;

import com.example.musouqsystem.Api.ApiException;
import com.example.musouqsystem.DTO.CouponsDTO;
import com.example.musouqsystem.Model.Coupons;
import com.example.musouqsystem.Model.Marketer;
import com.example.musouqsystem.Model.Orders;
import com.example.musouqsystem.Model.Shopper;
import com.example.musouqsystem.Repository.CouponsRepository;
import com.example.musouqsystem.Repository.MarketerRepository;
import com.example.musouqsystem.Repository.OrdersRepository;
import com.example.musouqsystem.Repository.ShopperRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class CouponsService {
    private final CouponsRepository couponsRepository;
    private final MarketerRepository marketerRepository;
    private final OrdersRepository ordersRepository;
    private final ShopperRepository shopperRepository;

    public List<Coupons> MarketerViewHisCoupons(Integer marketer_id) {
        Marketer marketer = marketerRepository.findMarketerById(marketer_id);
        if (marketer == null)
            throw new ApiException("you should complete your profile first");
        if (marketer.getCoupons().isEmpty())
            throw new ApiException("you have not any coupon yet");
        return couponsRepository.findCouponsByMarketer(marketer);
    }

    public void marketerAddCouponForAllUsers(Integer marketer_id, CouponsDTO couponsDTO) {
        Marketer marketer = marketerRepository.findMarketerById(marketer_id);
        if (marketer == null) throw new ApiException("you should complete your profile first");

        Coupons coupon = new Coupons();
        coupon.setMarketer(marketer);
        coupon.setCode(couponsDTO.getCode());
        coupon.setPercentage(couponsDTO.getPercentage());
        coupon.setType("general");
        coupon.setStatus("active");
        couponsRepository.save(coupon);
    }
    //marketerAddCouponForSpecificShoppers
    public void marketerAddCouponForSpecialShoppers(Integer marketer_id, CouponsDTO couponsDTO) {
        Marketer marketer = marketerRepository.findMarketerById(marketer_id);
        if (marketer == null) throw new ApiException("you should complete your profile first");

        Coupons coupon = new Coupons();
        coupon.setMarketer(marketer);
        coupon.setCode(couponsDTO.getCode());
        coupon.setPercentage(couponsDTO.getPercentage());
        coupon.setType("special");
        coupon.setStatus("active");
        couponsRepository.save(coupon);
    }

    public void marketerUpdateCoupon(Integer marketer_id, Integer coupon_id, Coupons coupons) {
        Marketer marketer = marketerRepository.findMarketerById(marketer_id);
        if (marketer == null)
            throw new ApiException("you should complete your profile first");
        Coupons coupons1 = couponsRepository.findCouponsById(coupon_id);
        if(coupons1==null)
            throw new ApiException("coupon not found");
        if (coupons1.getMarketer().getId() != marketer_id)
            throw new ApiException("the coupon not belong to you");
        coupons1.setCode(coupons.getCode());
        coupons1.setPercentage(coupons.getPercentage());
        coupons1.setType(coupons.getType());
        coupons1.setStatus(coupons.getStatus());
        couponsRepository.save(coupons1);
    }

    public void marketerDeleteCoupon(Integer marketer_id, Integer coupon_id) {
        Marketer marketer = marketerRepository.findMarketerById(marketer_id);
        if (marketer == null)
            throw new ApiException("you should complete your profile first");
        Coupons coupons = couponsRepository.findCouponsById(coupon_id);
        if(coupons==null)
            throw new ApiException("coupon not found");
        if (coupons.getMarketer().getId() != marketer_id)
            throw new ApiException("the coupon not belong to you");
        couponsRepository.delete(coupons);
    }

    public void marketerActivateCoupon(Integer marketer_id, Integer coupon_id) {
        Marketer marketer = marketerRepository.findMarketerById(marketer_id);
        if (marketer == null)
            throw new ApiException("you should complete your profile first");
        Coupons coupons = couponsRepository.findCouponsById(coupon_id);
        if (coupons.getMarketer().getId() != marketer_id)
            throw new ApiException("the coupon not belong to you");
        if (coupons.getStatus().equalsIgnoreCase("active"))
            throw new ApiException("the coupon is already active");
        coupons.setStatus("active");
        couponsRepository.save(coupons);
    }

    public void marketerDeactivateCoupon(Integer marketer_id, Integer coupon_id) {
        Marketer marketer = marketerRepository.findMarketerById(marketer_id);
        if (marketer == null)
            throw new ApiException("you should complete your profile first");
        Coupons coupons = couponsRepository.findCouponsById(coupon_id);
        if (coupons.getMarketer().getId() != marketer_id)
            throw new ApiException("the coupon not belong to you");
        if (coupons.getStatus().equalsIgnoreCase("deactive"))
            throw new ApiException("the coupon is already deactivate");
        coupons.setStatus("deactive");
        couponsRepository.save(coupons);
    }

    public void applyCouponOnOrder(Integer shopper_id,Integer order_id, String code) {
        Orders orders = ordersRepository.findOrdersById(order_id);
        if (orders == null)
            throw new ApiException("the Order not exist");
        Shopper shopper=shopperRepository.findShopperById(shopper_id);
        if (shopper == null)
            throw new ApiException("the shopper not exist");
        if (orders.getShopper().getId()!=shopper_id)
            throw new ApiException("this Order not belong to you");
        if(orders.isAppliedCoupon())
            throw new ApiException("you already applied coupon you can't applied another one");

        Marketer marketer=marketerRepository.findMarketerById(orders.getMarketer().getId());
        Coupons coupons = couponsRepository.findCouponsByCode(code);

        if (coupons == null)
            throw new ApiException("Error,it's invalid coupon please try another one ");

        if (!marketer.getCoupons().contains(coupons))
            throw new ApiException("please use your marketer coupons");

        if (coupons.getStatus().equalsIgnoreCase("deactive"))
            throw new ApiException("this coupon is not active please try another one ");

        if (coupons.getType().equalsIgnoreCase("special")) {

            if (shopper.getNum_of_orders() == null)
                shopper.setNum_of_orders(0);

            if (shopper.getNum_of_orders() == 0 || shopper.getNum_of_orders()<5)
                throw new ApiException("You cannot use this code because it is reserved for special shoppers");

            Double coupon_percentage = (coupons.getPercentage()) / 100;
            Double amountOfDiscount=orders.getTotal_amount() * coupon_percentage;
            Double total_price_after_coupon = orders.getTotal_amount()-amountOfDiscount;
            orders.setTotal_amount(total_price_after_coupon);
            orders.setAppliedCoupon(true);
            ordersRepository.save(orders);
        } else {
            Double coupon_percentage = (coupons.getPercentage()) / 100;
            Double amountOfDiscount=orders.getTotal_amount() * coupon_percentage;
            Double total_price_after_coupon = orders.getTotal_amount()-amountOfDiscount;
            orders.setTotal_amount(total_price_after_coupon);
            orders.setAppliedCoupon(true);
            ordersRepository.save(orders);
        }
    }
}
