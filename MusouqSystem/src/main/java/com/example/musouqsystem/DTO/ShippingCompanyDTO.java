package com.example.musouqsystem.DTO;

import jakarta.persistence.Column;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.hibernate.validator.constraints.Length;
@Data
@AllArgsConstructor
public class ShippingCompanyDTO {
    @NotEmpty(message = "shipping company name should not be empty")
    @Length(min = 3, message = "shipping company name length should be more than 3 characters")
    @Column(columnDefinition = "varchar(20) not null")
    private String name;

    @NotNull(message = "shipping price should not be empty")
    @Column(columnDefinition = "double not null")
    private Double shipping_price;

    private Integer order_id;
}
