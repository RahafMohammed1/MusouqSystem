package com.example.musouqsystem.RepositoryTest;

import com.example.musouqsystem.Model.Coupons;
import com.example.musouqsystem.Repository.CouponsRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class CouponsRepositoryTest {

    @Autowired
    CouponsRepository couponsRepository;

    Coupons coupons, coupons1;

    @BeforeEach
    void setUp() {
        coupons1 = new Coupons(null, "AA15", 0.3, "active", "general", null);
    }

    @Test
    public void findCouponsByCodeTest() {
        couponsRepository.save(coupons1);

        coupons = couponsRepository.findCouponsByCode(coupons1.getCode());
        Assertions.assertThat(coupons).isEqualTo(coupons1);

    }
}
