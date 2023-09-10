
package com.example.musouqsystem;

import com.example.musouqsystem.Controller.CouponsController;
import com.example.musouqsystem.Model.*;
import com.example.musouqsystem.Service.CouponsService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;


import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;


import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(value = CouponsController.class,excludeAutoConfiguration = {SecurityAutoConfiguration.class})
public class CouponControllerTest {
    @MockBean
    CouponsService couponsService;

    @Autowired
    MockMvc mockMvc;

    User user , user1;
    Marketer marketer;

    Shopper shopper;

    Orders orders ;


    Coupons coupon1, coupon2;

    List<Coupons> coupons;

    @BeforeEach
    void setUp() {
        user = new User(null, "RahafMohammed", "Ra@1234", "rahaf@gmail.com", "MARKETER", null, null, null);
        marketer = new Marketer(null, "Rahaf", "0551078203", null, null, null, null, user, null, null, null, null, null, null, null);
        coupon1=new Coupons(1,"AA15",10.0,"active","general",marketer);
        coupon2=new Coupons(2,"AA10",10.0,"active","special",marketer);

        coupons= Arrays.asList(coupon1);

        user1 = new User(null, "RanaMohammed", "Ra@1234", "rana@gmail.com", "SHOPPER", null, null, null);
        shopper=new Shopper(null,"Rana","0507457003","Alriyadh",null,user1,null,null,null,null,null);
        orders=new Orders(1, LocalDate.now(),"processing",1500.0,false,null,shopper,null,null,marketer,null,null);


    }

    @Test
    public void marketerAddCouponForAllUsersTest() throws Exception
    {
        mockMvc.perform(post("/api/v1/coupons/add-general")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(coupon1)))
                .andExpect(status().isOk());

    }
    @Test
    public void marketerAddCouponForSpecialShoppersTest() throws Exception
    {
        mockMvc.perform(post("/api/v1/coupons/add-special")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(coupon2)))
                .andExpect(status().isOk());

    }

    @Test
    public void marketerUpdateCouponTest() throws Exception
    {
        mockMvc.perform(put("/api/v1/coupons/update/{coupon_id}",coupon1.getId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(coupon1)))
                .andExpect(status().isOk());

    }

    @Test
    public void deleteProfileTest() throws Exception
    {
        mockMvc.perform(delete("/api/v1/coupons/delete/{coupon_id}",coupon1.getId()))
                .andExpect(status().isOk());
    }

    @Test
    public void marketerActivateCouponTest() throws Exception
    {
        mockMvc.perform(put("/api/v1/coupons/marketer-activate-coupon/{coupon_id}",coupon1.getId()))
                .andExpect(status().isOk());

    }

    @Test
    public void marketerDeactivateCouponTest() throws Exception
    {
        mockMvc.perform(put("/api/v1/coupons/marketer-deactivate-coupon/{coupon_id}",coupon1.getId()))
                .andExpect(status().isOk());

    }

    @Test
    public void applyCouponOnOrderTest() throws Exception
    {
        mockMvc.perform(put("/api/v1/coupons/apply-coupon/{order_id}/{code}",orders.getId(),"AA10"))
                .andExpect(status().isOk());
    }


}
