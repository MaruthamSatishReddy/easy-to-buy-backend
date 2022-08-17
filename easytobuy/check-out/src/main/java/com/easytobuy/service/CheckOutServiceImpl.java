package com.easytobuy.service;

import com.easytobuy.entity.*;
import com.easytobuy.repository.CustomerRepository;
import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.PaymentIntent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.*;

@Service
public class CheckOutServiceImpl implements CheckOutService {

  private CustomerRepository customerRepository;

  @Autowired
  public CheckOutServiceImpl(CustomerRepository customerRepository, @Value("${stripe.key.secret}")String secrectKey) {
    this.customerRepository = customerRepository;
    Stripe.apiKey=secrectKey;
  }

  @Override
  @Transactional
  public PurchaseResponse placeOrder(Purchase purchase) {
    Order order = purchase.getOrder();
    String orderTrackingId = generateTrackingId();
    order.setOrderTrackingId(orderTrackingId);
    Set<OrderItem> orderItems = purchase.getOrderItems();
    orderItems.forEach(item -> order.add(item));
    order.setBillingAddress(purchase.getBillingAddress());
    order.setShippingAddress(purchase.getShipingAddress());
    Customer customer = purchase.getCustomer();
    customer.add(order);
    customerRepository.save(customer);

    return new PurchaseResponse(orderTrackingId);
  }

  @Override
  public PaymentIntent crePaymentIntent(PaymentInfo paymentInfo) throws StripeException {
    List<String> paymentMethodTypes=new ArrayList<>();
    Map<String,Object>params=new HashMap<>();
    params.put("amount",paymentInfo.getAmount());
    params.put("currency",paymentInfo.getCurrency());
    params.put("payment_method_types",paymentMethodTypes);
    return PaymentIntent.create(params);
  }

  public String generateTrackingId() {
    return UUID.randomUUID().toString();
  }
}
