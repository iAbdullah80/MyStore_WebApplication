package com.example.shop.service;

import com.example.shop.dto.itemDto;
import com.example.shop.entity.Invoice;
import com.example.shop.repository.InvoiceRepository;
import com.example.shop.repository.UserRepo;
import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.checkout.Session;
import com.stripe.param.checkout.SessionCreateParams;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Service
public class orderService {
    @Value("${BASE_URL}")
    private String baseURL;

    @Value("${STRIPE_SECRET_KEY}")
    private String apiKey;

    @Autowired
    private InvoiceRepository invoiceRepository;

    @Autowired
    private UserRepo userRepository;

    public void saveInvoice(Long userId, String sessionId, int invoiceNumber) {
        Invoice invoice = new Invoice();
        invoice.setUser(userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found")));
        invoice.setSessionId(sessionId);
        invoice.setInvoiceNumber(invoiceNumber);
        invoice.setPaid(false);
        invoice.setCreatedAt(LocalDateTime.now());
        invoiceRepository.save(invoice);
    }
    public void updateInvoiceAsPaid(String sessionId) {
        Invoice invoice = invoiceRepository.findBySessionId(sessionId)
                .orElseThrow(() -> new RuntimeException("Invoice not found"));
        invoice.setPaid(true);
        invoiceRepository.save(invoice) ;
    }
    private int generateInvoiceNumber() {
        // Implement a method to generate a unique invoice number
        return new Random().nextInt(1000000) + 1;
    }

    public Session createSession(List<itemDto> itemDto, Long userId) throws  StripeException {
        // sucess and failure urls

        String successURL = baseURL + "payment/success.html";

        String failureURL = baseURL + "payment/failed.html";

        Stripe.apiKey = apiKey;

        List<SessionCreateParams.LineItem> sessionItemList = new ArrayList<>();

        for (itemDto item : itemDto) {
            sessionItemList.add(createSessionLineItem(item));
        }

        SessionCreateParams params = SessionCreateParams.builder()
                .addPaymentMethodType(SessionCreateParams.PaymentMethodType.CARD)
                .setMode(SessionCreateParams.Mode.PAYMENT)
                .setSuccessUrl(successURL)
                .setCancelUrl(failureURL)
                .addAllLineItem(sessionItemList)
                .setClientReferenceId(userId.toString())
                .build();
        Session session = Session.create(params);
        saveInvoice(userId, session.getId(), generateInvoiceNumber());
        return session;
    }

    private SessionCreateParams.LineItem createSessionLineItem(itemDto item) {

        return SessionCreateParams.LineItem.builder()
                .setPriceData(createPriceData(item))
                .setQuantity(Long.parseLong(String.valueOf(item.getBasketCounter())))
                .build();
    }

    private SessionCreateParams.LineItem.PriceData createPriceData(itemDto item) {
        return SessionCreateParams.LineItem.PriceData.builder()
                .setCurrency("usd")
                .setUnitAmount((long)(item.getPrice() * 100))
                .setProductData(
                        SessionCreateParams.LineItem.PriceData.ProductData.builder()
                                .setName(item.getName())
                                .build()
                ).build();
    }
}
