package com.example.musouqsystem.ServiceTest;

import com.example.musouqsystem.Model.Category;
import com.example.musouqsystem.Model.User;
import com.example.musouqsystem.Repository.AuthRepository;
import com.example.musouqsystem.Repository.CategoryRepository;
import com.example.musouqsystem.Service.CategoryService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class CategoryServiceTest {
    @InjectMocks
    CategoryService categoryService;
    @Mock
    CategoryRepository categoryRepository;

    User user;
    Category category1;
    Category category2;

    List<Category> categories;

    @BeforeEach
    void setUp() {
        user = new User(null,"Admin","Re342837","admin@gmail.com","ADMIN",null,null,null);

        category1 = new Category(null,"electronic",0.4,null);
        category2 = new Category(null,"food",0.2,null);

        categories = new ArrayList<>();
        categories.add(category1);
        categories.add(category2);
    }


    @Test
    public void getAllCategoriesTest(){
        when(categoryRepository.findAll()).thenReturn(categories);
        List<Category> checkCategory = categoryService.supplierGetAllCategories();

        Assertions.assertEquals(checkCategory,categories);
        Mockito.verify(categoryRepository, times(1)).findAll();
    }

    @Test
    public void addNewCategoryTest(){
        categoryService.addCategory(category1);
        verify(categoryRepository,times(1)).save(category1);
    }

    @Test
    public void updateCategoryTest(){
        when(categoryRepository.findCategoryById(category1.getId())).thenReturn(category1);
        category1.setTitle("Dress");
        category1.setMarketer_percent(0.5);

        categoryService.updateCategory(category1.getId(), category1);
        verify(categoryRepository, times(1)).findCategoryById(category1.getId());
    }

    @Test
    public void supplierUpdateMarketerPercentTest(){
        when(categoryRepository.findCategoryById(category1.getId())).thenReturn(category1);

        categoryService.supplierUpdateMarketerPercent(category1.getId(), 0.6);
        verify(categoryRepository,times(1)).findCategoryById(category1.getId());

    }

}
