package com.example.musouqsystem.RepositoryTest;

import com.example.musouqsystem.Model.Marketer;
import com.example.musouqsystem.Model.Shopper;
import com.example.musouqsystem.Model.User;
import com.example.musouqsystem.Repository.MarketerRepository;
import com.example.musouqsystem.Repository.ShopperRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

@ExtendWith(SpringExtension.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class ShopperRepositoryTest {
    @Autowired
    ShopperRepository shopperRepository;

    @Autowired
    MarketerRepository marketerRepository;
    User user1, user2, user3;
    Shopper shopper1, shopper2;
    List<Shopper> shoppers;
    Marketer marketer;

    @BeforeEach
    void setUp() {
        user1 = new User(null, "amal123", "Amal_1234", "example@gmail.com", "MARKETER", null, null,null);
        user2 = new User(null, "reham12", "Reham_1234", "reham@outlook.com", "SHOPPER", null, null, null);
        user3 = new User(null, "rehaf12", "Rahaf_1234", "rahaf12@outlook.com", "SHOPPER", null, null, null);

        marketer = new Marketer(null, "amal", "0590654378", null, null, null, null, user1, null, null, null, null, null, null, null);
        shopper1 = new Shopper(null, "reham", "0590654378", "Saudi arabia, Riyadh, Alnuda", 0, user2, null, null, null, null, marketer);
        shopper2 = new Shopper(null, "rahaf", "0590654378", "Saudi arabia, Riyadh, an narjis", 0, user3, null, null, null, null, marketer);

    }

    @Test
    public void findShoppersByMarketerIdTest() {
        marketerRepository.save(marketer);
        shopperRepository.save(shopper1);
        shopperRepository.save(shopper2);

        shoppers = shopperRepository.findShoppersByMarketerId(marketer.getId());
        Assertions.assertThat(shoppers.get(0).getMarketer().getId()).isEqualTo(shopper1.getMarketer().getId());

    }
}
