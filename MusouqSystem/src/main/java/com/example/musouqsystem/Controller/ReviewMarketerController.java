package com.example.musouqsystem.Controller;

import com.example.musouqsystem.Api.ApiResponse;
import com.example.musouqsystem.DTO.ReviewOrderDTO;
import com.example.musouqsystem.Model.ReviewMarketer;
import com.example.musouqsystem.Model.User;
import com.example.musouqsystem.Service.ReviewMarketerService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/reviewMarketer")
@RequiredArgsConstructor
public class ReviewMarketerController {
    private final ReviewMarketerService reviewMarketerService;
    @GetMapping("/get")
    public ResponseEntity getAllReviewsController(@AuthenticationPrincipal User user) {
        return ResponseEntity.status(200).body(reviewMarketerService.getAllReviewMarketers(user.getId()));
    }

    @PostMapping("/addReviewMarketer/{order_id}")
    public ResponseEntity addReviewMarketerController(@AuthenticationPrincipal User user ,@PathVariable Integer order_id,@RequestBody @Valid ReviewMarketer reviewMarketer){
        reviewMarketerService.addReviewMarketer(user.getId(), order_id, reviewMarketer);
        return ResponseEntity.status(200).body(new ApiResponse("your review marketer added successfully "));
    }

    @PutMapping("/updateReviewMarketer/{reviewMarketer_id}")
    public ResponseEntity updateReviewMarketerController(@AuthenticationPrincipal User user,@PathVariable Integer reviewMarketer_id,@RequestBody @Valid ReviewMarketer reviewMarketer) {
        reviewMarketerService.updateReviewMarketer(user.getId(),reviewMarketer_id,reviewMarketer);
        return ResponseEntity.status(200).body(new ApiResponse("your review marketer updated successfully"));
    }

    @DeleteMapping("/deleteReviewMarketer/{reviewMarketer}")
    public ResponseEntity deleteReviewOrderSuccessfully(@AuthenticationPrincipal User user, @PathVariable Integer reviewMarketer) {
        reviewMarketerService.deleteReviewMarketer(user.getId(),reviewMarketer);
        return ResponseEntity.status(200).body(new ApiResponse("your review marketer deleted successfully"));
    }


    @PutMapping("/rateMarketer")
    public ResponseEntity calculateMarketerRateController(@AuthenticationPrincipal User user){
        return ResponseEntity.status(200).body(reviewMarketerService.calculateMarketerRate(user.getId()));
    }
}
