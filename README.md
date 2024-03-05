1. User --> OrderService:  order_id is created
2. User --(order_id)--> PaymentService: PaymentService fetches amount from OrderService using order_id and sends them to Payment Gateway to generate payment link
3. User --> Payment Link : Makes the payment and redirected to callback url
4. Backup to callback url --> PaymentGateway has PaymentService webhook URL

APIs need to be created:
1. createPaymentLink
2. getPaymentStatus
3. handleWebhookEvent