package com.bookhaven.ecom.servicetest.admin;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.bookhaven.ecom.controller.admin.AdminCategoryController;
import com.bookhaven.ecom.dto.CategoryDto;
import com.bookhaven.ecom.entity.Category;
import com.bookhaven.ecom.services.admin.category.CategoryService;

@ExtendWith(MockitoExtension.class)
public class CategoryControllerTest {

    @InjectMocks
    private AdminCategoryController adminCategoryController;

    @Mock
    private CategoryService categoryService;

    @Test
    public void testCreateCategory() {
        CategoryDto categoryDto = new CategoryDto();
        // set properties for categoryDto

        Category category = new Category();
        // set properties for category

        when(categoryService.createcategory(categoryDto)).thenReturn(category);

        ResponseEntity<Category> response = adminCategoryController.createCategory(categoryDto);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(category, response.getBody());
    }

    @Test
    public void testGetAllCategories() {
        Category category1 = new Category();
        Category category2 = new Category();
        // set properties for categories

        List<Category> categories = Arrays.asList(category1, category2);

        when(categoryService.getAllCategories()).thenReturn(categories);

        ResponseEntity<List<Category>> response = adminCategoryController.getAllCategories();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(categories, response.getBody());
    }
}
