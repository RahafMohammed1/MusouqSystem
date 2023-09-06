package com.example.musouqsystem.Service;

import com.example.musouqsystem.Api.ApiException;
import com.example.musouqsystem.Model.*;
import com.example.musouqsystem.Repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;
    private final SupplierRepository supplierRepository;
    private final ShopperRepository shopperRepository;
    private final OrdersRepository ordersRepository;
    private final MarketerRepository marketerRepository;


//    marketer & supplier
    public List<Product> getAllProductsOfSupplier(Integer supplier_id) {
        return productRepository.findProductsBySupplierId(supplier_id);
    }

//    shopper
//    public List<Product> getAllProductsOfMarketer(Integer marketer_id) {
//        return productRepository.findProductsByMarketerId(marketer_id);
//    }

//    marketer add product - path variable

    public void supplierAddProduct(Integer supplier_id, Product product) {
        Supplier supplier = supplierRepository.findSupplierById(supplier_id);

        if (supplier == null) throw new ApiException("supplier not exist");

        product.setSupplier(supplier);
        productRepository.save(product);
    }

    public void marketerAddProduct(Integer marketer_id, Integer product_id, Integer supplier_id ) {
        Marketer marketer = marketerRepository.findMarketerById(marketer_id);
        Product product = productRepository.findProductById(product_id);
        Supplier supplier = supplierRepository.findSupplierById(supplier_id);

        if (marketer == null || product == null || supplier == null) throw new ApiException("cannot add product to marketer");

    }

    public void shopperAddProductToOrder(Integer shopper_id,Integer product_id, Integer order_id) {
        Shopper shopper = shopperRepository.findShopperById(shopper_id);
        Product product = productRepository.findProductById(product_id);
        Orders order = ordersRepository.findOrdersById(order_id);

        if (shopper == null || product == null || order == null) throw new ApiException("cannot add product to order");

        product.setOrders(order);
        productRepository.save(product);
    }



    public void assignSupplierToProduct(Integer supplier_id, Integer product_id){
        Supplier supplier = supplierRepository.findSupplierById(supplier_id);
        Product product = productRepository.findProductById(product_id);

        if (supplier == null || product == null) throw new ApiException("cannot assign supplier to product");

        product.setSupplier(supplier);
        productRepository.save(product);
    }
}
