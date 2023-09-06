package com.example.musouqsystem.Model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor

public class ReviewOrder {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotEmpty(message = "review content must not null")
    @Column(name = "review_order" , length = 1000)
    private String review_order;

    @Min(1)
    @Max(5)
    @Column(columnDefinition = "int(5) not null")
    private Integer rate_order;
}
