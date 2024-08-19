package com.example.shop.controller;

import com.example.shop.dto.StripeResponse;
import com.example.shop.dto.itemDto;
import com.example.shop.service.orderService;
import com.stripe.exception.StripeException;
import com.stripe.model.checkout.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Random;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/orders")
public class orderController {
    private final orderService orderService;

    public orderController(com.example.shop.service.orderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping("/create-checkout-session")
    public ResponseEntity<StripeResponse> createCheckoutSession(@RequestBody List<itemDto> itemDto) throws StripeException {

        Session session = orderService.createSession(itemDto);
        int randomNum = new Random().nextInt(1000) + 1;
        StripeResponse response = new StripeResponse(session.getId(), randomNum);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
