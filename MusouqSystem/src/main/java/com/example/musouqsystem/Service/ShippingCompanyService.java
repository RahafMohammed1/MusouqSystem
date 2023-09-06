package com.example.musouqsystem.Service;

import com.example.musouqsystem.Repository.ShippingCompanyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ShippingCompanyService {
    private final ShippingCompanyRepository shippingCompanyRepository;

}
