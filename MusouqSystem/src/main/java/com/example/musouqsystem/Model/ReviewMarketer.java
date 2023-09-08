package com.example.musouqsystem.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
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

public class ReviewMarketer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotEmpty(message = "review content must not null")
    @Column(name = "review_marketer" , length = 1000)
    private String review_marketer;

    @Min(1)
    @Max(5)
    @Column(columnDefinition = "int(5) not null")
    private Integer rate_marketer;

    @ManyToOne
    @JoinColumn(name = "shopper_id", referencedColumnName = "user_id")
    private Shopper shopper;

    @ManyToOne
    @JoinColumn(name = "marketer_id" , referencedColumnName = "user_id")
    @JsonIgnore
    private Marketer marketer;


}
