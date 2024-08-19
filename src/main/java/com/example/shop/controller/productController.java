package com.example.shop.controller;
import com.example.shop.entity.product;
import com.example.shop.service.productService;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/products")
public class productController {
    private final productService productService;

    @Autowired
    public productController(productService productService) {
        this.productService = productService;
    }

    @GetMapping
    @RequestMapping("/all")
    public List<product> getProducts() {
        return productService.getProducts();
    }

}
