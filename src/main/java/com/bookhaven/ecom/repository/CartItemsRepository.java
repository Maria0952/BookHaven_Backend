package com.bookhaven.ecom.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bookhaven.ecom.entity.CartItems;

@Repository
public interface CartItemsRepository extends JpaRepository<CartItems, Long>{
	Optional<CartItems> findByProductIdAndOrderIdAndUserId(Long productId,long orderId,Long userId);
}
