package com.example.musouqsystem.Service;

import com.example.musouqsystem.Api.ApiException;
import com.example.musouqsystem.DTO.ShopperDTO;
import com.example.musouqsystem.Model.Shopper;
import com.example.musouqsystem.Model.User;
import com.example.musouqsystem.Repository.AuthRepository;
import com.example.musouqsystem.Repository.ShopperRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ShopperService {
    private final ShopperRepository shopperRepository;
    private final AuthRepository authRepository;


    public List<Shopper> getAllShopper(){
        return shopperRepository.findAll();
    }

    public void completeShopperProfile(ShopperDTO shopperDTO){
    }

    public void updateShopperProfile(Integer user_id, ShopperDTO shopperDTO){
        User user = authRepository.findUserById(user_id);
        if (user == null)
            throw new ApiException("Sorry the shopper not found");

    }
}
