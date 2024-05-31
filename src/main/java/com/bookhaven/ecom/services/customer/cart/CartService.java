package com.bookhaven.ecom.services.customer.cart;

import java.util.List;
 
import org.springframework.http.ResponseEntity;

import com.bookhaven.ecom.dto.AddProductInCartDto;
import com.bookhaven.ecom.dto.OrderDto;
import com.bookhaven.ecom.dto.PlaceOrderDto;
 
public interface CartService {
 
	ResponseEntity<?> addProductToCart(AddProductInCartDto addProductInCartDto);
	OrderDto getCartByUserId(Long userId);
	OrderDto applyCoupon(Long userId,String code);
	OrderDto increaseProductQuantity(AddProductInCartDto addProductInCartDto); 
	OrderDto decreaseProductQuantity(AddProductInCartDto addProductInCartDto);
	OrderDto placeOrder(PlaceOrderDto placeOrderDto);
	List<OrderDto> getMyPlacedOrders(Long userId);
}