package com.example.musouqsystem.Service;

import com.example.musouqsystem.Api.ApiException;
import com.example.musouqsystem.DTO.ShopperDTO;
import com.example.musouqsystem.Model.Marketer;
import com.example.musouqsystem.Model.Shopper;
import com.example.musouqsystem.Model.User;
import com.example.musouqsystem.Repository.AuthRepository;
import com.example.musouqsystem.Repository.MarketerRepository;
import com.example.musouqsystem.Repository.ShopperRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.yaml.snakeyaml.error.Mark;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ShopperService {
    private final ShopperRepository shopperRepository;
    private final AuthRepository authRepository;
    private final MarketerRepository marketerRepository;


    public List<Shopper> getAllShopper(Integer user_id){

        return shopperRepository.findShoppersByMarketerId(user_id);
    }

    public void completeShopperProfile(Integer user_id,ShopperDTO shopperDTO){
        User user = authRepository.findUserById(user_id);
        Shopper checkShopper = shopperRepository.findShopperById(user_id);

        if (checkShopper != null)
            throw new ApiException("This shopper already complete his profile");

        shopperDTO.setUser_id(user_id);
        Shopper shopper = new Shopper(shopperDTO.getUser_id(), shopperDTO.getName(),shopperDTO.getPhone(),shopperDTO.getAddress(),0,user,null,null,null,null
        ,null);

        shopperRepository.save(shopper);
    }

    public void updateShopperProfile(Integer user_id,Integer shopper_id, ShopperDTO shopperDTO){
        User user = authRepository.findUserById(user_id);
        Shopper oldShopper = shopperRepository.findShopperById(shopper_id);
        if (oldShopper == null)
            throw new ApiException("Sorry the shopper id is wrong");
        else if (user.getShopper().getId() != shopper_id) {
            throw new ApiException("Sorry you can't update this profile");
        }

        oldShopper.setName(shopperDTO.getName());
        oldShopper.setPhone(shopperDTO.getPhone());
        oldShopper.setAddress(shopperDTO.getAddress());

        shopperRepository.save(oldShopper);
    }


    public void deleteShopperAccount(Integer user_id,Integer shopper_id){
        Shopper deleteShopper = shopperRepository.findShopperById(shopper_id);
        User user = authRepository.findUserById(user_id);

        if (deleteShopper == null)
            throw new ApiException("Sorry the shopper id is wrong");
        else if (user.getShopper().getId() != shopper_id) {
            throw new ApiException("Sorry you can't delete this account");
        }
        if (deleteShopper.getOrders().isEmpty()) {
            authRepository.delete(user);
            shopperRepository.delete(deleteShopper);
        }else
            throw new ApiException("Sorry you can't delete your profile");
    }

    public void ShopperSelectMarketer(Integer user_id, Integer marketer_id){
        Shopper shopper = shopperRepository.findShopperById(user_id);
        Marketer marketer = marketerRepository.findMarketerById(marketer_id);

        if (shopper == null)
            throw new ApiException("Sorry the shopper id is wrong");
        else if (marketer == null)
            throw new ApiException("Sorry the marketer id is wrong");


        shopper.setMarketer(marketer);

        shopperRepository.save(shopper);
    }
}
