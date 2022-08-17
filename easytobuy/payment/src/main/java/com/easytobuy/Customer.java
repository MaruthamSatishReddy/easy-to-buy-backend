/** */
package com.easytobuy;

import lombok.*;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * @author Satish Reddy
 */
@Entity
@Table(name = "customer")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Customer {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name = "id")
  private Long id;

  @Column(name = "first_name")
  private String firstName;

  @Column(name = "last_name")
  private String lastName;

  @Column(name = "email")
  private String email;

  @OneToMany(cascade = CascadeType.ALL, mappedBy = "customer")
  private Set<Order> orders = new HashSet<>();

  @OneToOne
  @JoinColumn(name = "billing_address_id", referencedColumnName = "address_id")
  private Address billingAddress;

  @OneToOne
  @JoinColumn(name = "shipping_address_id", referencedColumnName = "address_id")
  private Address shippingAddress;

  public void add(Order order) {
    if (order != null) {
      if (orders == null) {
        orders = new HashSet<>();
      }
      orders.add(order);
      order.setCustomer(this);
    }
  }
}
