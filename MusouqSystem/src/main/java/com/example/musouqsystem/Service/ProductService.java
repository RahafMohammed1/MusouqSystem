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
    private final MarketerRepository marketerRepository;


//    marketer & supplier get all
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
        Product product = productRepository.findProductByIdAndSupplierId(product_id, supplier_id);

        if (marketer == null || product == null) throw new ApiException("cannot add product to marketer");

        product.getMarketers().add(marketer);
        productRepository.save(product);
    }

//    public void shopperAddProductToOrder(Integer shopper_id,Integer product_id, Integer order_id) {
//        Shopper shopper = shopperRepository.findShopperById(shopper_id);
//        Product product = productRepository.findProductById(product_id);
//        Orders order = ordersRepository.findOrdersById(order_id);
//
//        if (shopper == null || product == null || order == null) throw new ApiException("cannot add product to order");
//
//        product.setOrders(order);
//        productRepository.save(product);
//    }


    public void supplierUpdateProduct(Integer supplier_id, Integer product_id, Product product) {
        Product oldProduct = productRepository.findProductByIdAndSupplierId(product_id, supplier_id);

        if (oldProduct == null) throw new ApiException("supplier or product not exist");

        oldProduct.setName(product.getName());
        oldProduct.setDescription(product.getDescription());
        oldProduct.setPrice(product.getPrice());
        oldProduct.setCategory(product.getCategory());
        oldProduct.setStock(product.getStock());

        productRepository.save(oldProduct);
    }

    public void marketerApplyDiscount(Integer marketer_id, Integer product_id, Double discount) {
        Marketer marketer = marketerRepository.findMarketerById(marketer_id);
        Product product = productRepository.findProductById(product_id);

        if (marketer == null || product == null) throw new ApiException("marketer or product not exist");

        if (product.getMarketers().contains(marketer)){
            product.setPrice_after_discount(product.getPrice() * discount);
            productRepository.save(product);
        }else throw new ApiException("you cannot apply discount on this product");
    }

    public void supplierDeleteProduct(Integer supplier_id, Integer product_id) {
        Product product = productRepository.findProductByIdAndSupplierId(product_id, supplier_id);

        if (product == null) throw new ApiException("supplier or product not exist");

        productRepository.delete(product);
    }

    public void marketerDeleteProduct(Integer marketer_id, Integer product_id) {
        Marketer marketer = marketerRepository.findMarketerById(marketer_id);
        Product product = productRepository.findProductById(product_id);

        if (marketer == null || product == null) throw new ApiException("marketer or product not exist");

        if (product.getMarketers().contains(marketer)) {
            product.getMarketers().remove(marketer);
            productRepository.save(product);
        }else throw new ApiException("you cannot delete this product");

    }

//    public void shopperRemoveProductFromOrder(Integer shopper_id, Integer product_id) {
//        Shopper shopper = shopperRepository.findShopperById(shopper_id);
//        Product product = productRepository.findProductById(product_id);
//
//        if (shopper == null || product == null) throw new ApiException("marketer or product not exist");
//
//        if (product.getOrders().getOrder_status().equalsIgnoreCase("processing")){
//            product.setOrders(null);
//            productRepository.save(product);
//        }else throw new ApiException("you cannot delete product after shipping");
//    }


    public void assignSupplierToProduct(Integer supplier_id, Integer product_id){
        Supplier supplier = supplierRepository.findSupplierById(supplier_id);
        Product product = productRepository.findProductById(product_id);

        if (supplier == null || product == null) throw new ApiException("cannot assign supplier to product");

        product.setSupplier(supplier);
        productRepository.save(product);
    }
}
