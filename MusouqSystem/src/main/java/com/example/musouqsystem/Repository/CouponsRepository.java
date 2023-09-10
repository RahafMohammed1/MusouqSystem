package com.example.musouqsystem.Repository;

import com.example.musouqsystem.Model.Coupons;
import com.example.musouqsystem.Model.Marketer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CouponsRepository extends JpaRepository<Coupons,Integer> {
    Coupons findCouponsById(Integer id);
    Coupons findCouponsByCode(String code);

    List<Coupons> findCouponsByMarketer(Marketer marketer);
}
