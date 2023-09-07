package com.example.musouqsystem.Controller;

import com.example.musouqsystem.Api.ApiResponse;
import com.example.musouqsystem.DTO.ImageDTO;
import com.example.musouqsystem.Model.Image;
import com.example.musouqsystem.Service.ImageService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/image")
public class ImageController {
    private final ImageService imageService;

    @GetMapping("/getAllImage")
    public ResponseEntity getAllImage() {
        return ResponseEntity.status(200).body(imageService.getAllImage());
    }

    @PostMapping("/addImage/{product_id}")
    public ResponseEntity addImage(@PathVariable Integer product_id, @RequestBody @Valid ImageDTO imageDTO) {
        imageService.addImage(product_id, imageDTO);
        return ResponseEntity.status(200).body(new ApiResponse("image added successfully"));
    }

    @PutMapping("/changeImage/{supplier_id}/{product_id}")
    public ResponseEntity changeImage(@PathVariable Integer supplier_id, @PathVariable Integer product_id, @RequestBody @Valid Image image) {
        imageService.changeImage(supplier_id, product_id, image);
        return ResponseEntity.status(200).body(new ApiResponse("image changed successfully"));

    }

    @DeleteMapping("/deleteImage/{supplier_id}/{product_id}")
    public ResponseEntity deleteImage(@PathVariable Integer supplier_id, @PathVariable Integer product_id) {
        imageService.deleteImage(supplier_id, product_id);
        return ResponseEntity.status(200).body(new ApiResponse("image deleted successfully"));
    }
}
