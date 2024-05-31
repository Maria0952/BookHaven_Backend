package com.bookhaven.ecom.services.admin.adminproduct;

import java.io.IOException;
import java.util.List;

import com.bookhaven.ecom.dto.ProductDto;

public interface AdminProductService {

	ProductDto addProduct(ProductDto productDto) throws IOException; 
	List<ProductDto> getAllProducts();
	List<ProductDto> getAllProductByName(String name);
	ProductDto getProductById(Long productId);
	ProductDto updateProduct(Long productId , ProductDto productDto) throws IOException;
	boolean deleteProduct(Long id);
}
