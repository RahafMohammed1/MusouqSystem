package com.example.musouqsystem.Repository;

import com.example.musouqsystem.Model.ReviewOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReviewOrderRepository extends JpaRepository<ReviewOrder, Integer> {

    ReviewOrder findReviewOrderById(Integer id);
}
