package com.example.musouqsystem.Service;

import com.example.musouqsystem.Model.User;
import com.example.musouqsystem.Repository.AuthRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final AuthRepository authRepository;


    public List<User> getAllUser(){
        return authRepository.findAll();
    }


    public void adminRegister(User user) {
        String hash = new BCryptPasswordEncoder().encode(user.getPassword());
        user.setPassword(hash);
        user.setRole("ADMIN");
        authRepository.save(user);
    }
    public void supplierRegister(User user) {
        String hash = new BCryptPasswordEncoder().encode(user.getPassword());
        user.setPassword(hash);
        user.setRole("SUPPLIER");
        authRepository.save(user);
    }

    public void shopperRegister(User user) {
        String hash = new BCryptPasswordEncoder().encode(user.getPassword());
        user.setPassword(hash);
        user.setRole("SHOPPER");
        authRepository.save(user);
    }

    public void marketerRegister(User user) {
        String hash = new BCryptPasswordEncoder().encode(user.getPassword());
        user.setPassword(hash);
        user.setRole("MARKETER");
        authRepository.save(user);
    }

}
