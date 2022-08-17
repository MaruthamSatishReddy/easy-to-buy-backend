/** */
package com.easytobuy.service;

import com.easytobuy.entity.PaymentInfo;
import com.easytobuy.entity.Purchase;
import com.easytobuy.entity.PurchaseResponse;
import com.stripe.exception.StripeException;
import com.stripe.model.PaymentIntent;

/**
 * @author Satish Reddy
 */
public interface CheckOutService {
  PurchaseResponse placeOrder(Purchase purchase);
  PaymentIntent crePaymentIntent(PaymentInfo paymentInfo) throws StripeException;
}
