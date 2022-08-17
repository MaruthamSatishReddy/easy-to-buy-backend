package com.easytobuy;

import io.micrometer.core.annotation.Timed;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@Slf4j
@RequestMapping("/api/v1/category")
@AllArgsConstructor
public class CategoryController {
  private final CategoryRepository categoryRepository;

  @PostMapping("/save")
  @Timed(value = "saveCategory.time",description = "Time Taken To Persist the Category Details")
  public ResponseEntity<Category> saveCategory(@RequestBody Category category) {
    categoryRepository.save(category);
    return ResponseEntity.ok(category);
  }

  @CrossOrigin(origins = "http://localhost:3000")
  @GetMapping
  public ResponseEntity<List<Category>> getCategories() {
      List<Category> getCategories =
        categoryRepository.findAll().stream().collect(Collectors.toList());
    return new ResponseEntity<List<Category>>(getCategories, HttpStatus.OK);
  }
}
