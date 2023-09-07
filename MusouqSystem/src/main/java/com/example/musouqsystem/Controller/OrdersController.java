package com.example.musouqsystem.Controller;

import com.example.musouqsystem.Api.ApiResponse;
import com.example.musouqsystem.DTO.ProductListDto;
import com.example.musouqsystem.Model.Orders;
import com.example.musouqsystem.Service.OrdersService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("/api/v1/order")
@RequiredArgsConstructor
public class OrdersController {
    private final OrdersService ordersService;

    @GetMapping("/get")
    public ResponseEntity getAllOrders(){
        return ResponseEntity.status(200).body(ordersService.getMyOrders());
    }

    @PostMapping("/makeOrder")
    public ResponseEntity shopperMakeOrder(){
        ordersService.ShopperMakeOrder(new Orders());
        return ResponseEntity.status(200).body(new ApiResponse("You created the order successfully"));
    }

    @DeleteMapping("/deleteOrder/{order_id}")
    public ResponseEntity deleteOrderController(@PathVariable Integer order_id){
        ordersService.deleteOrder(order_id);
        return ResponseEntity.status(200).body(new ApiResponse("The ordered deleted successfully"));

    }
}
