package com.example.musouqsystem.Repository;

import com.example.musouqsystem.Model.Shopper;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ShopperRepository extends JpaRepository<Shopper , Integer> {

    Shopper findShopperById(Integer id);
}
