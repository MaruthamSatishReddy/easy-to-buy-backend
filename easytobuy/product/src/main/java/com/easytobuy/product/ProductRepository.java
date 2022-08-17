package com.easytobuy.product;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {
  List<Product> findByCategoryId(Integer categoryId);
  Page<Product> findByProductNameContaining(String searchKey, Pageable pageable);
  List<Product> findByProductNameContaining(String searchKey);
}
