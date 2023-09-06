package com.example.musouqsystem.Service;

import com.example.musouqsystem.Repository.CouponsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CouponsService {
    private final CouponsRepository couponsRepository;
}
