package com.example.musouqsystem.Controller;

import com.example.musouqsystem.Api.ApiResponse;
import com.example.musouqsystem.DTO.ReviewOrderDTO;
import com.example.musouqsystem.Service.ReviewOrderService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/reviewOrder")
@RequiredArgsConstructor
public class ReviewOrderController {
    private final ReviewOrderService reviewOrderService;

    // TODO: 9/6/2023 with secuity i must update it
    @GetMapping("/get")
    public ResponseEntity getAllReviewsController() {
        return ResponseEntity.status(200).body(reviewOrderService.getAllReviews());
    }

    @PostMapping("/addReviewOrder/{shopper_id}")
    public ResponseEntity addReviewOrderController(@PathVariable Integer shopper_id,@RequestBody @Valid ReviewOrderDTO reviewOrderDTO){
        reviewOrderService.addReviewOrder(shopper_id , reviewOrderDTO);
        return ResponseEntity.status(200).body(new ApiResponse("your review order added successfully "));
    }

    @PutMapping("/updateReviewOrder/{shopper_id}")
    public ResponseEntity updateReviewOrderController(@PathVariable Integer shopper_id,@RequestBody @Valid ReviewOrderDTO reviewOrderDTO) {
        reviewOrderService.updateReviewOrder(shopper_id, reviewOrderDTO);
        return ResponseEntity.status(200).body(new ApiResponse("your review order updated successfully"));
    }

    @DeleteMapping("/deleteReviewOrder/{reviewOrder}")
    public ResponseEntity deleteReviewOrderSuccessfully(@PathVariable Integer reviewOrder) {
        reviewOrderService.deleteReviewOrder(reviewOrder);
        return ResponseEntity.status(200).body(new ApiResponse("your review order deleted successfully"));
    }
}
