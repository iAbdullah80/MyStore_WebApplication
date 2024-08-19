package com.example.shop.controller;

import com.example.shop.entity.product;
import com.example.shop.service.productService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/admin")
public class adminController {
    private final productService productService;

    @Autowired
    public adminController(productService productService) {
        this.productService = productService;
    }


    @PostMapping
    @RequestMapping("/add")
    public void addProduct(@RequestBody product product) {
        productService.addProduct(product);
    }

    @DeleteMapping
    @RequestMapping("/delete/id/{id}")
    public void deleteProduct(@PathVariable("id") Long id) {
        productService.deleteProduct(id);
    }
    @DeleteMapping
    @RequestMapping("/delete/num/{number}")
    public void deleteProduct(@PathVariable("number") int number) {
        productService.deleteProductNumber(number);
    }
}
