package com.example.musouqsystem.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;


    @NotEmpty(message = "category title should not be empty")
    @Column(columnDefinition = "varchar(50) not null")
    private String title;

    @Column(columnDefinition = "double")
    private Double marketer_percent;


//    @ManyToOne
//    @JoinColumn(name = "supplier_id", referencedColumnName = "id")
//    @JsonIgnore
//    private Supplier supplier;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "category")
    private Set<Product> products;

}
