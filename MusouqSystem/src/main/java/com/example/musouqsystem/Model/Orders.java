package com.example.musouqsystem.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.Set;

@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Orders {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(columnDefinition = "datetime")
    private LocalDate order_date;

    @Pattern(regexp = "(processing)|(delivered)|(shipped)")
    @Column(columnDefinition = "varchar(30)")
    private String order_status;

    @Column(columnDefinition = "double default 0")
    private Double total_amount =0.0;

    @Column(columnDefinition = "boolean default false")
    private boolean isAppliedCoupon;

    @Column(columnDefinition = "boolean")
    private Boolean review_status;

    @ManyToOne
    @JoinColumn(name = "shopper_id" , referencedColumnName = "user_id")
    private Shopper shopper;

    @OneToOne(cascade = CascadeType.ALL, mappedBy = "orders")
    @PrimaryKeyJoinColumn
    private ReviewOrder reviewOrder;

    @ManyToOne
    @JoinColumn(name = "supplier_id", referencedColumnName = "user_id")
    @JsonIgnore
    private Supplier supplier;

    @ManyToOne
    @JoinColumn(name = "marketer_id", referencedColumnName = "user_id")
    @JsonIgnore
    private Marketer marketer;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "orders")
    private Set<Product> products;

    @ManyToOne
    @JoinColumn(name = "shipping_company_id", referencedColumnName = "id")
    @JsonIgnore
    private ShippingCompany shippingCompany;
}
