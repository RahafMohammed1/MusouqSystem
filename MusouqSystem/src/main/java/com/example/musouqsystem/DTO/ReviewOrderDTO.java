package com.example.musouqsystem.DTO;

import jakarta.persistence.Column;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor

public class ReviewOrderDTO {
    private Integer order_id;

    @NotEmpty(message = "review content must not null")
    @Column(name = "review_order" , length = 1000)
    private String review_order;

    @Min(1)
    @Max(5)
    @Column(columnDefinition = "int(5) not null")
    private Integer rate_order;
}
