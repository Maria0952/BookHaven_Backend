package com.bookhaven.ecom.services.customer.review;
 
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
 
import org.springframework.stereotype.Service;

import com.bookhaven.ecom.dto.OrderedProductsResponseDto;
import com.bookhaven.ecom.dto.ProductDto;
import com.bookhaven.ecom.dto.ReviewDto;
import com.bookhaven.ecom.entity.CartItems;
import com.bookhaven.ecom.entity.Order;
import com.bookhaven.ecom.entity.Product;
import com.bookhaven.ecom.entity.Review;
import com.bookhaven.ecom.entity.User;
import com.bookhaven.ecom.repository.OrderRepository;
import com.bookhaven.ecom.repository.ProductRepository;
import com.bookhaven.ecom.repository.ReviewRepository;
import com.bookhaven.ecom.repository.UserRepository;

import lombok.RequiredArgsConstructor;
 
@Service
@RequiredArgsConstructor
public class ReviewServiceImpl implements  ReviewService{
 
	private final OrderRepository orderRepository;
	private final ProductRepository productRepository;
	private final UserRepository userRepository;
	private final ReviewRepository reviewRepository;
	public OrderedProductsResponseDto getOrderedProductsDetailsByOrderId(Long orderId) {
		Optional<Order> optionalOrder =orderRepository.findById(orderId);
		OrderedProductsResponseDto orderedProductsResponseDto = new OrderedProductsResponseDto();
		if(optionalOrder.isPresent()) {
			orderedProductsResponseDto.setOrderAmount(optionalOrder.get().getAmount());
			List<ProductDto> productDtoList = new ArrayList<>();
			for(CartItems cartItems : optionalOrder.get().getCartItems()) {
				ProductDto productDto= new ProductDto();
				productDto.setId(cartItems.getProduct().getId());
				productDto.setName(cartItems.getProduct().getName());
				productDto.setPrice(cartItems.getPrice());
				productDto.setQuantity(cartItems.getQuantity());
				productDto.setByteImg(cartItems.getProduct().getImg());
				productDtoList.add(productDto);
			}
			orderedProductsResponseDto.setProductDtoList(productDtoList);
		}
		return orderedProductsResponseDto;
	}
	
	public ReviewDto giveReview(ReviewDto reviewDto) throws IOException {
		Optional<Product> optionalProduct= productRepository.findById(reviewDto.getProductId());
		Optional<User> optionalUser=userRepository.findById(reviewDto.getUserId());
		if(optionalProduct.isPresent() && optionalUser.isPresent()) {
			Review review=new Review();
			review.setRating(reviewDto.getRating());
			review.setDescription(reviewDto.getDescription());
			review.setUser(optionalUser.get());
			review.setProduct(optionalProduct.get());
			review.setImg(reviewDto.getImg().getBytes());
			return reviewRepository.save(review).getDto();
		}
		return null;
	}
}