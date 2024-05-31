package com.bookhaven.ecom.servicetest.admin;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.bookhaven.ecom.dto.CategoryDto;
import com.bookhaven.ecom.entity.Category;
import com.bookhaven.ecom.repository.CategoryRepository;
import com.bookhaven.ecom.services.admin.category.CategoryServiceImpl;


@ExtendWith(MockitoExtension.class)
public class CategoryServiceImplTest {

    @InjectMocks
    private CategoryServiceImpl categoryService;

    @Mock
    private CategoryRepository categoryRepository;

    @Test
    public void testCreateCategory() {
        CategoryDto categoryDto = new CategoryDto();
        categoryDto.setName("Test");
        categoryDto.setDescription("Test Description");

        Category category = new Category();
        category.setName(categoryDto.getName());
        category.setDescription(categoryDto.getDescription());

        when(categoryRepository.save(category)).thenReturn(category);

        Category createdCategory = categoryService.createcategory(categoryDto);

        assertEquals(categoryDto.getName(), createdCategory.getName());
        assertEquals(categoryDto.getDescription(), createdCategory.getDescription());
    }
    @Test
    public void testGetAllCategories() {
        Category category1 = new Category();
        category1.setName("Test1");
        category1.setDescription("Test Description1");

        Category category2 = new Category();
        category2.setName("Test2");
        category2.setDescription("Test Description2");

        List<Category> categories = Arrays.asList(category1, category2);

        when(categoryRepository.findAll()).thenReturn(categories);

        List<Category> result = categoryService.getAllCategories();

        assertEquals(2, result.size());
        assertEquals("Test1", result.get(0).getName());
        assertEquals("Test2", result.get(1).getName());
    }
}