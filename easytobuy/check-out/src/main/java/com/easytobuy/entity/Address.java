/** */
package com.easytobuy.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

/**
 * @author Satish Reddy
 */
@Entity
@Table(name = "address")
@Getter
@Setter
public class Address {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "address_id")
  private Long addressId;

  @Column(name = "street")
  private String street;

  @Column(name = "city")
  private String city;

  @Column(name = "state")
  private String state;

  @Column(name = "country")
  private String country;

  @Column(name = "zip_code")
  private String zipCode;
}
