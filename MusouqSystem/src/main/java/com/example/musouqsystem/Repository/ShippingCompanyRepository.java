package com.example.musouqsystem.Repository;

import com.example.musouqsystem.Model.Request;
import com.example.musouqsystem.Model.ShippingCompany;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ShippingCompanyRepository extends JpaRepository<ShippingCompany,Integer> {

    ShippingCompany findShippingCompanyById(Integer id);
}
