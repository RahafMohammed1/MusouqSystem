package com.example.musouqsystem.Controller;

import com.example.musouqsystem.Api.ApiResponse;
import com.example.musouqsystem.Model.Product;
import com.example.musouqsystem.Model.User;
import com.example.musouqsystem.Service.ProductService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;


@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/product")
public class ProductController {
    private final ProductService productService;

    @GetMapping("/getAllProductsOfSupplier")
    public ResponseEntity getAllProductsOfSupplier(@AuthenticationPrincipal User user) {
        return ResponseEntity.status(200).body(productService.getAllProductsOfSupplier(user.getId()));
    }

    @GetMapping("/marketerGetAllProductsOfSupplier")
    public ResponseEntity marketerGetAllProductsOfSupplier(@AuthenticationPrincipal User user) {
        return ResponseEntity.status(200).body(productService.marketerGetAllProductsOfSupplier(user.getId()));
    }

    @GetMapping("/getAllProductsOfMarketer")
    public ResponseEntity getAllProductsOfMarketer(@AuthenticationPrincipal User user) {
        return ResponseEntity.status(200).body(productService.getAllProductsOfMarketer(user.getId()));
    }


    @GetMapping("/getAllProductsByCategory/{category_id}")
    public ResponseEntity getAllProductsByCategory(@AuthenticationPrincipal User user, @PathVariable Integer category_id) {
        return ResponseEntity.status(200).body(productService.getAllProductsByCategory(user.getId(), category_id));
    }

    @PostMapping("/supplierAddProduct/{category_id}")
    public ResponseEntity supplierAddProduct(@AuthenticationPrincipal User user, @PathVariable Integer category_id, @RequestBody @Valid Product product) {
        productService.supplierAddProduct(user.getId(), category_id, product);
        return ResponseEntity.status(200).body(new ApiResponse("product added successfully"));
    }

    @PutMapping("/marketerAddProduct/{product_id}")
    public ResponseEntity marketerAddProduct(@AuthenticationPrincipal User user, @PathVariable Integer product_id) {
        productService.marketerAddProduct(user.getId(), product_id);
        return ResponseEntity.status(200).body(new ApiResponse("product added successfully"));
    }


    @PutMapping("/supplierUpdateProduct/{product_id}")
    public ResponseEntity supplierUpdateProduct(@AuthenticationPrincipal User user, @PathVariable Integer product_id, @RequestBody @Valid Product product) {
        productService.supplierUpdateProduct(user.getId(), product_id, product);
        return ResponseEntity.status(200).body(new ApiResponse("product updated successfully"));
    }

    @PutMapping("/marketerApplyDiscount/{product_id}/{discount}")
    public ResponseEntity marketerApplyDiscount(@AuthenticationPrincipal User user, @PathVariable Integer product_id, @PathVariable Integer discount) {
        productService.marketerApplyDiscount(user.getId(), product_id, discount);
        return ResponseEntity.status(200).body(new ApiResponse("discount applied to product successfully"));
    }

    @DeleteMapping("/supplierDeleteProduct/{product_id}")
    public ResponseEntity supplierDeleteProduct(@AuthenticationPrincipal User user, @PathVariable Integer product_id) {
        productService.supplierDeleteProduct(user.getId(), product_id);
        return ResponseEntity.status(200).body(new ApiResponse("product deleted successfully"));
    }

    @DeleteMapping("/marketerDeleteProduct/{product_id}")
    public ResponseEntity marketerDeleteProduct(@AuthenticationPrincipal User user, @PathVariable Integer product_id) {
        productService.marketerDeleteProduct(user.getId(), product_id);
        return ResponseEntity.status(200).body(new ApiResponse("product deleted successfully"));
    }

}
