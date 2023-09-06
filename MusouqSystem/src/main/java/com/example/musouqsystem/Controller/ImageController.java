package com.example.musouqsystem.Controller;

import com.example.musouqsystem.Api.ApiResponse;
import com.example.musouqsystem.DTO.ImageDTO;
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

    @PutMapping("/changeImage/{img_id}")
    public ResponseEntity changeImage(@PathVariable Integer img_id, @RequestBody @Valid ImageDTO imageDTO) {
        imageService.changeImage(img_id, imageDTO);
        return ResponseEntity.status(200).body(new ApiResponse("image added successfully"));

    }

    @DeleteMapping("/deleteImage/{img_id}")
    public ResponseEntity deleteImage(@PathVariable Integer img_id) {
        imageService.deleteImage(img_id);
        return ResponseEntity.status(200).body(new ApiResponse("image added successfully"));
    }
}
