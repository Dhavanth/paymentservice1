package org.example.paymentservice1.services;

import com.razorpay.RazorpayException;

public interface PaymentService {

    // API - 1: CREATE PAYMENT LINK
    public String createPaymentLink(String orderId) throws RazorpayException;

    // API - 2: GET PAYMENT STATUS
    public String getPaymentStatus(String orderId);
}
