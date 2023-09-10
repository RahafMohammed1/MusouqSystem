package com.example.musouqsystem.Service;

import com.example.musouqsystem.Api.ApiException;
import com.example.musouqsystem.Model.ShippingCompany;
import com.example.musouqsystem.Repository.OrdersRepository;
import com.example.musouqsystem.Repository.ShippingCompanyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ShippingCompanyService {
    private final ShippingCompanyRepository shippingCompanyRepository;
    private final OrdersRepository ordersRepository;

    public List<ShippingCompany> shopperGetAllShippingCompany() {
        return shippingCompanyRepository.findAll();
    }

    public void adminAddShippingCompany(ShippingCompany shippingCompany) {
        shippingCompanyRepository.save(shippingCompany);
    }

    public void adminUpdateShippingCompany(Integer shippingCompany_id, ShippingCompany shippingCompany) {
        ShippingCompany shippingCompany1 = shippingCompanyRepository.findShippingCompanyById(shippingCompany_id);

        if (shippingCompany1 == null) throw new ApiException("The shippingCompany not found");

        shippingCompany1.setShipping_price(shippingCompany.getShipping_price());
        shippingCompany1.setName(shippingCompany.getName());
        shippingCompanyRepository.save(shippingCompany1);
    }

    public void adminDeleteShippingCompany(Integer shippingCompany_id) {
        ShippingCompany shippingCompany1 = shippingCompanyRepository.findShippingCompanyById(shippingCompany_id);
        if (shippingCompany1 == null) throw new ApiException("The shippingCompany not found");
        if(!shippingCompany1.getOrders().isEmpty())
            throw new ApiException("you can't delete this company because there is Orders related with it");
        shippingCompanyRepository.delete(shippingCompany1);
    }

}
