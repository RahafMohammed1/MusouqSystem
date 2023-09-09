package com.example.musouqsystem.Controller;

import com.example.musouqsystem.Api.ApiResponse;
import com.example.musouqsystem.DTO.ReviewOrderDTO;
import com.example.musouqsystem.Model.User;
import com.example.musouqsystem.Service.ReviewOrderService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/reviewOrder")
@RequiredArgsConstructor
public class ReviewOrderController {
    private final ReviewOrderService reviewOrderService;

    // TODO: 9/6/2023 with secuity i must update it
    @GetMapping("/get")
    public ResponseEntity getAllReviewsController(@AuthenticationPrincipal User user) {
        return ResponseEntity.status(200).body(reviewOrderService.getAllReviews(user.getId()));
    }

    @PostMapping("/addReviewOrder")
    public ResponseEntity addReviewOrderController(@AuthenticationPrincipal User user,@RequestBody @Valid ReviewOrderDTO reviewOrderDTO){
        reviewOrderService.addReviewOrder(user.getId(), reviewOrderDTO);
        return ResponseEntity.status(200).body(new ApiResponse("your review order added successfully "));
    }

    @PutMapping("/updateReviewOrder")
    public ResponseEntity updateReviewOrderController(@AuthenticationPrincipal User user,@RequestBody @Valid ReviewOrderDTO reviewOrderDTO) {
        reviewOrderService.updateReviewOrder(user.getId(),reviewOrderDTO);
        return ResponseEntity.status(200).body(new ApiResponse("your review order updated successfully"));
    }

    @DeleteMapping("/deleteReviewOrder/{reviewOrder}")
    public ResponseEntity deleteReviewOrderSuccessfully(@AuthenticationPrincipal User user, @PathVariable Integer reviewOrder) {
        reviewOrderService.deleteReviewOrder(user.getId(),reviewOrder);
        return ResponseEntity.status(200).body(new ApiResponse("your review order deleted successfully"));
    }
}
