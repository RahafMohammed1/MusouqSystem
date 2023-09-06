package com.example.musouqsystem.DTO;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.hibernate.validator.constraints.Length;
@Data
@AllArgsConstructor

public class ShopperDTO {
    private Integer user_id;

    @NotEmpty(message = "The shopper name must not empty")
    @Length(min = 3, message = "shopper name length should be more than 3 characters")
    @Column(columnDefinition = "varchar(20) not null")
    private String name;

    @NotEmpty(message = "The phone number must not empty")
    @Pattern(regexp = "^(009665|9665|\\+9665|05|5)(5|0|3|6|4|9|1|8|7)([0-9]{7})$")
    @Column(columnDefinition = "varchar(10) not null")
    private String phone;

    @NotEmpty(message = "The address must not empty")
    @Length(min = 3, message = "shopper name length should be more than 3 characters")
    @Column(columnDefinition = "varchar(255) not null")
    private String address;

    @Column(columnDefinition = "int default 0")
    private Integer num_of_orders;

}
