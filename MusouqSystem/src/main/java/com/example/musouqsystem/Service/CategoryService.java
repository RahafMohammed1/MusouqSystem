package com.example.musouqsystem.Service;

import com.example.musouqsystem.Api.ApiException;
import com.example.musouqsystem.Model.Category;
import com.example.musouqsystem.Model.Supplier;
import com.example.musouqsystem.Repository.CategoryRepository;
import com.example.musouqsystem.Repository.SupplierRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CategoryService {
    private final CategoryRepository categoryRepository;
    private final SupplierRepository supplierRepository;



    public void assignSupplierToCategory(Integer supplier_id, Integer category_id) {
        Supplier supplier = supplierRepository.findSupplierById(supplier_id);
        Category category = categoryRepository.findCategoryById(category_id);

        if (supplier == null || category == null) throw new ApiException("cannot assign supplier to category");

        category.setSupplier(supplier);
        categoryRepository.save(category);
    }
}
