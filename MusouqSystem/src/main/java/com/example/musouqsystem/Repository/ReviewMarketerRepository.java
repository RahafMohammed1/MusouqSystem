package com.example.musouqsystem.Repository;

import com.example.musouqsystem.Model.ReviewMarketer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReviewMarketerRepository extends JpaRepository<ReviewMarketer , Integer> {

    ReviewMarketer findReviewMarketerById(Integer id);

    List<ReviewMarketer> findAllByShopperId(Integer shopper_id);

    @Query("select avg(review.rate_marketer) from ReviewMarketer review where review.marketer.id= ?1")
    Integer calculateRateToMarketer(Integer marketer_id);
}
