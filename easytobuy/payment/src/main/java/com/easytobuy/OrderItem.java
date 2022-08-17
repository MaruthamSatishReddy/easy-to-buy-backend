/** */
package com.easytobuy;

import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;

/**
 * @author Satish Reddy
 */
@Entity
@Table(name = "order_item")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class OrderItem {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name = "id")
  private Long id;

  @Column(name = "product_img_url")
  private String productImageURL;

  @Column(name = "unit_price")
  private BigDecimal unitPrice;

  @Column(name = "quantity")
  private int quantity;

  @Column(name = "product_id")
  private Long productId;

  @ManyToOne
  @JoinColumn(name = "order_id")
  private Order order;
}
