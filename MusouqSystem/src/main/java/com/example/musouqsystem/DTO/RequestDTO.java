package com.example.musouqsystem.DTO;

import jakarta.persistence.Column;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
@Data
@AllArgsConstructor
public class RequestDTO {

    @NotEmpty(message = "please type some thing to your supplier")
    @Column(columnDefinition = "varchar(200) not null")
    private String req_description;

    private Integer user_id;

}
