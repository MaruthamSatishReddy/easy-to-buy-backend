/** */
package com.easytobuy.repository;

import com.easytobuy.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Satish Reddy
 */
@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {}
