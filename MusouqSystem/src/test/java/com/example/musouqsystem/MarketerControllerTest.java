package com.example.musouqsystem;

import com.example.musouqsystem.Controller.MarketerController;
import com.example.musouqsystem.Model.Marketer;
import com.example.musouqsystem.Model.Supplier;
import com.example.musouqsystem.Model.User;
import com.example.musouqsystem.Service.MarketerService;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(value = MarketerController.class ,excludeAutoConfiguration = {SecurityAutoConfiguration.class})
public class MarketerControllerTest {
    @MockBean
    MarketerService marketerService;

    @Autowired
    MockMvc mockMvc;

    User user1,user2,user3,user4;

    Marketer marketer1,marketer2,marketer3;

    Supplier supplier;

    List<Marketer> allMarketers;
    List<Marketer>marketersOfSupplier;


    @BeforeEach
    void setUp()
    {
       // user4=new User(4,"Aysha","Ra@123456","Aysha@gmail.com","SUPPLIER",null,null,null);
       // supplier=new Supplier(4,"Aysha","0552077202",user4,null,null,null,null,null);
       // user1=new User(1,"Rahaf","Ra@123456","rahaf@gmail.com","MARKETER",null,null,null);
       // marketer1=new Marketer(1,"Rahaf","0507358003",null,null,null,null,user1,supplier,null,null,null,null,null,null);
       // user2=new User(null,"Maram","Ra@123456","maram@gmail.com","MARKETER",null,null,null);
        marketer2=new Marketer(1,"Maram","0508358003",null,null,null,null,null,null,null,null,null,null,null,null);
        user3=new User(null,"Rana","Ra@123456","Rana@gmail.com","MARKETER",null,null,null);
        marketer3=new Marketer(2,"rana","0508858003",null,null,null,null,user3,null,null,null,null,null,null,null);

        allMarketers= Arrays.asList(marketer2);
      //  allMarketers.add(marketer2);
      //  allMarketers.add(marketer3);

        //marketersOfSupplier=Arrays.asList(marketer1);

    }

    @Test
    public void shopperGetAllMarketerTest() throws Exception
    {
        when(marketerService.shopperGetAllMarketer()).thenReturn(allMarketers);
        mockMvc.perform(get("/api/v1/marketer/shopper-get-all-marketer"))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$",Matchers.hasSize(1)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0]").value(marketer2));
    }

}
