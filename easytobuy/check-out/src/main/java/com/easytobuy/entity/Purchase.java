/** */
package com.easytobuy.entity;

import lombok.Data;

import java.util.Set;

/**
 * @author Satish Reddy
 */
@Data
public class Purchase {

  private Customer customer;
  private Address shipingAddress;
  private Address billingAddress;
  private Order order;
  private Set<OrderItem> orderItems;
}
