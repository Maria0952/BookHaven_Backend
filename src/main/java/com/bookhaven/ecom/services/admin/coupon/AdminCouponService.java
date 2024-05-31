package com.bookhaven.ecom.services.admin.coupon;

import java.util.List;

import com.bookhaven.ecom.entity.Coupon;

public interface AdminCouponService {
	Coupon createCoupon(Coupon coupon);
	List<Coupon> getAllCoupons();
}
