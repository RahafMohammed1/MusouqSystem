package com.example.musouqsystem.DTO;

import jakarta.persistence.Column;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CouponsDTO {

    @Pattern(regexp = "^(?=[A-Z])([a-zA-Z0-9]+)$",message = "the code must be start with capital letter")
    @Size(min=3,max = 12,message ="the code must have 3-12 character" )
    @Column(columnDefinition = "varchar(30) unique")
    private String code;

    @NotNull(message = "percentage should not be empty")
    @Column(columnDefinition = "double not null")
    private Double percentage;


    private Integer marketer_id;
}
