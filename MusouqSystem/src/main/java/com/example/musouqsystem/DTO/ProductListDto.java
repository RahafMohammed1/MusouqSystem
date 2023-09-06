package com.example.musouqsystem.DTO;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.ArrayList;

@Data
@AllArgsConstructor
public class ProductListDto {
    @NotNull(message = "The product list must not null")
    private ArrayList<Integer> products;
}
