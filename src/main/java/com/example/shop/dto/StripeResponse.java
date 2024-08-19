package com.example.shop.dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class StripeResponse {
    private String sessionId;
    private int invoice;

    public StripeResponse(String sessionId, int invoice) {
        this.sessionId = sessionId;
        this.invoice = invoice;

    }

}