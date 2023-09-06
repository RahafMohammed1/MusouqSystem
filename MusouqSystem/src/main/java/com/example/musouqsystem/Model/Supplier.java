package com.example.musouqsystem.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
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
public class Supplier {
    @Id
    private Integer id;

    @NotEmpty(message = "name should not be empty")
    @Length(min = 3, message = "name length should be more than 3 characters")
    @Column(columnDefinition = "varchar(10) not null")
    private String name;

    @NotEmpty(message = "phone should not be empty")
    @Length(min = 9, message = "phone length should be 10 numbers")
    @Column(columnDefinition = "varchar(10) not null")
    private String phone;


    @OneToOne
    @MapsId
    @JsonIgnore
    private User user;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "supplier")
    private Set<Product> products;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "supplier")
    private Set<Category> categories;
}
