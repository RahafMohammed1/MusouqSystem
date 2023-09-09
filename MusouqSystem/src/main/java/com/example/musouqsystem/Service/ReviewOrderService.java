package com.example.musouqsystem.Service;

import com.example.musouqsystem.Api.ApiException;
import com.example.musouqsystem.DTO.ReviewOrderDTO;
import com.example.musouqsystem.Model.Orders;
import com.example.musouqsystem.Model.ReviewOrder;
import com.example.musouqsystem.Model.Shopper;
import com.example.musouqsystem.Model.User;
import com.example.musouqsystem.Repository.AuthRepository;
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
    private final AuthRepository authRepository;

    // TODO: 9/6/2023 with security, I must change this method to show only reviews for user
    public List<ReviewOrder> getAllReviews(Integer user_id){
        User user = authRepository.findUserById(user_id);
        if (user.getShopper() == null)
            throw new ApiException("Sorry you can't view your reviews orders");

        return reviewOrderRepository.findAllByShopperId(user.getShopper().getId());
    }

    public void addReviewOrder(Integer user_id,ReviewOrderDTO reviewOrderDTO){
        Orders orders = ordersRepository.findOrdersById(reviewOrderDTO.getOrder_id());
        Shopper shopper = shopperRepository.findShopperById(user_id);

        if (orders == null)
            throw new ApiException("Sorry no order to rate it");
        else if (shopper == null)
            throw new ApiException("Sorry , the shopper id is wrong");
        if (!(shopper.getOrders().contains(orders)))
            throw new ApiException("Sorry you can't write review for this order");

        if (orders.getReview_status())
            throw new ApiException("You already rate this order");
        else if (!(orders.getOrder_status().equals("delivered")))
            throw new ApiException("You can't review until the order delivered to you ");

        ReviewOrder reviewOrder = new ReviewOrder(null, reviewOrderDTO.getReview_order(), reviewOrderDTO.getRate_order(),shopper,orders);
        orders.setReview_status(true);
        ordersRepository.save(orders);
        reviewOrderRepository.save(reviewOrder);
    }
    public void updateReviewOrder(Integer user_id, ReviewOrderDTO reviewOrderDTO){
        User user = authRepository.findUserById(user_id);
        Orders orders = ordersRepository.findOrdersById(reviewOrderDTO.getOrder_id());
        if (orders == null)
            throw new ApiException("Sorry the review is wrong");
        else if (user.getShopper().getId() != orders.getShopper().getId()) {
            throw new ApiException("Sorry you can't update this review");
        }
        Shopper shopper = shopperRepository.findShopperById(orders.getShopper().getId());
        if (shopper == null)
            throw new ApiException("Sorry can't update reviews until create order and delivered");

        ReviewOrder updateReview = new ReviewOrder(reviewOrderDTO.getOrder_id(), reviewOrderDTO.getReview_order(),reviewOrderDTO.getRate_order(),shopper, orders);

        reviewOrderRepository.save(updateReview);
    }

    public void deleteReviewOrder(Integer user_id,Integer reviewOrder_id){
        User user = authRepository.findUserById(user_id);
        Orders review_order_deleted = ordersRepository.findOrdersById(reviewOrder_id);

        if (review_order_deleted == null)
            throw new ApiException("Sorry the review order is wrong");
        else if (user.getShopper().getId() != review_order_deleted.getShopper().getId())
            throw new ApiException("Sorry you can't delete this review order");

        reviewOrderRepository.delete(review_order_deleted.getReviewOrder());
    }
}
