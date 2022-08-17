package com.easytobuy;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class CheckOutApplication {

  public static void main(String[] args) {
    SpringApplication.run(CheckOutApplication.class, args);
  }
}
