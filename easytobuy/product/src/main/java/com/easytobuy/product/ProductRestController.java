package com.easytobuy.product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/products")
public class ProductRestController {

  @Autowired private ProductRepository productRepository;

  @GetMapping("/category/{categoryId}")
  @CrossOrigin(origins = "http://angular:4200")
  public List<Product> getByCategoryId(@PathVariable("categoryId") Integer categoryId) {
    List<Product> product = productRepository.findByCategoryId(categoryId);
    return product;
  }

  @DeleteMapping("/{productId}")
  public HttpStatus deleteProduct(@PathVariable("productId") Integer productId) {
    Optional<Product> Product = productRepository.findById(productId);
    if (Product.isPresent()) {
      productRepository.deleteById(productId);
      return HttpStatus.NO_CONTENT;
    } else {
      return HttpStatus.NOT_FOUND;
    }
  }
  @CrossOrigin(origins = "http://localhost:4200")
  @PostMapping
  public ResponseEntity<Product> saveProduct(@RequestBody Product products) {
    BigDecimal productPrice = BigDecimal.valueOf(products.getProductPrice().intValue());
    products.setProductPrice(productPrice);
    productRepository.save(products);
    return new ResponseEntity<Product>(products, HttpStatus.CREATED);
  }
  @GetMapping("/search/{searchKey}")
  @CrossOrigin(origins = "http://localhost:4200")
  public List<Product> searchProduct(@PathVariable("searchKey") String searchKey) {
    /*Page<Product> searchProducts = productRepository.findByProductNameContaining(searchKey, Pageable.ofSize(5));*/
    List<Product> searchProducts=productRepository.findByProductNameContaining(searchKey);
    return searchProducts;
  }
}
