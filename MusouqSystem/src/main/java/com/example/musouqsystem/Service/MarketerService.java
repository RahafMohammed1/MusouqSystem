package com.example.musouqsystem.Service;

import com.example.musouqsystem.Repository.MarketerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MarketerService {
    private final MarketerRepository marketerRepository;
}
