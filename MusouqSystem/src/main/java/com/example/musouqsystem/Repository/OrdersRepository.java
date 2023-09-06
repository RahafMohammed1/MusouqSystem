package com.example.musouqsystem.Repository;

import com.example.musouqsystem.Model.Orders;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrdersRepository extends JpaRepository<Orders , Integer> {
    Orders findOrdersById(Integer id);
}
