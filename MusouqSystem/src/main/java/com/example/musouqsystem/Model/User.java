package com.example.musouqsystem.Model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
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
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotEmpty(message = "username should not be empty")
    @Length(min = 5, message = "username length should be more than 5 characters")
    @Column(columnDefinition = "varchar(20) not null unique")
    private String username;

    @NotEmpty(message = "password should not be empty")
    @Pattern(regexp = "^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[a-zA-Z]).{6,}$", message = "enter strong password!")
    @Column(columnDefinition = "varchar(100) not null")
    private String password;

    @NotEmpty(message = "email should not be empty")
    @Email(regexp = "[a-z0-9._%+-]+@[a-z0-9.-]+\\.[a-z]{2,3}", message = "enter valid email")
    @Column(columnDefinition = "varchar(30) not null unique")
    private String email;

    private String role;

//
//    @OneToOne(cascade = CascadeType.ALL, mappedBy = "user")
//    @PrimaryKeyJoinColumn
//    private Marketer marketer;
//
//    @OneToOne(cascade = CascadeType.ALL, mappedBy = "user")
//    @PrimaryKeyJoinColumn
//    private Shopper shopper;
//
//    @OneToOne(cascade = CascadeType.ALL, mappedBy = "user")
//    @PrimaryKeyJoinColumn
//    private Supplier supplier;
}
