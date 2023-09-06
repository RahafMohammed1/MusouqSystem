package com.example.musouqsystem.Service;

import com.example.musouqsystem.Api.ApiException;
import com.example.musouqsystem.Model.Product;
import com.example.musouqsystem.Model.Supplier;
import com.example.musouqsystem.Repository.ProductRepository;
import com.example.musouqsystem.Repository.SupplierRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;
    private final SupplierRepository supplierRepository;

//    shopper add product to order
//    supplier add product
//    marketer add product - path variable

    public void shopperAddProductToOrder(Integer shopper_id) {

    }

    public void assignSupplierToProduct(Integer supplier_id, Integer product_id){
        Supplier supplier = supplierRepository.findSupplierById(supplier_id);
        Product product = productRepository.findProductById(product_id);

        if (supplier == null || product == null) throw new ApiException("cannot assign supplier to product");

        product.setSupplier(supplier);
        productRepository.save(product);
    }
}
