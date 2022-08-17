/** */
package com.easytobuy;

import lombok.Data;

import java.util.Set;

/**
 * @author Satish Reddy
 */
@Data
public class Purchase {

  private Customer customer;
  private Address shippingAddress;
  private Address billingAddress;
  private Order order;
  private Set<OrderItem> orderItems;
}
