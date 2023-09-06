package com.example.musouqsystem.Controller;

import com.example.musouqsystem.Api.ApiResponse;
import com.example.musouqsystem.DTO.ReviewOrderDTO;
import com.example.musouqsystem.Model.ReviewMarketer;
import com.example.musouqsystem.Service.ReviewMarketerService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/reviewMarketer")
@RequiredArgsConstructor
public class ReviewMarketerController {
    private final ReviewMarketerService reviewMarketerService;
    @GetMapping("/get")
    public ResponseEntity getAllReviewsController() {
        return ResponseEntity.status(200).body(reviewMarketerService.getAllReviewMarketers());
    }

    @PostMapping("/addReviewMarketer/{shopper_id}/{order_id}")
    public ResponseEntity addReviewMarketerController(@PathVariable Integer shopper_id, @PathVariable Integer order_id,@RequestBody @Valid ReviewMarketer reviewMarketer){
        reviewMarketerService.addReviewMarketer(shopper_id,order_id, reviewMarketer);
        return ResponseEntity.status(200).body(new ApiResponse("your review marketer added successfully "));
    }

    @PutMapping("/updateReviewMarketer/{reviewMarketer_id}")
    public ResponseEntity updateReviewMarketerController(@PathVariable Integer reviewMarketer_id,@RequestBody @Valid ReviewMarketer reviewMarketer) {
        reviewMarketerService.updateReviewMarketer(reviewMarketer_id, reviewMarketer);
        return ResponseEntity.status(200).body(new ApiResponse("your review marketer updated successfully"));
    }

    @DeleteMapping("/deleteReviewMarketer/{reviewMarketer}")
    public ResponseEntity deleteReviewOrderSuccessfully(@PathVariable Integer reviewMarketer) {
        reviewMarketerService.deleteReviewMarketer(reviewMarketer);
        return ResponseEntity.status(200).body(new ApiResponse("your review marketer deleted successfully"));
    }
}
