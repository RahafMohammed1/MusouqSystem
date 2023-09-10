package com.example.musouqsystem.RepositoryTest;

import com.example.musouqsystem.Model.*;
import com.example.musouqsystem.Repository.ProductRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

@ExtendWith(SpringExtension.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class ProductRepositoryTest {
    @Autowired
    ProductRepository productRepository;

    User user;
    Supplier supplier;
    Category category;
    Product product, product1, product2;
    List<Product> products;

    @BeforeEach
    void setUp() {
        user = new User(null, "amal123", "Amal_1234", "example@gmail.com", "SUPPLIER", null, null,null);
        supplier = new Supplier(4, "amal", "0505221256", user, null, null, null, null, null);
        category = new Category(1, "electronic", null, null);
        product1 = new Product(null, "apple watch", "black color, 44mm", 1500.0, 0.0, 4, supplier, category, null, null, null);
        product2 = new Product(null, "iphone", "black color", 5000.0, 0.0, 4, supplier, category, null, null, null);
    }

    @Test
    public void findProductsBySupplierIdTest() {
        productRepository.save(product1);
        productRepository.save(product2);

        products = productRepository.findProductsBySupplierId(supplier.getId());
        Assertions.assertThat(products.get(0).getSupplier().getId()).isEqualTo(supplier.getId());
    }

    @Test
    public void findProductByIdAndSupplierId() {
        productRepository.save(product1);
        product = productRepository.findProductByIdAndSupplierId(product1.getId(), supplier.getId());
        Assertions.assertThat(product).isEqualTo(product1);
    }


    @Test
    public void findProductsByCategoryAndSupplierTest() {
        productRepository.save(product1);
        products = productRepository.findProductsByCategoryAndSupplier(category, supplier);
        Assertions.assertThat(products.get(0).getCategory().getId()).isEqualTo(category.getId());
        Assertions.assertThat(products.get(0).getSupplier().getId()).isEqualTo(supplier.getId());
    }
    
}
