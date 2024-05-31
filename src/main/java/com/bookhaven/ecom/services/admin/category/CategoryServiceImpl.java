package com.bookhaven.ecom.services.admin.category;

import java.util.List;

import org.springframework.stereotype.Service;

import com.bookhaven.ecom.dto.CategoryDto;
import com.bookhaven.ecom.entity.Category;
import com.bookhaven.ecom.repository.CategoryRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService  {
	
	private final CategoryRepository categoryRepository;
	
	public Category createcategory(CategoryDto categoryDto) {
		Category category=new Category();
		category.setName(categoryDto.getName());
		category.setDescription(categoryDto.getDescription());
	    return categoryRepository.save(category);
	}
	
	public List<Category> getAllCategories(){
		return categoryRepository.findAll();
	}
}
