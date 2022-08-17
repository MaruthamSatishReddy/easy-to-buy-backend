/** */
package com.easytobuy;

import lombok.*;

import javax.persistence.*;

/**
 * @author Satish Reddy
 */
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
public class Profile {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Integer id;
  private String name;
  private String password;
  private String email;
}
