package com.example.musouqsystem.Service;

import com.example.musouqsystem.Api.ApiException;
import com.example.musouqsystem.Model.Category;
import com.example.musouqsystem.Model.Supplier;
import com.example.musouqsystem.Model.User;
import com.example.musouqsystem.Repository.AuthRepository;
import com.example.musouqsystem.Repository.CategoryRepository;
import com.example.musouqsystem.Repository.SupplierRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryService {
    private final CategoryRepository categoryRepository;


    public List<Category> supplierGetAllCategories() {
        return categoryRepository.findAll();
    }

//    admin auth
    public void addCategory(Category category) {
        categoryRepository.save(category);
    }


    public void updateCategory(Integer category_id, Category category) {
        Category oldCategory = categoryRepository.findCategoryById(category_id);
        if (oldCategory == null) throw new ApiException("category not exist");

        oldCategory.setTitle(category.getTitle());
        categoryRepository.save(oldCategory);
    }

    public void supplierUpdateMarketerPercent(Integer category_id, Double percent) {
        Category category = categoryRepository.findCategoryById(category_id);

        if (category == null) throw new ApiException("category or supplier not exist");

        category.setMarketer_percent(percent / 100);
        categoryRepository.save(category);
    }

    public void deleteCategory(Integer category_id) {
        Category oldCategory = categoryRepository.findCategoryById(category_id);
        if (oldCategory == null) throw new ApiException("category not exist");

        if (oldCategory.getProducts().isEmpty())
            categoryRepository.delete(oldCategory);
        else throw new ApiException("you cannot delete category that has products");
    }


}
