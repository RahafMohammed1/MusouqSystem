package com.example.musouqsystem.Service;

import com.example.musouqsystem.Api.ApiException;
import com.example.musouqsystem.Model.*;
import com.example.musouqsystem.Repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;
    private final SupplierRepository supplierRepository;
    private final MarketerRepository marketerRepository;
    private final CategoryRepository categoryRepository;
    private final AuthRepository authRepository;


//    supplier get all
    public List<Product> getAllProductsOfSupplier(Integer supplier_id) {
        User user = authRepository.findUserById(supplier_id);
        return productRepository.findProductsBySupplierId(user.getId());
    }

    public Set<Product> marketerGetAllProductsOfSupplier(Integer marketer_id) {
        User user = authRepository.findUserById(marketer_id);

        if (user.getMarketer().getSupplier() == null) throw new ApiException("you don't have supplier, select one first");

        return user.getMarketer().getSupplier().getProducts();
    }


//    shopper
    public List<Product> getAllProductsOfMarketer(Integer shopper_id) {
        User user = authRepository.findUserById(shopper_id);

        return productRepository.findAllByMarketersContains(user.getShopper().getMarketer());
    }


    public List<Product> getAllProductsByCategory(Integer user_id, Integer category_id) {
        User user = authRepository.findUserById(user_id);
        Category category = categoryRepository.findCategoryById(category_id);

        if (category == null) throw new ApiException("category not found");

        if (user.getRole().equals("MARKETER"))
            return productRepository.findProductsByCategoryAndMarketersContains(category, user.getMarketer());
        else if (user.getRole().equals("SUPPLIER"))
            return productRepository.findProductsByCategoryAndSupplier(category, user.getSupplier());
        else throw new ApiException("you don't have products yet");

    }


    public void supplierAddProduct(Integer supplier_id, Integer category_id, Product product) {
        User user = authRepository.findUserById(supplier_id);
        Category category = categoryRepository.findCategoryById(category_id);

        if (category == null) throw new ApiException("category not exist");

        product.setSupplier(user.getSupplier());
        product.setCategory(category);
        productRepository.save(product);
        categoryRepository.save(category);
    }

    public void marketerAddProduct(Integer marketer_id, Integer product_id) {
        User user = authRepository.findUserById(marketer_id);
        Product product = productRepository.findProductById(product_id);

        if (product == null) throw new ApiException("cannot add product to marketer");

        if (user.getMarketer().getSupplier().getProducts().contains(product)) {
            product.getMarketers().add(user.getMarketer());
            user.getMarketer().getProducts().add(product);
            marketerRepository.save(user.getMarketer());
            productRepository.save(product);
        }else throw new ApiException("you cannot add this product");

    }


    public void supplierUpdateProduct(Integer supplier_id, Integer product_id, Product product) {
        User user = authRepository.findUserById(supplier_id);
        Product oldProduct = productRepository.findProductByIdAndSupplierId(product_id, user.getId());

        if (oldProduct == null) throw new ApiException("supplier or product not exist");

        oldProduct.setName(product.getName());
        oldProduct.setDescription(product.getDescription());
        oldProduct.setPrice(product.getPrice());
        oldProduct.setStock(product.getStock());

        productRepository.save(oldProduct);
    }

    public void marketerApplyDiscount(Integer marketer_id, Integer product_id, Integer discount) {
        User user = authRepository.findUserById(marketer_id);
        Product product = productRepository.findProductById(product_id);

        if (product == null) throw new ApiException("product not exist");

        if (product.getMarketers().contains(user.getMarketer())){
            product.setPrice_after_discount(product.getPrice() - (product.getPrice() * (discount / 100.0)));
            productRepository.save(product);
        }else throw new ApiException("you cannot apply discount on this product");
    }

    public void supplierDeleteProduct(Integer supplier_id, Integer product_id) {
        User user = authRepository.findUserById(supplier_id);
        Product product = productRepository.findProductByIdAndSupplierId(product_id, user.getId());

        if (product == null) throw new ApiException("product not exist");

        if (product.getMarketers().isEmpty())
            productRepository.delete(product);
        else throw new ApiException("product belongs to a marketer now");
    }

    public void marketerDeleteProduct(Integer marketer_id, Integer product_id) {
        User user = authRepository.findUserById(marketer_id);
        Product product = productRepository.findProductById(product_id);

        if (product == null) throw new ApiException("product not exist");

        if (product.getMarketers().contains(user.getMarketer()) || user.getMarketer().getProducts().contains(product)) {
            product.getMarketers().remove(user.getMarketer());
            user.getMarketer().getProducts().remove(product);
            marketerRepository.save(user.getMarketer());
            productRepository.save(product);
        }else throw new ApiException("you cannot delete this product");

    }


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
