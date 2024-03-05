package org.example.paymentservice1.controllers;

import com.razorpay.RazorpayException;
import org.example.paymentservice1.dtos.CreatePaymentLinkRequestDto;
import org.example.paymentservice1.services.PaymentService;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/payment")
public class PaymentController {

    private PaymentService paymentService;

    public PaymentController(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    // API - 1: CREATE PAYMENT LINK
    @PostMapping("/")
    public String createPaymentLink(@RequestBody CreatePaymentLinkRequestDto createPaymentLinkRequestDto) throws RazorpayException {
        return paymentService.createPaymentLink(createPaymentLinkRequestDto.getOrderId());
    }

    // API - 2: GET PAYMENT STATUS
    @PostMapping("/status")
    public void getPaymentStatus(@RequestBody String orderId) {
        paymentService.getPaymentStatus(orderId);
    }

    // API - 3: HANDLE WEBHOOKS

    @PostMapping("/webhook")
    public void handleWebhookEvent(@RequestBody Map<String, String> webhookEvent){
        System.out.println("Webhook event received: " + webhookEvent);
    }

    // API - 4: CALLBACK URL
    @GetMapping("/callback")
    public String paymentCallback() {
        return "Callback successful!";
    }
}
