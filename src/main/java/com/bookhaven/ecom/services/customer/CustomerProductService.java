package com.bookhaven.ecom.services.customer;

import java.util.List;

import com.bookhaven.ecom.dto.ProductDetailDto;
import com.bookhaven.ecom.dto.ProductDto;

public interface CustomerProductService {
List<ProductDto> searchProductByTitle(String title);
List<ProductDto> getAllProducts();
ProductDetailDto getProductDetailById(Long productId);	
	
}
