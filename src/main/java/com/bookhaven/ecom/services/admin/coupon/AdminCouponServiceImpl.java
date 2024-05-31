package com.bookhaven.ecom.services.admin.coupon;
 
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.bookhaven.ecom.entity.Coupon;
import com.bookhaven.ecom.exceptions.ValidationException;
import com.bookhaven.ecom.repository.CouponRepository;

import lombok.RequiredArgsConstructor;
 
@Service
@RequiredArgsConstructor
public class AdminCouponServiceImpl implements AdminCouponService {
 
	private final CouponRepository couponRepository;
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	public Coupon createCoupon(Coupon coupon) {
		if(couponRepository.existsByCode(coupon.getCode())) {
			throw new ValidationException("Coupon code already exists");
		}
		return couponRepository.save(coupon);
	}
	public List<Coupon> getAllCoupons(){
		return couponRepository.findAll();
	}
}
