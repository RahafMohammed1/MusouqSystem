package com.example.musouqsystem.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Coupons {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;


    @Pattern(regexp = "^(?=[A-Z])([a-zA-Z0-9]+)$",message = "the code must be start with capital letter")
    @Size(min=3,max = 12,message ="the code must have 3-12 character" )
    @Column(columnDefinition = "varchar(30)")
    private String code;

    @NotNull(message = "percentage should not be empty")
    @Column(columnDefinition = "double not null")
    private Double percentage;

    @Column(columnDefinition = "boolean")
    private Boolean status;

    @ManyToOne
    @JoinColumn(name = "marketer_id", referencedColumnName = "id")
    @JsonIgnore
    private Marketer marketer;

}
