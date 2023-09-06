package com.example.musouqsystem.Controller;

import com.example.musouqsystem.Api.ApiResponse;
import com.example.musouqsystem.Model.Orders;
import com.example.musouqsystem.Service.OrdersService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/order")
@RequiredArgsConstructor
public class OrdersController {
    private final OrdersService ordersService;

//    @PostMapping("/addOrder/{shopper_id}/{product_1}/{product_2}")
//    public ResponseEntity shopperAddOrderController(@PathVariable Integer shopper_id, @PathVariable Integer product_1 , @PathVariable Integer product_2 , @RequestBody @Valid Orders orders){
//        ordersService.ShopperAddOrder(shopper_id, product_1, product_2, orders);
//        return ResponseEntity.status(200).body(new ApiResponse("The ordered created successfully"));
//    }
}
