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

import java.util.List;

@Service
@RequiredArgsConstructor
public class ShopperService {
    private final ShopperRepository shopperRepository;
    private final AuthRepository authRepository;
    private final MarketerRepository marketerRepository;


    public List<Shopper> getAllShopper(){
        return shopperRepository.findAll();
    }

    public void completeShopperProfile(Shopper shopper){
        shopperRepository.save(shopper);
    }

    public void updateShopperProfile(Integer shopper_id, Shopper shopper){
        Shopper oldShopper = shopperRepository.findShopperById(shopper_id);
        if (oldShopper == null)
            throw new ApiException("Sorry the shopper id is wrong");


        oldShopper.setName(shopper.getName());
        oldShopper.setPhone(shopper.getPhone());
        oldShopper.setAddress(shopper.getAddress());

        shopperRepository.save(oldShopper);
    }

    // TODO: 9/6/2023 add additional condition to check orders status

    public void deleteShopperAccount(Integer shopper_id){
        Shopper deleteShopper = shopperRepository.findShopperById(shopper_id);
        if (deleteShopper == null)
            throw new ApiException("Sorry the shopper id is wrong");

        shopperRepository.delete(deleteShopper);
    }

    public void ShopperSelectMarketer(Integer shopper_id, Integer marketer_id){
        Shopper shopper = shopperRepository.findShopperById(shopper_id);
        Marketer marketer = marketerRepository.findMarketerById(marketer_id);

        if (shopper == null)
            throw new ApiException("Sorry the shopper id is wrong");
        else if (marketer == null)
            throw new ApiException("Sorry the marketer id is wrong");

        shopper.setMarketer(marketer);
        marketer.getShoppers().add(shopper);

        shopperRepository.save(shopper);
        marketerRepository.save(marketer);
    }
}
