package com.example.musouqsystem.Repository;

import com.example.musouqsystem.Model.Marketer;
import com.example.musouqsystem.Model.Shopper;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ShopperRepository extends JpaRepository<Shopper , Integer> {

    Shopper findShopperById(Integer id);

    List<Shopper> findShoppersByMarketerId(Integer marketer_id);
}
