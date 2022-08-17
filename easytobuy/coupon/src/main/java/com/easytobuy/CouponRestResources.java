/** */
package com.easytobuy;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * @author Satish Reddy
 */
@RestController
@RequestMapping("/api/v1/coupons")
@RequiredArgsConstructor
public class CouponRestResources {
  @Autowired private final CouponRepository couponRepository;

  @Value("${server.port}")
  private int serverPort;

  @PostMapping("/save")
  public ResponseEntity<String> saveCoupon(@RequestBody Coupon coupon) {
    Coupon saveCoupon = couponRepository.save(coupon);
    URI uri =
        ServletUriComponentsBuilder.fromCurrentRequest()
            .path("/{id}")
            .buildAndExpand(saveCoupon.getCouponId())
            .toUri();
    return ResponseEntity.created(uri).build();
  }

  @GetMapping("/{id}")
  public Coupon getCouponById(@PathVariable int id) {
    return couponRepository
        .findById(id)
        .orElseThrow(() -> new RuntimeException("Cannot Find Coupon By ID: " + id));
  }

  @GetMapping("/couponCode/{couponCode}")
  public ResponseEntity<Optional<Coupon>> getByCouponCode(
      @PathVariable("couponCode") String couponCode) {
    Optional<Coupon> couponDetail = couponRepository.findByCouponCode(couponCode);
    if (couponDetail.isPresent()) {
      return new ResponseEntity<Optional<Coupon>>(couponDetail, HttpStatus.OK);
    }
    return new ResponseEntity<Optional<Coupon>>(couponDetail, HttpStatus.NO_CONTENT);
  }

  @GetMapping
  public List<Coupon> getCoupons() {
    return couponRepository.findAll().stream().collect(Collectors.toList());
  }
}
