package com.example.musouqsystem.Service;

import com.example.musouqsystem.Api.ApiException;
import com.example.musouqsystem.Model.*;
import com.example.musouqsystem.Repository.*;
import lombok.RequiredArgsConstructor;
import org.aspectj.weaver.ast.Or;
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
    public void ShopperMakeOrder(Orders orders){
    ordersRepository.save(orders);
    }
    public void ShopperAddProductToOrder(Integer shopper_id, Integer product_id,Integer order_id ) {
        Shopper shopper = shopperRepository.findShopperById(shopper_id);
        Product product = productRepository.findProductById(product_id);
        Orders orders = ordersRepository.findOrdersById(order_id);


        if (shopper == null)
            throw new ApiException("shopper id is wrong");
        else if (product == null)
            throw new ApiException("product id is wrong");
        else if (orders == null)
            throw new ApiException("order id is wrong");

        if ((orders.getOrder_status() != null))
            throw new ApiException("Sorry you can't add product beacuse the order status changed, make a new order to add a products");


        if (shopper.getMarketer() == null)
            throw new ApiException("sorry you must select the marketer first");

        if (product.getStock() == 0)
            throw new ApiException("sorry the product '"+product.getName() +"' out of the stock");

        if (shopper.getMarketer().getProducts().contains(product)){
            orders.getProducts().add(product);
            product.setOrders(orders);
            orders.setShopper(shopper);
            orders.setSupplier(product.getSupplier());
            orders.setMarketer(shopper.getMarketer());
            product.setStock(product.getStock() -1);
            ordersRepository.save(orders);
        }else throw new ApiException("Sorry the product id does not match the prodcut in marketer store");
    }
    public Double calculateProductsAmount(Integer order_id, Integer product_id){
        Orders orders = ordersRepository.findOrdersById(order_id);
        Product product = productRepository.findProductById(product_id);
        Double total = 0.0;

        if (orders == null)
            throw new ApiException("order id is wrong");
        else if (product == null){
            throw new ApiException("product id is wrong");
        }

        if (orders.getProducts().isEmpty())
            throw new ApiException("You must add product to calculate amount");

        if (!(orders.getShopper().getMarketer().getProducts().contains(product)))
            throw new ApiException("Sorry the product not found in the marketer store");

        if (orders.getTotal_amount() == null)
            orders.setTotal_amount(0.0);

        if (product.getPrice_after_discount() != null) {
            total = orders.getTotal_amount().doubleValue() + product.getPrice_after_discount();
        }else {
            total = orders.getTotal_amount() .doubleValue()+ product.getPrice();
        }
        orders.setTotal_amount(total);
        ordersRepository.save(orders);
        return total;
    }

    public Double selectShippingCompany(Integer order_id, Integer shippingCompany_id){
        ShippingCompany shippingCompany = shippingCompanyRepository.findShippingCompanyById(shippingCompany_id);
        Orders orders = ordersRepository.findOrdersById(order_id);

        if (shippingCompany == null)
            throw new ApiException("Sorry the shipping company id is wrong");
        else if (orders == null)
            throw new ApiException("Sorry the order id is wrong");

        orders.setShippingCompany(shippingCompany);

        if (orders.getTotal_amount() == null)
            orders.setTotal_amount(0.0);

        Double total = orders.getTotal_amount() + shippingCompany.getShipping_price();
        orders.setTotal_amount(total);

        ordersRepository.save(orders);
        shippingCompanyRepository.save(shippingCompany);

        return total;
    }

    public Orders completeOrder(Integer order_id) {
        Orders orders = ordersRepository.findOrdersById(order_id);
        ShippingCompany shippingCompany = shippingCompanyRepository.findShippingCompanyById(orders.getShippingCompany().getId());
        Shopper shopper = shopperRepository.findShopperById(orders.getShopper().getId());
        Marketer marketer = marketerRepository.findMarketerById(orders.getMarketer().getId());

        if (orders == null)
            throw new ApiException("Sorry, the order id is wrong");

        if (shippingCompany == null)
            throw new ApiException("Sorry you must choose the shipping company to complete the order");
        if (orders.getProducts().isEmpty())
            throw new ApiException("You must add product to complete the order");


        orders.setOrder_date(LocalDate.now());
        orders.setOrder_status("processing");
        orders.setReview_status(false);

        if (shopper.getNum_of_orders() == null)
            shopper.setNum_of_orders(0);

        if (marketer.getNumber_of_product_sold() == null)
            marketer.setNumber_of_product_sold(0);

        shopper.setNum_of_orders((shopper.getNum_of_orders() + 1));
        marketer.setNumber_of_product_sold((marketer.getNumber_of_product_sold() + 1));

        ordersRepository.save(orders);
        shopperRepository.save(shopper);
        marketerRepository.save(marketer);
        return orders;

    }

    public void deliveredOrder(Integer order_id){
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


    // auth by shopper
//    public void ShopperAddOrder(Integer shopper_id, Integer marketer_id,Integer shippingCompany_id,ProductListDto productListDto){
//
//        // initialize a new order
//        Orders orders = new Orders();
//
//        Shopper shopper = shopperRepository.findShopperById(shopper_id);
//        Marketer marketer = marketerRepository.findMarketerById(marketer_id);
//        List<Product> productList = new ArrayList<>();
//        ArrayList<Integer> products = productListDto.getProducts();
//
//        List<Product> marketerProducts = productRepository.findAllByMarketersContains(marketer);
//
//        Double total_price = 0.0;
//
//        if (shopper == null)
//            throw new ApiException("Sorry the shopper id is wrong");
//
//        if (marketer.getId() != shopper.getMarketer().getId())
//            throw new ApiException("the wrong marketer");
//
//
//        for (int i = 0; i < products.size(); i++) {
//            Product checkProduct = productRepository.findProductById(products.get(i));
//            if (checkProduct == null)
//                throw new ApiException("Sorry the product id is wrong");
//
//            if (marketerProducts.get(i).getId() == checkProduct.getId()){
//                total_price += checkProduct.getPrice();
//                productList.add(checkProduct);
//            }
//        }
//
//        Double shipPrice = assignOrderToShippingCompany(orders.getId(), shippingCompany_id);
//
//        orders.setOrder_date(LocalDate.now());
//        orders.setOrder_status("processing");
//        orders.setReview_status(false);
//        orders.setTotal_amount((total_price+shipPrice));
//        orders.getProducts().addAll(productList);
//
//        orders.setShopper(shopper);
//        orders.setSupplier(productList.get(0).getSupplier());
//        orders.setMarketer(shopper.getMarketer());
//
//
//        ordersRepository.save(orders);
//
//        // count completed order
//        shopper.setNum_of_orders((shopper.getNum_of_orders() + 1));
//        marketer.setNumber_of_product_sold((marketer.getNumber_of_product_sold() +1));
//        shopperRepository.save(shopper);
//        marketerRepository.save(marketer);
//    }



    public void deleteOrder(Integer order_id){
        Orders deleteOrder = ordersRepository.findOrdersById(order_id);

        if (deleteOrder == null)
            throw new ApiException("Sorry the order is is wrong");

        if (deleteOrder.getOrder_status()!= null)
            throw new ApiException("Sorry, you can't cancel your order");

        ordersRepository.delete(deleteOrder);
    }


}
