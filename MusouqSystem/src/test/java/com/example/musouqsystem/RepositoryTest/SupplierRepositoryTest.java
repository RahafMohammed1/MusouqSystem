package com.example.musouqsystem.RepositoryTest;


import com.example.musouqsystem.Model.Supplier;
import com.example.musouqsystem.Model.User;
import com.example.musouqsystem.Repository.SupplierRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class SupplierRepositoryTest {

    @Autowired
    SupplierRepository supplierRepository;

    User user;
    Supplier supplier;

    @BeforeEach
    void setUp() {
        user = new User(null, "amal123", "Amal_1234", "example@gmail.com", "SUPPLIER", null, null,null);
        supplier = new Supplier(null, "amal", "0505221256", user, null, null, null, null, null);
    }


    @Test
    public void findSupplierByIdTest() {
        supplierRepository.save(supplier);
        Supplier supplier1 = supplierRepository.findSupplierById(supplier.getId());
        Assertions.assertThat(supplier1).isEqualTo(supplier);
    }
}
