package com.example.musouqsystem.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotEmpty(message = "product name should not be empty")
    @Length(min = 3, message = "product name length should be more than 3 characters")
    @Column(columnDefinition = "varchar(20) not null")
    private String name;

    @NotEmpty(message = "product description should not be empty")
    @Column(columnDefinition = "varchar(100) not null")
    private String description;

    @NotNull(message = "price should not be empty")
    @Column(columnDefinition = "double not null")
    private Double price;

    @Column(columnDefinition = "double")
    private Double price_after_discount;

    @NotNull(message = "stock should not be empty")
    @Column(columnDefinition = "int not null")
    private Integer stock;


    @ManyToOne
    @JoinColumn(name = "supplier_id", referencedColumnName = "user_id")
    @JsonIgnore
    private Supplier supplier;

    @ManyToOne
    @JoinColumn(name = "category_id", referencedColumnName = "id")
    @JsonIgnore
    private Category category;

    @ManyToOne
    @JoinColumn(name = "order_id", referencedColumnName = "id")
    @JsonIgnore
    private Orders orders;

    @ManyToMany
    @JsonIgnore
    private Set<Marketer> marketers;


    @OneToOne(cascade = CascadeType.ALL, mappedBy = "product")
    @PrimaryKeyJoinColumn
    private Image image;

}
