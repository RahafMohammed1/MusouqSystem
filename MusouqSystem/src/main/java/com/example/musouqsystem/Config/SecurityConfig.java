package com.example.musouqsystem.Config;

import com.example.musouqsystem.Service.MyUserDetailsService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
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
                .requestMatchers("/api/v1/shopper/selectMarketer/{shopper_id}/{marketer_id}").hasAuthority("SHOPPER")

                .requestMatchers("/api/v1/order/get").hasAuthority("SHOPPER")
                .requestMatchers("/api/v1/order/makeOrder/{shopper_id}").hasAuthority("SHOPPER")
                .requestMatchers("/api/v1/order/addProduct/{shopper_id}/{product_id}/{order_id}").hasAuthority("SHOPPER")
                .requestMatchers("/api/v1/order/calcAmount/{order_id}/{product_id}").hasAuthority("SHOPPER")
                .requestMatchers("/api/v1/order/SelectshippingCompany/{order_id}/{shippingCompany_id}").hasAuthority("SHOPPER")
                .requestMatchers("/api/v1/order/completeOrder/{order_id}").hasAuthority("SHOPPER")
                .requestMatchers("/api/v1/order/deleviredOrder/{order_id}").hasAuthority("ADMIN")
                .requestMatchers("/api/v1/order/deleteOrder/{order_id}").hasAuthority("SHOPPER")

                .requestMatchers("/api/v1/marketer/get-all-marketer").hasAuthority("SHOPPER")
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


                .requestMatchers("/api/v1/supplier/getAllSuppliers","/api/v1/product/marketerGetAllProductsOfSupplier","/api/v1/product/getAllProductsByCategory/{category_id}"
                        ,"/api/v1/product/marketerAddProduct/{product_id}/{supplier_id}","/api/v1/product/marketerApplyDiscount/{product_id}/{discount}"
                        ,"/api/v1/product/marketerDeleteProduct/{product_id}").hasAuthority("MARKETER")

                .requestMatchers("/api/v1/supplier/completeProfile","/api/v1/supplier/updateProfile","/api/v1/supplier/shipOrder/{order_id}","/api/v1/supplier/deleteAccount"
                        ,"/api/v1/product/getAllProductsOfSupplier","/api/v1/product/getAllProductsByCategory/{category_id}","/api/v1/product/supplierAddProduct/{category_id}"
                        , "/api/v1/product/supplierUpdateProduct/{product_id}","/api/v1/product/supplierDeleteProduct/{product_id}").hasAuthority("SUPPLIER")

                .requestMatchers("/api/v1/product/getAllProductsByCategory/{category_id}").hasAuthority("SHOPPER")
                

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
