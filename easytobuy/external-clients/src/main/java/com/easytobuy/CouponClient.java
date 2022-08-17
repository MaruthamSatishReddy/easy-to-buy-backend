package com.easytobuy;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Optional;

@FeignClient("coupon")
public interface CouponClient {
  @GetMapping(path = "/api/v1/coupons/couponCode/{couponCode}")
  Optional<Coupon> getByCouponCode(@PathVariable("couponCode") String couponCode);
}
