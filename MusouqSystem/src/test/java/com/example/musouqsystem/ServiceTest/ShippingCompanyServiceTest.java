package com.example.musouqsystem.ServiceTest;

import com.example.musouqsystem.Model.Category;
import com.example.musouqsystem.Model.ShippingCompany;
import com.example.musouqsystem.Repository.ShippingCompanyRepository;
import com.example.musouqsystem.Service.ShippingCompanyService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)

public class ShippingCompanyServiceTest {

    @InjectMocks
    ShippingCompanyService shippingCompanyService;

    @Mock
    ShippingCompanyRepository shippingCompanyRepository;

    ShippingCompany shippingCompany_1;
    ShippingCompany shippingCompany_2;

    List<ShippingCompany> shippingCompanies;

    @BeforeEach
    void setUp() {
        shippingCompany_1 = new ShippingCompany(null,"Aramex",30.5,null);
        shippingCompany_2 = new ShippingCompany(null,"Naqel",23.4,null);

        shippingCompanies = new ArrayList<>();
        shippingCompanies.add(shippingCompany_1);
        shippingCompanies.add(shippingCompany_2);
    }

    @Test
    public void shopperGetAllShippingCompanyTest(){
        when(shippingCompanyRepository.findAll()).thenReturn(shippingCompanies);
        List<ShippingCompany> checkShippingCompany = shippingCompanyService.shopperGetAllShippingCompany();

        Assertions.assertEquals(checkShippingCompany,shippingCompanies);
        Mockito.verify(shippingCompanyRepository, times(1)).findAll();

    }
}
