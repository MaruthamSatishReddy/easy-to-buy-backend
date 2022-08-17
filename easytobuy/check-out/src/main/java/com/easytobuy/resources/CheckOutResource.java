/** */
package com.easytobuy.resources;

import com.easytobuy.entity.PaymentInfo;
import com.easytobuy.entity.Purchase;
import com.easytobuy.entity.PurchaseResponse;
import com.easytobuy.service.CheckOutService;
import com.stripe.exception.StripeException;
import com.stripe.model.PaymentIntent;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * @author Satish Reddy
 */
@RestController
@RequestMapping("/api/checkout")
@AllArgsConstructor
public class CheckOutResource {

  @Autowired
  public CheckOutService checkOutService;

/*  @Autowired
  public CheckOutResource(CheckOutService checkOutService) {
    this.checkOutService = checkOutService;
  }*/
/*
  @PostMapping("/purchase")
  @CrossOrigin(origins = "http://localhost:4200")
  public PurchaseResponse placeOrder(@RequestBody Purchase purchase) {
    PurchaseResponse purchaseResponse = checkOutService.placeOrder(purchase);
    return purchaseResponse;
  }*/
  @PostMapping("/paymentInfo")
  public ResponseEntity<String> createPaymentInfo(@RequestBody PaymentInfo paymentInfo) throws StripeException {
    PaymentIntent paymentIntent = checkOutService.crePaymentIntent(paymentInfo);
    String paymentIntentInfo=paymentIntent.toJson();
    return new ResponseEntity<>(paymentIntentInfo, HttpStatus.OK);
  }
}
