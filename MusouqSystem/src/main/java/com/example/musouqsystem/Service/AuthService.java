package com.example.musouqsystem.Service;

import com.example.musouqsystem.Model.User;
import com.example.musouqsystem.Repository.AuthRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final AuthRepository authRepository;


    public List<User> getAllUser(){
        return authRepository.findAll();
    }

}
