package com.example.musouqsystem.Model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import java.util.Set;

@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ShippingCompany {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotEmpty(message = "shipping company name should not be empty")
    @Length(min = 3, message = "shipping company name length should be more than 3 characters")
    @Column(columnDefinition = "varchar(20) not null")
    private String name;

    @NotNull(message = "shipping price should not be empty")
    @Column(columnDefinition = "double not null")
    private Double shipping_price;


    @OneToMany(cascade = CascadeType.ALL, mappedBy = "shippingCompany")
    private Set<Orders> orders;


}
