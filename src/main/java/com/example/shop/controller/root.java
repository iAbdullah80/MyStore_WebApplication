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
@RequestMapping("/")
public class root {
    @Autowired
    private orderService orderService;

    @GetMapping("/")
    public String test() {
        return "hello";
    }

}
