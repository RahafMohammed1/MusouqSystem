package com.example.musouqsystem.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.ArrayList;

@Data
@AllArgsConstructor
public class ProductListDto {
    private ArrayList<Integer> products_id;
}
