package com.example.shop.controller;

import com.example.shop.service.orderService;
import com.stripe.exception.SignatureVerificationException;
import com.stripe.model.Event;
import com.stripe.model.EventDataObjectDeserializer;
import com.stripe.model.checkout.Session;
import com.stripe.net.Webhook;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.logging.Logger;

@RestController
@RequestMapping("/api/webhook")
public class WebhookController {

    private static final Logger logger = Logger.getLogger(WebhookController.class.getName());
    @Autowired
    private orderService orderService;

    @Value("${STRIPE.WEBHOOK.SECRET}")
    private String webhookSecret;

    @PostMapping("/stripe")
    public ResponseEntity<String> handleStripeWebhook(@RequestBody String payload, @RequestHeader("Stripe-Signature") String sigHeader) {
        try {   // webhook events such as charge.succeeded,payment.intent.created, payment.intent.succeeded, checkout.session.completed ...
                Event event = Webhook.constructEvent(payload, sigHeader, webhookSecret);
                logger.info("Webhook Event Type: "+
                        event.getType());

                // when processing checkout.session.completed event type is received execute the following
            if ("checkout.session.completed".equals(event.getType())) {
                // Deserialize the nested object inside the event
                EventDataObjectDeserializer dataObjectDeserializer = event.getDataObjectDeserializer();
                // check if the data object is present
                if (dataObjectDeserializer.getObject().isPresent()) {
                    // Cast the object to a session
                    Session session = (Session) dataObjectDeserializer.getObject().get();
                    // Get the session ID
                    String sessionId = session.getId();
                    // Update the invoice as paid
                    try {
                        // Update the invoice as paid
                        orderService.updateInvoiceAsPaid(sessionId);
                    } catch (Exception e) {
                        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error updating invoice: " + e.getMessage());
                    }
                }
            }
            return ResponseEntity.ok().body("Webhook processed successfully");

        } catch (SignatureVerificationException e) {
            logger.info("Invalid signature: "+ e);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid signature");
        } catch (Exception e) {
            logger.info("Error processing webhook: "+ e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Webhook error: " + e.getMessage());
        }
    }

}
