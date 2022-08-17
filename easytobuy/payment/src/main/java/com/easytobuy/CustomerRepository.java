/** */
package com.easytobuy;

import com.easytobuy.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Satish Reddy
 */
@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {}
