package com.example.musouqsystem.Repository;

import com.example.musouqsystem.Model.Orders;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrdersRepository extends JpaRepository<Orders , Integer> {
    Orders findOrdersById(Integer id);

    // to get the orders for the shopper
//    List<Orders> findAllByUser();
}
