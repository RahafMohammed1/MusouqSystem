package com.example.musouqsystem.Service;

import com.example.musouqsystem.Api.ApiException;
import com.example.musouqsystem.Model.Orders;
import com.example.musouqsystem.Model.Product;
import com.example.musouqsystem.Model.Shopper;
import com.example.musouqsystem.Repository.OrdersRepository;
import com.example.musouqsystem.Repository.ProductRepository;
import com.example.musouqsystem.Repository.ShopperRepository;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OrdersService {
    private final OrdersRepository ordersRepository;
    private final ShopperRepository shopperRepository;
    private final ProductRepository productRepository;

    // TODO: 9/6/2023 the user get his/her orders with security
    public List<Orders> getMyOrders(){
        return ordersRepository.findAll() ;
    }

    // auth by shopper
    public void ShopperAddOrder(Integer shopper_id, Orders orders, ArrayList<Integer> products){
        Shopper shopper = shopperRepository.findShopperById(shopper_id);
        List<Product> productList = new ArrayList<>();
        Double total_amount = 0.0;

        if (shopper == null)
            throw new ApiException("Sorry the shopper id is wrong");

        for (int i = 0; i < products.size(); i++) {
            Product checkProduct = productRepository.findProductById(products.get(i));
            if (checkProduct == null)
                throw new ApiException("Sorry the product id is wrong");

            total_amount += checkProduct.getPrice();
            productList.add(checkProduct);
        }
        orders.setOrder_date(LocalDate.now());
        orders.setOrder_status("processing");
        orders.setShopper(shopper);
        orders.setReview_status(false);
        orders.setTotal_amount(total_amount);
        orders.getProducts().addAll(productList);
        orders.setShopper(shopper);
        orders.setSupplier(productList.get(0).getSupplier());
        ordersRepository.save(orders);
    }

    public void deleteOrder(Integer order_id){
        Orders deleteOrder = ordersRepository.findOrdersById(order_id);

        if (deleteOrder == null)
            throw new ApiException("Sorry the order is is wrong");

        if (!(deleteOrder.getOrder_status().equalsIgnoreCase("processing")))
            throw new ApiException("Sorry, you can't cancel your order");

        deleteOrder.setShopper(null);
        deleteOrder.setReviewOrder(null);
        deleteOrder.setSupplier(null);

        ordersRepository.delete(deleteOrder);
    }
}
