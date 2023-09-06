package com.example.musouqsystem.Repository;

import com.example.musouqsystem.Model.ReviewMarketer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReviewMarketerRepository extends JpaRepository<ReviewMarketer , Integer> {

    ReviewMarketer findReviewMarketerById(Integer id);
}
