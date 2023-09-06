package com.example.musouqsystem.Service;

import com.example.musouqsystem.Api.ApiException;
import com.example.musouqsystem.Model.ShippingCompany;
import com.example.musouqsystem.Repository.ShippingCompanyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ShippingCompanyService {
    private final ShippingCompanyRepository shippingCompanyRepository;

    public List<ShippingCompany> shopperGetAllShippingCompany() {
        return shippingCompanyRepository.findAll();
    }

    public void addShippingCompany(ShippingCompany shippingCompany) {
        shippingCompanyRepository.save(shippingCompany);
    }

    public void updateShippingCompany(Integer shippingCompany_id, ShippingCompany shippingCompany) {
        ShippingCompany shippingCompany1 = shippingCompanyRepository.findShippingCompanyById(shippingCompany_id);

        if (shippingCompany1 == null) throw new ApiException("The shippingCompany not found");

        shippingCompany1.setShipping_price(shippingCompany.getShipping_price());
        shippingCompany1.setName(shippingCompany.getName());
        shippingCompany1.setDelivery_time(shippingCompany.getDelivery_time());
        shippingCompanyRepository.save(shippingCompany1);
    }

    public void deleteShippingCompany(Integer shippingCompany_id) {
        ShippingCompany shippingCompany1 = shippingCompanyRepository.findShippingCompanyById(shippingCompany_id);

        if (shippingCompany1 == null) throw new ApiException("The shippingCompany not found");
        shippingCompanyRepository.delete(shippingCompany1);
    }
}
