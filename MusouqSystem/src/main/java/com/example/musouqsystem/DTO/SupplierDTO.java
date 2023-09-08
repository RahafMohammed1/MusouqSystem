package com.example.musouqsystem.DTO;

import jakarta.persistence.Column;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

@Data
@AllArgsConstructor
public class SupplierDTO {
    private Integer user_id;
    @NotEmpty(message = "name should not be empty")
    @Length(min = 3, message = "name length should be more than 3 characters")
    @Column(columnDefinition = "varchar(10) not null")
    private String name;

    @NotEmpty(message = "phone should not be empty")
    @Length(min = 9, message = "phone length should be 10 numbers")
    @Column(columnDefinition = "varchar(10) not null")
    private String phone;


}
