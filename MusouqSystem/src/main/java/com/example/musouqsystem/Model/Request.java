package com.example.musouqsystem.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Request {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotEmpty(message = "please type some thing to your supplier")
    @Column(columnDefinition = "varchar(200) not null")
    private String req_description;


    @Column(columnDefinition = "varchar(20)")
    private String status;

    @Column(columnDefinition = "datetime")
    private LocalDate req_date;

    @ManyToOne
    @JoinColumn(name = "marketer_id", referencedColumnName = "user_id")
    @JsonIgnore
    private Marketer marketer;

    @ManyToOne
    @JoinColumn(name = "supplier_id", referencedColumnName = "user_id")
    @JsonIgnore
    private Supplier supplier;

}
