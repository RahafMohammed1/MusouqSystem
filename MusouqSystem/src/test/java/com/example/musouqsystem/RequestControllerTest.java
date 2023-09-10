package com.example.musouqsystem;

import com.example.musouqsystem.Controller.MarketerController;
import com.example.musouqsystem.Controller.RequestController;
import com.example.musouqsystem.Model.Marketer;
import com.example.musouqsystem.Model.Request;
import com.example.musouqsystem.Model.User;
import com.example.musouqsystem.Service.RequestService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(value = RequestController.class ,excludeAutoConfiguration = {SecurityAutoConfiguration.class})
public class RequestControllerTest {

    @MockBean
    RequestService requestService;

    @Autowired
    MockMvc mockMvc;

    User user;
    Marketer marketer;
    Request request1,request2;

    List<Request> marketerRequests;

    @BeforeEach
    void setUp()
    {
        user=new User(null,"RahafMohammed","Ra@1234","rahaf@gmail.com","MARKETER",null,null,null);
        marketer= new Marketer(null,"Rahaf","0551078203",null,null,null,null,user,null,null,null,null,null,null,null);
        request1=new Request(1,"hi,i'm Rahaf and i'm expert marketer","pending", LocalDate.now(),marketer,null);
        request2=new Request(2,"hi,i'm Rahaf and i'm expert marketer","pending", LocalDate.now(),marketer,null);

        marketerRequests=Arrays.asList(request1);


    }

    @Test
    public void marketerViewAllHisRequestTest()throws Exception
    {
        when(requestService.marketerViewAllHisRequest(user.getId())).thenReturn(marketerRequests);
        mockMvc.perform(get("/api/v1/request/get-marketer-request"))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath( "$", Matchers.hasSize(1)));

    }

    @Test
    public void marketerSendRequestTest()throws Exception
    {
        mockMvc.perform(post("/api/v1/request/send-request")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(request1)))
                .andExpect(status().isOk());

    }

}
