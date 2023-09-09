package com.example.musouqsystem.Controller;


import com.example.musouqsystem.Api.ApiException;
import com.example.musouqsystem.Api.ApiResponse;
import com.example.musouqsystem.Model.Category;
import com.example.musouqsystem.Model.User;
import com.example.musouqsystem.Service.CategoryService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/category")
public class CategoryController {
    private final CategoryService categoryService;

    @GetMapping("/getAllCategories")
    public ResponseEntity supplierGetAllCategories() {
        return ResponseEntity.status(200).body(categoryService.supplierGetAllCategories());
    }

    @PostMapping("/addCategory")
    public ResponseEntity addCategory(@RequestBody @Valid Category category) {
        categoryService.addCategory(category);
        return ResponseEntity.status(200).body(new ApiResponse("category added successfully"));
    }


    @PutMapping("/updateCategory/{category_id}")
    public ResponseEntity updateCategory(@PathVariable  Integer category_id, @RequestBody @Valid Category category) {
        categoryService.updateCategory(category_id, category);
        return ResponseEntity.status(200).body(new ApiResponse("category updated successfully"));
    }

    @PutMapping("/updatePercent/{category_id}/{percent}")
    public ResponseEntity supplierUpdateMarketerPercent(@PathVariable Integer category_id, @PathVariable Double percent) {
        categoryService.supplierUpdateMarketerPercent(category_id, percent);
        return ResponseEntity.status(200).body(new ApiResponse("category marketer percent updated successfully"));
    }

    @DeleteMapping("/deleteCategory/{category_id}")
    public ResponseEntity deleteCategory(@PathVariable Integer category_id) {
        categoryService.deleteCategory(category_id);
        return ResponseEntity.status(200).body(new ApiResponse("category deleted successfully"));
    }
}
