package com.example.shop.service;

import com.example.shop.entity.product;
import com.example.shop.repository.productRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class productService {

    private final productRepository productRepository;

    @Autowired
    public productService(productRepository productRepository) {
        this.productRepository = productRepository;
    }


    public List<product> getProducts() {
        return productRepository.findAll();
    }

    public void addProduct(product product) {
        productRepository.save(product);
    }

    public void deleteProduct(Long id) {
        productRepository.deleteById(id);
    }

    public void deleteProductNumber(int number) {
        productRepository.deleteByNumber(number);
    }
}
