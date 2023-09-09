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
    public ResponseEntity getAllProductsByCategory(@PathVariable Integer category_id) {
        return ResponseEntity.status(200).body(productService.getAllProductsByCategory(category_id));
    }

    @PostMapping("/supplierAddProduct/{supplier_id}/{category_id}")
    public ResponseEntity supplierAddProduct(@PathVariable Integer supplier_id, @PathVariable Integer category_id, @RequestBody @Valid Product product) {
        productService.supplierAddProduct(supplier_id, category_id, product);
        return ResponseEntity.status(200).body(new ApiResponse("product added successfully"));
    }

    @PutMapping("/marketerAddProduct/{marketer_id}/{product_id}/{supplier_id}")
    public ResponseEntity marketerAddProduct(@PathVariable Integer marketer_id, @PathVariable Integer product_id, @PathVariable Integer supplier_id) {
        productService.marketerAddProduct(marketer_id, product_id, supplier_id);
        return ResponseEntity.status(200).body(new ApiResponse("product added successfully"));
    }


    @PutMapping("/supplierUpdateProduct/{supplier_id}/{product_id}")
    public ResponseEntity supplierUpdateProduct(@PathVariable Integer supplier_id, @PathVariable Integer product_id, @RequestBody @Valid Product product) {
        productService.supplierUpdateProduct(supplier_id, product_id, product);
        return ResponseEntity.status(200).body(new ApiResponse("product updated successfully"));
    }

    @PutMapping("/marketerApplyDiscount/{marketer_id}/{product_id}/{discount}")
    public ResponseEntity marketerApplyDiscount(@PathVariable Integer marketer_id, @PathVariable Integer product_id, @PathVariable Integer discount) {
        productService.marketerApplyDiscount(marketer_id, product_id, discount);
        return ResponseEntity.status(200).body(new ApiResponse("discount applied to product successfully"));
    }

    @DeleteMapping("/supplierDeleteProduct/{supplier_id}/{product_id}")
    public ResponseEntity supplierDeleteProduct(@PathVariable Integer supplier_id, @PathVariable Integer product_id) {
        productService.supplierDeleteProduct(supplier_id, product_id);
        return ResponseEntity.status(200).body(new ApiResponse("product deleted successfully"));
    }

    @DeleteMapping("/marketerDeleteProduct/{marketer_id}/{product_id}")
    public ResponseEntity marketerDeleteProduct(@PathVariable Integer marketer_id, @PathVariable Integer product_id) {
        productService.marketerDeleteProduct(marketer_id, product_id);
        return ResponseEntity.status(200).body(new ApiResponse("product deleted successfully"));
    }

}
