package com.easytobuy;

import com.easytobuy.*;
import com.easytobuy.CustomerRepository;
import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.PaymentIntent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class PaymentServiceImpl implements PaymentService{
    private CustomerRepository customerRepository;

    @Autowired
    public PaymentServiceImpl(CustomerRepository customerRepository, @Value("${stripe.key.secret}")String secrectKey) {
        this.customerRepository = customerRepository;
        Stripe.apiKey=secrectKey;
    }


    @Override
    public PurchaseResponse placeOrder(Purchase purchase) {
        Order order = purchase.getOrder();
        String orderTrackingId = generateTrackingId();
        order.setOrderTrackingId(orderTrackingId);
        Set<OrderItem> orderItems = purchase.getOrderItems();
        orderItems.forEach(item -> order.add(item));
        order.setBillingAddress(purchase.getBillingAddress());
        order.setShippingAddress(purchase.getShippingAddress());
        Customer customer = purchase.getCustomer();
        customer.add(order);
        customerRepository.save(customer);

        return new PurchaseResponse(orderTrackingId);
    }

    @Override
    public PaymentIntent crePaymentIntent(PaymentInfo paymentInfo) throws StripeException {
        List<String> paymentMethodTypes=new ArrayList<>();
        paymentMethodTypes.add("card");
        Map<String,Object> params=new HashMap<>();
        params.put("amount",paymentInfo.getAmount());
        params.put("currency",paymentInfo.getCurrency());
        params.put("payment_method_types",paymentMethodTypes);
        return PaymentIntent.create(params);
    }
    public String generateTrackingId() {
        return UUID.randomUUID().toString();
    }
}
