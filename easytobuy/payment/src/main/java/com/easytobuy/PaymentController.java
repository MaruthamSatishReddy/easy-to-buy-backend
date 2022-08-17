package com.easytobuy;

import com.easytobuy.Purchase;
import com.easytobuy.PurchaseResponse;
import com.stripe.exception.StripeException;
import com.stripe.model.PaymentIntent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/payment")

public class PaymentController {

    @Autowired
    public PaymentService paymentService;
    @PostMapping("/paymentInfo")
    @CrossOrigin(origins = "http://localhost:4200")
    public ResponseEntity<String> createPaymentInfo(@RequestBody PaymentInfo paymentInfo) throws StripeException {
        PaymentIntent paymentIntent = paymentService.crePaymentIntent(paymentInfo);
        String paymentIntentInfo=paymentIntent.toJson();
        return new ResponseEntity<>(paymentIntentInfo, HttpStatus.OK);
    }
  @PostMapping("/purchase")
  @CrossOrigin(origins = "http://localhost:4200")
  public PurchaseResponse placeOrder(@RequestBody Purchase purchase) {
    PurchaseResponse purchaseResponse = paymentService.placeOrder(purchase);
    return purchaseResponse;
  }
}
