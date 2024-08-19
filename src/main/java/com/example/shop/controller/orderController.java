package com.example.shop.controller;

import com.example.shop.dto.StripeResponse;
import com.example.shop.dto.itemDto;
import com.example.shop.entity.Invoice;
import com.example.shop.repository.InvoiceRepository;
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

    @Autowired
    private final orderService orderService;

    @Autowired
    private InvoiceRepository invoiceRepository;

    public orderController(com.example.shop.service.orderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping("/create-checkout-session")
    public ResponseEntity<StripeResponse> createCheckoutSession(@RequestBody List<itemDto> itemDto,
                                                                @RequestParam Long userId) throws StripeException {

        Session session = orderService.createSession(itemDto, userId);
        Invoice invoice = invoiceRepository.findBySessionId(session.getId())
                .orElseThrow(() -> new RuntimeException("Invoice not found"));

        StripeResponse response = new StripeResponse(session.getId(), invoice.getInvoiceNumber());
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
