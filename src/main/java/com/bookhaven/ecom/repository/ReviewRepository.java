package com.bookhaven.ecom.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bookhaven.ecom.entity.FAQ;
import com.bookhaven.ecom.entity.Review;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Long>{
	List<Review> findAllByProductId(Long productId);
}
