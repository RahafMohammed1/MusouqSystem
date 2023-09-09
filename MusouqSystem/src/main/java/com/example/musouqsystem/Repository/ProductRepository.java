package com.example.musouqsystem.Repository;

import com.example.musouqsystem.Model.Category;
import com.example.musouqsystem.Model.Marketer;
import com.example.musouqsystem.Model.Product;
import com.example.musouqsystem.Model.Supplier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {
    Product findProductById(Integer id);

    List<Product> findProductsBySupplierId(Integer supplier_id);

    Product findProductByIdAndSupplierId(Integer product_id, Integer supplier_id);

    List<Product> findProductsByCategoryAndMarketersContains(Category category, Marketer marketer);

    List<Product> findProductsByCategoryAndSupplier(Category category, Supplier supplier);

    List<Product> findAllByMarketersContains(Marketer marketer);


}
