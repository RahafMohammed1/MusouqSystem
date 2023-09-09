package com.example.musouqsystem.Controller;

import com.example.musouqsystem.Api.ApiResponse;
import com.example.musouqsystem.DTO.MarketerDTO;
import com.example.musouqsystem.Model.Marketer;
import com.example.musouqsystem.Model.ShippingCompany;
import com.example.musouqsystem.Repository.ShippingCompanyRepository;
import com.example.musouqsystem.Service.ShippingCompanyService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/shipping")
@RequiredArgsConstructor
public class ShippingCompanyController {
    private final ShippingCompanyService shippingCompanyService;
    @GetMapping("/get-all")
    public ResponseEntity shopperGetAllShippingCompany()
    {
        return ResponseEntity.status(200).body(shippingCompanyService.shopperGetAllShippingCompany());
    }

    @PostMapping("/add")
    public ResponseEntity adminAddShippingCompany(@RequestBody @Valid ShippingCompany shippingCompany)
    {
        shippingCompanyService.adminAddShippingCompany(shippingCompany);
        return ResponseEntity.status(200).body(new ApiResponse("the ShippingCompany is added"));
    }

    @PutMapping("/update/{shippingCompany_id}")
    public ResponseEntity adminUpdateShippingCompany(@PathVariable Integer shippingCompany_id,@RequestBody @Valid ShippingCompany shippingCompany)
    {
        shippingCompanyService.adminUpdateShippingCompany(shippingCompany_id, shippingCompany);
        return ResponseEntity.status(200).body(new ApiResponse("the ShippingCompany is updated"));
    }

    @DeleteMapping("/delete/{shippingCompany_id}")
    public ResponseEntity adminDeleteShippingCompany(@PathVariable Integer shippingCompany_id)
    {
        shippingCompanyService.adminDeleteShippingCompany(shippingCompany_id);
        return ResponseEntity.status(200).body(new ApiResponse("the ShippingCompany id deleted"));
    }

}
