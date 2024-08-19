package com.example.shop.service;

import com.example.shop.dto.itemDto;
import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.checkout.Session;
import com.stripe.param.checkout.SessionCreateParams;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class orderService {
    @Value("${BASE_URL}")
    private String baseURL;

    @Value("${STRIPE_SECRET_KEY}")
    private String apiKey;
    public Session createSession(List<itemDto> itemDto) throws  StripeException {
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
                .build();
        return Session.create(params);
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
