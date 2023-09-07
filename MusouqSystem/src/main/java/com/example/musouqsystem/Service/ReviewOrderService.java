package com.example.musouqsystem.Service;

import com.example.musouqsystem.Api.ApiException;
import com.example.musouqsystem.DTO.ReviewOrderDTO;
import com.example.musouqsystem.Model.Orders;
import com.example.musouqsystem.Model.ReviewOrder;
import com.example.musouqsystem.Model.Shopper;
import com.example.musouqsystem.Repository.OrdersRepository;
import com.example.musouqsystem.Repository.ReviewOrderRepository;
import com.example.musouqsystem.Repository.ShopperRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ReviewOrderService {
    private final ReviewOrderRepository reviewOrderRepository;
    private final OrdersRepository ordersRepository;
    private final ShopperRepository shopperRepository;

    // TODO: 9/6/2023 with security, I must change this method to show only reviews for user
    public List<ReviewOrder> getAllReviews(){
        return reviewOrderRepository.findAll();
    }

    public void addReviewOrder(Integer shopper_id,ReviewOrderDTO reviewOrderDTO){
        Orders orders = ordersRepository.findOrdersById(reviewOrderDTO.getOrder_id());
        Shopper shopper = shopperRepository.findShopperById(shopper_id);

        if (orders == null)
            throw new ApiException("Sorry no order to rate it");
        else if (shopper == null)
            throw new ApiException("Sorry , the shopper id is wrong");

        if (orders.getReview_status())
            throw new ApiException("You already rate this order");
        else if (!(orders.getOrder_status().equals("delivered")))
            throw new ApiException("You can't review until the order delivered to you ");

        ReviewOrder reviewOrder = new ReviewOrder(null, reviewOrderDTO.getReview_order(), reviewOrderDTO.getRate_order(),shopper,orders);
        orders.setReview_status(true);
        ordersRepository.save(orders);
        reviewOrderRepository.save(reviewOrder);
    }
    public void updateReviewOrder( ReviewOrderDTO reviewOrderDTO){
        Orders orders = ordersRepository.findOrdersById(reviewOrderDTO.getOrder_id());
        if (orders == null)
            throw new ApiException("Sorry the review is wrong");

        Shopper shopper = shopperRepository.findShopperById(orders.getShopper().getId());
        if (shopper == null)
            throw new ApiException("Sorry the shopper id is wrong");

        ReviewOrder updateReview = new ReviewOrder(reviewOrderDTO.getOrder_id(), reviewOrderDTO.getReview_order(),reviewOrderDTO.getRate_order(),shopper, orders);

        reviewOrderRepository.save(updateReview);
    }

    public void deleteReviewOrder(Integer reviewOrder_id){
        Orders review_order_deleted = ordersRepository.findOrdersById(reviewOrder_id);

        if (review_order_deleted == null)
            throw new ApiException("Sorry the review order is wrong");
        reviewOrderRepository.delete(review_order_deleted.getReviewOrder());
    }
}
