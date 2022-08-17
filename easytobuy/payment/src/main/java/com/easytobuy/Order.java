/** */
package com.easytobuy;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * @author Satish Reddy
 */
@Entity
@Table(name = "orders")
@Getter
@Setter
public class Order {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name = "id")
  private Long id;

  @Column(name = "order_tracking_id")
  private String orderTrackingId;

  @Column(name = "quantity")
  private int quantity;

  @Column(name = "total_price")
  private BigDecimal totalPrice;

  @Column(name = "status")
  private String status;

  @Column(name = "order_created_date")
  @CreationTimestamp
  private Date orderDate;

  @UpdateTimestamp
  @Column(name = "order_lastupdated_date")
  private Date lastUpdate;

  @OneToMany(cascade = CascadeType.ALL, mappedBy = "order")
  private Set<OrderItem> orderItems = new HashSet<>();

  @ManyToOne
  @JoinColumn(name = "customer_id")
  private Customer customer;

  @OneToOne(cascade = CascadeType.ALL)
  @JoinColumn(name = "billing_address_id", referencedColumnName = "address_id")
  private Address billingAddress;

  @OneToOne(cascade = CascadeType.ALL)
  @JoinColumn(name = "shipping_address_id", referencedColumnName = "address_id")
  private Address ShippingAddress;

  public void add(OrderItem item) {
    if (item != null) {
      if (orderItems == null) {
        orderItems = new HashSet<>();
      }
      orderItems.add(item);
      item.setOrder(this);
    }
  }
}
