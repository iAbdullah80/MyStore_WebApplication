package com.example.shop.controller;
import com.example.shop.entity.product;
import com.example.shop.service.productService;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("/")
public class test {
    private final productService productService;

    @Autowired
    public test(productService productService) {
        this.productService = productService;
    }

    @GetMapping
    @RequestMapping("/hello")
    public String getProducts() {
        return "hello";
    }
}
