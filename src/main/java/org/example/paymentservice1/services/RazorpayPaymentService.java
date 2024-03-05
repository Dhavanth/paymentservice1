package org.example.paymentservice1.services;

import com.razorpay.PaymentLink;
import com.razorpay.RazorpayClient;
import com.razorpay.RazorpayException;
import org.json.JSONObject;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

@Service
@Primary
public class RazorpayPaymentService implements PaymentService{

    private RazorpayClient razorpayClient;

    public RazorpayPaymentService(RazorpayClient razorpayClient) {
        this.razorpayClient = razorpayClient;
    }


    @Override
    public String createPaymentLink(String orderId) throws RazorpayException {
        JSONObject paymentLinkRequest = new JSONObject();
        // 1. AMOUNT DETAILS
        // Rs.25.22 is given as 25.22 * 100 = 2522. last two digits are decimal points
        paymentLinkRequest.put("amount",10000);
        paymentLinkRequest.put("currency","INR");
        paymentLinkRequest.put("accept_partial",false);
//        paymentLinkRequest.put("first_min_partial_amount",100);

        // 2. PAYMENT LINK EXPIRY DETAILS
        paymentLinkRequest.put("expire_by",System.currentTimeMillis() + (15 * 60 * 1000)); // 15 minutes from now
        // ORDER DETAILS
        paymentLinkRequest.put("reference_id", orderId);
        paymentLinkRequest.put("description","Payment for order no: " + orderId);


        // 3. CUSTOMER DETAILS
        // Internally a restTemplate call can be made to get the customer details
        // Order order = orderservice.getOrderDetails(orderId);
        // String customerName = order.getUser().getName;
        // String mobile = order.getUser().getMobile;
        JSONObject customer = new JSONObject();
        customer.put("name","+919999999999");
        customer.put("contact","Customer1");
        customer.put("email","abc@gmail.com");
        paymentLinkRequest.put("customer",customer);

        // 4. NOTIFICATION DETAILS
        JSONObject notify = new JSONObject();
        notify.put("sms",true);
        notify.put("email",true);
        paymentLinkRequest.put("reminder_enable",true);

        // 5. ORDER ITEMS
        JSONObject notes = new JSONObject();
        notes.put("Order items","1. Apple iPhone 15 Pro, 2. MacBook Air, 3. Apple Watch Series 7, 4. Apple AirPods Pro, 5. Apple HomePod Mini");
        paymentLinkRequest.put("notes",notes);

        // 6. CALLBACK URL
        paymentLinkRequest.put("callback_url","http://localhost:8080/payment/callback");
        paymentLinkRequest.put("callback_method","get");

        PaymentLink paymentLink = razorpayClient.paymentLink.create(paymentLinkRequest);

        // 7. RETURNING ONLY THE PAYMENT URL
        return paymentLink.get("short_url");
    }

    @Override
    public String getPaymentStatus(String orderId) {
        // Go to the DB
        // Check if it is present in the DB
        // If present, return the status
        // If not present, call the payment gateway, get the status and update the DB as well
        // return the status
        return null;
    }
}
