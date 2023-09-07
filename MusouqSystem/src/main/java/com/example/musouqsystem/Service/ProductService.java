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
    private final ImageRepository imageRepository;
    private final CategoryRepository categoryRepository;


//    marketer & supplier get all
    public List<Product> getAllProductsOfSupplier(Integer supplier_id) {
        return productRepository.findProductsBySupplierId(supplier_id);
    }


//    shopper
    public List<Product> getAllProductsOfMarketer(Integer marketer_id) {
        Marketer marketer = marketerRepository.findMarketerById(marketer_id);
        if (marketer == null) throw new ApiException("marketer not found");
        return productRepository.findAllByMarketersContains(marketer);
    }


    public List<Product> getAllProductsByCategory(Integer category_id) {
        Category category = categoryRepository.findCategoryById(category_id);
        if (category == null) throw new ApiException("category not found");

        return productRepository.findProductsByCategory(category);
    }


    public void supplierAddProduct(Integer supplier_id, Integer category_id, Product product, Double percent) {
        Supplier supplier = supplierRepository.findSupplierById(supplier_id);
        Category category = categoryRepository.findCategoryById(category_id);

        if (supplier == null || category == null) throw new ApiException("supplier or category not exist");

        product.setSupplier(supplier);
        product.setCategory(category);
        category.setSupplier(supplier);
        category.setMarketer_percent(percent / 100);
        productRepository.save(product);
        categoryRepository.save(category);
    }

    public void marketerAddProduct(Integer marketer_id, Integer product_id, Integer supplier_id ) {
        Marketer marketer = marketerRepository.findMarketerById(marketer_id);
        Product product = productRepository.findProductByIdAndSupplierId(product_id, supplier_id);

        if (marketer == null || product == null) throw new ApiException("cannot add product to marketer");

        product.getMarketers().add(marketer);
        productRepository.save(product);
    }


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
    public void assignSupplierToProduct(Integer supplier_id, Integer product_id){
        Supplier supplier = supplierRepository.findSupplierById(supplier_id);
        Product product = productRepository.findProductById(product_id);

        if (supplier == null || product == null) throw new ApiException("cannot assign supplier to product");

        product.setSupplier(supplier);
        productRepository.save(product);
    }

    public void assignCategoryToProduct(Integer category_id, Integer product_id) {
        Category category = categoryRepository.findCategoryById(category_id);
        Product product = productRepository.findProductById(product_id);

        if (category == null || product == null) throw new ApiException("cannot assign category to product");

        product.setCategory(category);
        productRepository.save(product);
    }
}
