package com.easytobuy;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.math.BigDecimal;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Coupon {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Integer couponId;

  private String couponCode;
  private String couponDescription;
  private BigDecimal couponPrice;
}
