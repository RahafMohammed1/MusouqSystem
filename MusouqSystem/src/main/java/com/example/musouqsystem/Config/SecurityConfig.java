package com.example.musouqsystem.Config;

import com.example.musouqsystem.Service.MyUserDetailsService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {
    private final MyUserDetailsService myUserDetailsService;

    @Bean
    public DaoAuthenticationProvider authenticationProvider(){
        DaoAuthenticationProvider authenticationProvider=new
                DaoAuthenticationProvider();

        authenticationProvider.setUserDetailsService(myUserDetailsService);
        authenticationProvider.setPasswordEncoder(new
                BCryptPasswordEncoder());
        return authenticationProvider;
    }
//SHOPPER MARKETER SUPPLIER ADMIN
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http)
            throws Exception{
        http.csrf().disable()
                .sessionManagement()

                .sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED)
                .and()
                .authenticationProvider(authenticationProvider())
                .authorizeHttpRequests()

                .requestMatchers("/api/v1/auth/**").permitAll()
                .requestMatchers("/api/v1/shopper/marketerGetShoppers").hasAuthority("MARKETER")
                .requestMatchers("/api/v1/shopper/completeProfile").hasAuthority("SHOPPER")
                .requestMatchers("/api/v1/shopper/updateProfile/{shopper_id}").hasAuthority("SHOPPER")
                .requestMatchers("/api/v1/shopper/deleteAccount/{shopper_id}").hasAuthority("SHOPPER")
                .requestMatchers("/api/v1/shopper/selectMarketer/{marketer_id}").hasAuthority("SHOPPER")

                .requestMatchers("/api/v1/order/get").hasAuthority("SHOPPER")
                .requestMatchers("/api/v1/order/makeOrder").hasAuthority("SHOPPER")
                .requestMatchers("/api/v1/order/addProduct/{product_id}/{order_id}").hasAuthority("SHOPPER")
                .requestMatchers("/api/v1/order/displayTotalAmount/{order_id}").hasAuthority("SHOPPER")
                .requestMatchers("/api/v1/order/SelectshippingCompany/{order_id}/{shippingCompany_id}").hasAuthority("SHOPPER")
                .requestMatchers("/api/v1/order/completeOrder/{order_id}").hasAuthority("SHOPPER")
                .requestMatchers("/api/v1/order/deleviredOrder/{order_id}").hasAuthority("ADMIN")
                .requestMatchers("/api/v1/order/deleteOrder/{order_id}").hasAuthority("SHOPPER")

                .requestMatchers("api/v1/reviewOrder/get").hasAuthority("SHOPPER")
                .requestMatchers("api/v1/reviewOrder/addReviewOrder").hasAuthority("SHOPPER")
                .requestMatchers("api/v1/reviewOrder/updateReviewOrder").hasAuthority("SHOPPER")
                .requestMatchers("api/v1/reviewOrder/deleteReviewOrder/{reviewOrder}").hasAuthority("SHOPPER")
                .requestMatchers("api/v1/reviewOrder/deleteReviewOrder/{reviewOrder}").hasAuthority("SHOPPER")

                .requestMatchers("/api/v1/reviewMarketer/get").hasAuthority("SHOPPER")
                .requestMatchers("/api/v1/reviewMarketer/addReviewMarketer/{order_id}").hasAuthority("SHOPPER")
                .requestMatchers("/api/v1/reviewMarketer/updateReviewMarketer/{reviewMarketer_id}").hasAuthority("SHOPPER")
                .requestMatchers("/api/v1/reviewMarketer/deleteReviewMarketer/{reviewMarketer}").hasAuthority("SHOPPER")
                .requestMatchers("/api/v1/reviewMarketer/rateMarketer").hasAuthority("MARKETER")

                .requestMatchers("/api/v1/marketer/get-all-marketer").hasAuthority("SHOPPER")
                .requestMatchers("/api/v1/marketer/supplier-get-all-marketer").hasAuthority("SUPPLIER")
                .requestMatchers("/api/v1/marketer/complete-profile").hasAuthority("MARKETER")
                .requestMatchers("/api/v1/marketer/update-profile/{marketer_id}").hasAuthority("MARKETER")
                .requestMatchers("/api/v1/marketer/delete-profile").hasAuthority("MARKETER")
                .requestMatchers("/api/v1/marketer/select/supplier/{supplier_id}").hasAuthority("MARKETER")
                .requestMatchers("/api/v1/marketer/select/supplier/{supplier_id}").hasAuthority("MARKETER")
                .requestMatchers("/api/v1/marketer/delete-supplier").hasAuthority("MARKETER")


                .requestMatchers("/api/v1/request/get-marketer-request").hasAuthority("MARKETER")
                .requestMatchers("/api/v1/request/get-supplier-request").hasAuthority("SUPPLIER")
                .requestMatchers("/api/v1/request/send-request").hasAuthority("MARKETER")
                .requestMatchers("/api/v1/request/update-request/{request_id}").hasAuthority("MARKETER")
                .requestMatchers("/api/v1/request/delete-request/{request_id}").hasAuthority("MARKETER")
                .requestMatchers("/api/v1/request/accept-request/{request_id}").hasAuthority("SUPPLIER")
                .requestMatchers("/api/v1/request/reject-request/{request_id}").hasAuthority("SUPPLIER")

                .requestMatchers("/api/v1/coupons/get-all").hasAuthority("MARKETER")
                .requestMatchers("/api/v1/coupons/add-general").hasAuthority("MARKETER")
                .requestMatchers("/api/v1/coupons/add-special").hasAuthority("MARKETER")
                .requestMatchers("/api/v1/coupons/update/{coupon_id}").hasAuthority("MARKETER")
                .requestMatchers("/api/v1/coupons/delete/{coupon_id}").hasAuthority("MARKETER")
                .requestMatchers("/api/v1/coupons/marketer-activate-coupon/{coupon_id}").hasAuthority("MARKETER")
                .requestMatchers("/api/v1/coupons/marketer-deactivate-coupon/{coupon_id}").hasAuthority("MARKETER")
                .requestMatchers("/api/v1/coupons/apply-coupon/{order_id}/{code}").hasAuthority("SHOPPER")

                .requestMatchers("/api/v1/shipping/get-all").hasAnyAuthority("ADMIN","SHOPPER")
                .requestMatchers("/api/v1/shipping/add").hasAuthority("ADMIN")
                .requestMatchers("/api/v1/shipping/update/{shippingCompany_id}").hasAuthority("ADMIN")
                .requestMatchers("/api/v1/shipping/delete/{shippingCompany_id}").hasAuthority("ADMIN")

                .requestMatchers("/api/v1/category/addCategory","/api/v1/category/updateCategory/{category_id}","/api/v1/category/deleteCategory/{category_id}"
                        ,"/api/v1/image/getAllImage").hasAuthority("ADMIN")

                .requestMatchers("/api/v1/supplier/getAllSuppliers","/api/v1/product/marketerGetAllProductsOfSupplier"
                        ,"/api/v1/product/marketerAddProduct/{product_id}/{supplier_id}","/api/v1/product/marketerApplyDiscount/{product_id}/{discount}"
                        ,"/api/v1/product/marketerDeleteProduct/{product_id}").hasAuthority("MARKETER")

                .requestMatchers("/api/v1/supplier/completeProfile","/api/v1/supplier/updateProfile","/api/v1/supplier/shipOrder/{order_id}","/api/v1/supplier/deleteAccount"
                        ,"/api/v1/product/getAllProductsOfSupplier","/api/v1/product/supplierAddProduct/{category_id}"
                        , "/api/v1/product/supplierUpdateProduct/{product_id}","/api/v1/product/supplierDeleteProduct/{product_id}"
                        ,"/api/v1/category/getAllCategories","/api/v1/category/updatePercent/{category_id}/{percent}"
                        ,"/api/v1/image/addImage/{product_id}","/api/v1/image/changeImage/{product_id}","/api/v1/image/deleteImage/{product_id}").hasAuthority("SUPPLIER")

                .requestMatchers("/api/v1/product/getAllProductsByCategory/{category_id}").hasAnyAuthority("SHOPPER","MARKETER","SUPPLIER")

                .anyRequest().authenticated()
                .and()
                .logout().logoutUrl("/api/v1/auth/logout")
                .deleteCookies("JSESSIONID")
                .invalidateHttpSession(true)
                .and()
                .httpBasic();
        return http.build();
    }


}
