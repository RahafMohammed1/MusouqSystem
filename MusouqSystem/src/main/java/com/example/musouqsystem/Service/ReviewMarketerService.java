package com.example.musouqsystem.Service;

import com.example.musouqsystem.Api.ApiException;
import com.example.musouqsystem.Model.*;
import com.example.musouqsystem.Repository.MarketerRepository;
import com.example.musouqsystem.Repository.OrdersRepository;
import com.example.musouqsystem.Repository.ReviewMarketerRepository;
import com.example.musouqsystem.Repository.ShopperRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ReviewMarketerService {
    private final ReviewMarketerRepository reviewMarketerRepository;
    private final ShopperRepository shopperRepository;
    private final MarketerRepository marketerRepository;
    private final OrdersRepository ordersRepository;


    // TODO: 9/6/2023 with security, I must change this method to show only reviews for user
    public List<ReviewMarketer> getAllReviewMarketers(){
        return reviewMarketerRepository.findAll();
    }

    public void addReviewMarketer(Integer shopper_id,Integer order_id, ReviewMarketer reviewMarketer){
        Orders orders = ordersRepository.findOrdersById(order_id);
        Shopper shopper = shopperRepository.findShopperById(shopper_id);
        Marketer marketer = marketerRepository.findMarketerById(orders.getMarketer().getId());

        if (orders == null)
            throw new ApiException("Sorry no order to rate it");
        else if (shopper == null)
            throw new ApiException("Sorry , the shopper id is wrong");

        if (orders.getShopper().getId() != shopper.getId())
            throw new ApiException("Sorry you can't rate the marketer of this order");

        if (!(orders.getOrder_status().equalsIgnoreCase("delivered")))
            throw new ApiException("You can't rate becuase the order does not delivered");


        reviewMarketer.setShopper(shopper);
        reviewMarketer.setMarketer(marketer);
        reviewMarketerRepository.save(reviewMarketer);
    }
    public void updateReviewMarketer(Integer reviewMarketer_id, ReviewMarketer reviewMarketer){
        ReviewMarketer oldReviewMarketer = reviewMarketerRepository.findReviewMarketerById(reviewMarketer_id);

        if (oldReviewMarketer == null)
            throw new ApiException("Sorry the review marketer id is wrong");

        oldReviewMarketer.setReview_marketer(reviewMarketer.getReview_marketer());
        oldReviewMarketer.setRate_marketer(reviewMarketer.getRate_marketer());
        reviewMarketerRepository.save(reviewMarketer);
    }

    public void deleteReviewMarketer(Integer reviewMarketer_id){
        ReviewMarketer reviewMarketer = reviewMarketerRepository.findReviewMarketerById(reviewMarketer_id);

        if (reviewMarketer == null)
            throw new ApiException("Sorry the review order is wrong");

        reviewMarketer.setMarketer(null);
        reviewMarketer.setShopper(null);
        reviewMarketerRepository.delete(reviewMarketer);
    }

    public Marketer calculateMarketerRate(Integer marketer_id){
        Marketer marketer = marketerRepository.findMarketerById(marketer_id);

        if (marketer == null)
            throw new ApiException("Sorry the marketer id is wrong");

        Integer result_rate = reviewMarketerRepository.calculateRateToMarketer(marketer_id);
        marketer.setMarketer_rate(result_rate);
        marketerRepository.save(marketer);

        return marketer;
    }
}
