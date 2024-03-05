package org.example.paymentservice1.services;

import org.springframework.stereotype.Service;

@Service
public class StripePaymentService implements PaymentService{
    @Override
    public String createPaymentLink(String orderId) {
        return null;
    }

    @Override
    public String getPaymentStatus(String orderId) {
        return null;
    }
}
