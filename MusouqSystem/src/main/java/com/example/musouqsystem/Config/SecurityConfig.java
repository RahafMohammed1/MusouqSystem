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
//                .requestMatchers("/api/v1/marketer/**").permitAll()
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
