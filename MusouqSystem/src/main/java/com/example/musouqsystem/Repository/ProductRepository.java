package com.example.musouqsystem.Repository;

import com.example.musouqsystem.Model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {
    Product findProductById(Integer id);

    List<Product> findProductsBySupplierId(Integer supplier_id);

//    List<Product> findProductsByMarketerId(Integer marketer_id);
}
