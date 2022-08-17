package com.easytobuy;
import com.stripe.exception.StripeException;
import com.stripe.model.PaymentIntent;


public interface PaymentService {
    PurchaseResponse placeOrder(Purchase purchase);
    PaymentIntent crePaymentIntent(PaymentInfo paymentInfo) throws StripeException;
}
