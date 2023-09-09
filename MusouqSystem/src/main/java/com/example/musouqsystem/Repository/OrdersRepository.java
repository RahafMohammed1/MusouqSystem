package com.example.musouqsystem.Repository;

import com.example.musouqsystem.Model.Orders;
import com.example.musouqsystem.Model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

@Repository
public interface OrdersRepository extends JpaRepository<Orders , Integer> {
    Orders findOrdersById(Integer id);

    // to get the orders for the shopper
   List<Orders> findAllByShopperId(Integer shopper_id);


}
