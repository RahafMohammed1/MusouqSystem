package com.example.musouqsystem.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Marketer {
    @Id
    private Integer id;

    @NotEmpty(message = "The marketer name must not empty")
    @Length(min = 3, message = "marketer name length should be more than 3 characters")
    @Column(columnDefinition = "varchar(20) not null")
    private String name;

    @NotEmpty(message = "The phone number must not empty")
    @Pattern(regexp = "^(009665|9665|\\+9665|05|5)(5|0|3|6|4|9|1|8|7)([0-9]{7})$")
    @Column(columnDefinition = "varchar(10) not null")
    private String phone;

    @Column(columnDefinition = "int default 0")
    private Integer number_of_product_sold = 0 ;

    @Column(columnDefinition = "double default 0")
    private Double dues = 0.0 ;

    @OneToOne
    @MapsId
    @JsonIgnore
    private User user;

//    @OneToMany(cascade = CascadeType.ALL , mappedBy = "marketer")

}
