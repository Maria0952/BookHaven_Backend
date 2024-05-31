package com.bookhaven.ecom.dto;

import java.util.List;

import lombok.Data;

@Data
public class OrderedProductsResponseDto {
	
	private Long orderAmount;
	
	private List<ProductDto> productDtoList;
	
}
