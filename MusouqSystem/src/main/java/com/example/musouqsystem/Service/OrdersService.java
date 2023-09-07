package com.example.musouqsystem.Service;

import com.example.musouqsystem.Api.ApiException;
import com.example.musouqsystem.DTO.ProductListDto;
import com.example.musouqsystem.Model.*;
import com.example.musouqsystem.Repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.util.ArrayList;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OrdersService {
    private final OrdersRepository ordersRepository;
    private final ShopperRepository shopperRepository;
    private final ProductRepository productRepository;
    private final ShippingCompanyRepository shippingCompanyRepository;
    private final MarketerRepository marketerRepository;
    private final CouponsRepository couponsRepository;

    // TODO: 9/6/2023 the user get his/her orders with security
    public List<Orders> getMyOrders(){
        return ordersRepository.findAll() ;
    }

    // auth by shopper
    public void ShopperAddOrder(Integer shopper_id, Integer marketer_id,Integer shippingCompany_id, ProductListDto productListDto){
        // initialize a new order
        Orders orders = new Orders();

        Shopper shopper = shopperRepository.findShopperById(shopper_id);
        Marketer marketer = marketerRepository.findMarketerById(marketer_id);
        List<Product> productList = new ArrayList<>();
        ArrayList<Integer> products = productListDto.getProducts();

        List<Product> marketerProducts = productRepository.findAllByMarketersContains(marketer);

        Double total_price = 0.0;

        if (shopper == null)
            throw new ApiException("Sorry the shopper id is wrong");

        if (marketer.getId() != shopper.getMarketer().getId())
            throw new ApiException("the wrong marketer");


        for (int i = 0; i < products.size(); i++) {
            Product checkProduct = productRepository.findProductById(products.get(i));
            if (checkProduct == null)
                throw new ApiException("Sorry the product id is wrong");

            if (marketerProducts.get(i).getId() == checkProduct.getId()){
                total_price += checkProduct.getPrice();
                productList.add(checkProduct);
            }
        }

        Double shipPrice = assignOrderToShippingCompany(orders.getId(), shippingCompany_id);

        orders.setOrder_date(LocalDate.now());
        orders.setOrder_status("processing");
        orders.setReview_status(false);
        orders.setTotal_amount((total_price+shipPrice));
        orders.getProducts().addAll(productList);

        orders.setShopper(shopper);
        orders.setSupplier(productList.get(0).getSupplier());
        orders.setMarketer(shopper.getMarketer());


        ordersRepository.save(orders);

        // count completed order
        shopper.setNum_of_orders((shopper.getNum_of_orders() + 1));
        marketer.setNumber_of_product_sold((marketer.getNumber_of_product_sold() +1));
        shopperRepository.save(shopper);
        marketerRepository.save(marketer);
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

    public Double assignOrderToShippingCompany(Integer order_id, Integer shippingCompany_id){
        ShippingCompany shippingCompany = shippingCompanyRepository.findShippingCompanyById(shippingCompany_id);
        Orders orders = ordersRepository.findOrdersById(order_id);

        if (shippingCompany == null)
            throw new ApiException("Sorry the shipping company id is wrong");
        else if (orders == null)
            throw new ApiException("Sorry the order id is wrong");


        orders.setShippingCompany(shippingCompany);
        shippingCompany.getOrders().add(orders);

        ordersRepository.save(orders);
        shippingCompanyRepository.save(shippingCompany);

        return shippingCompany.getShipping_price();
    }


    // TODO: 9/7/2023 with secuirty i need to take a user id 
    public void confirmDeliver(Integer order_id){
        Orders confirmOrder = ordersRepository.findOrdersById(order_id);
        
        if (confirmOrder == null)
            throw new ApiException("Sorry, the order id is wrong");
        if (confirmOrder.getOrder_status().equals("processing"))
            throw new ApiException("Your order need to shipping");
        if (confirmOrder.getOrder_status().equals("delivered"))
            throw new ApiException("Your order already delivered");
        
        confirmOrder.setOrder_status("delivered");
        
        ordersRepository.save(confirmOrder);
    }
}
