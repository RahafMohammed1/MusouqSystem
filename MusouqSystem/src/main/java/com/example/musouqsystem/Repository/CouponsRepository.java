package com.example.musouqsystem.Repository;

import com.example.musouqsystem.Model.Coupons;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CouponsRepository extends JpaRepository<Coupons,Integer> {
    Coupons findCouponsById(Integer id);
    Coupons findCouponsByCode(String code);
}
