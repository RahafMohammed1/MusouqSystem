package com.example.musouqsystem.Controller;

import com.example.musouqsystem.Api.ApiResponse;
import com.example.musouqsystem.Model.User;
import com.example.musouqsystem.Service.AuthService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/auth")
public class AuthController {
    private final AuthService authService;

//    @PostMapping("/adminRegister")
//    public ResponseEntity adminRegister(@RequestBody @Valid User user) {
//        authService.adminRegister(user);
//        return ResponseEntity.status(200).body(new ApiResponse("success registration for admin.."));
//    }
//
//    @PostMapping("/supplierRegister")
//    public ResponseEntity supplierRegister(@RequestBody @Valid User user) {
//        authService.supplierRegister(user);
//        return ResponseEntity.status(200).body(new ApiResponse("success registration for supplier.."));
//    }
//
//    @PostMapping("/shopperRegister")
//    public ResponseEntity shopperRegister(@RequestBody @Valid User user) {
//        authService.shopperRegister(user);
//        return ResponseEntity.status(200).body(new ApiResponse("success registration for shopper.."));
//    }
//
//    @PostMapping("/marketerRegister")
//    public ResponseEntity marketerRegister(@RequestBody @Valid User user) {
//        authService.marketerRegister(user);
//        return ResponseEntity.status(200).body(new ApiResponse("success registration for marketer.."));
//    }

}
