package com.example.musouqsystem.Service;

import com.example.musouqsystem.Api.ApiException;
import com.example.musouqsystem.Model.*;
import com.example.musouqsystem.Repository.*;
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
    private final AuthRepository authRepository;


    // TODO: 9/6/2023 with security, I must change this method to show only reviews for user
    public List<ReviewMarketer> getAllReviewMarketers(Integer user_id){
        User user = authRepository.findUserById(user_id);
        if (user.getShopper() == null)
            throw new ApiException("Sorry you can't view your review marketer");


        return reviewMarketerRepository.findAllByShopperId(user.getShopper().getId());
    }

    public void addReviewMarketer(Integer user_id,Integer order_id, ReviewMarketer reviewMarketer){
        User user = authRepository.findUserById(user_id);
        Orders orders = ordersRepository.findOrdersById(order_id);
        if (orders == null)
            throw new ApiException("Sorry no order to rate it");
        Shopper shopper = shopperRepository.findShopperById(orders.getShopper().getId());
        Marketer marketer = marketerRepository.findMarketerById(orders.getMarketer().getId());

        if (shopper == null)
            throw new ApiException("Sorry , the shopper id is wrong");
        else if (marketer == null) {
            throw new ApiException("wrong marketer");
        }

        if (user.getShopper().getId() != shopper.getId())
            throw new ApiException("Sorry you can't add review marketer");



        if (!(orders.getOrder_status().equalsIgnoreCase("delivered")))
            throw new ApiException("You can't rate becuase the order does not delivered");

        reviewMarketer.setShopper(shopper);
        reviewMarketer.setMarketer(marketer);
        reviewMarketerRepository.save(reviewMarketer);
    }
    public void updateReviewMarketer(Integer user_id,Integer reviewMarketer_id, ReviewMarketer reviewMarketer){
        User user = authRepository.findUserById(user_id);
        ReviewMarketer oldReviewMarketer = reviewMarketerRepository.findReviewMarketerById(reviewMarketer_id);

        if (oldReviewMarketer == null)
            throw new ApiException("Sorry the review marketer id is wrong");

        if (user.getShopper().getId() != oldReviewMarketer.getShopper().getId())
            throw new ApiException("Sorry you can't update oon this review marketer");


        oldReviewMarketer.setReview_marketer(reviewMarketer.getReview_marketer());
        oldReviewMarketer.setRate_marketer(reviewMarketer.getRate_marketer());
        reviewMarketerRepository.save(oldReviewMarketer);
    }

    public void deleteReviewMarketer(Integer user_id,Integer reviewMarketer_id){
        User user = authRepository.findUserById(user_id);
        ReviewMarketer reviewMarketer = reviewMarketerRepository.findReviewMarketerById(reviewMarketer_id);

        if (reviewMarketer == null)
            throw new ApiException("Sorry the review order is wrong");
        else if (user.getShopper().getId() != reviewMarketer.getShopper().getId())
            throw new ApiException("Sorry you can't delete this review marketer");

        reviewMarketer.setShopper(null);
        reviewMarketer.setMarketer(null);
        reviewMarketerRepository.delete(reviewMarketer);
    }

    public Marketer calculateMarketerRate(Integer user_id){
        Marketer marketer = marketerRepository.findMarketerById(user_id);

        if (marketer == null)
            throw new ApiException("Sorry the marketer id is wrong");

        Integer result_rate = reviewMarketerRepository.calculateRateToMarketer(marketer.getId());
        marketer.setMarketer_rate(result_rate);
        marketerRepository.save(marketer);

        return marketer;
    }
}
