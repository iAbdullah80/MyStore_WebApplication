package com.example.shop.service;

import com.example.shop.entity.product;
import com.example.shop.repository.InvoiceRepository;
import com.example.shop.repository.productRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class productService {

    private final productRepository productRepository;
    private final InvoiceRepository invoiceRepository;

    @Autowired
    public productService(productRepository productRepository, InvoiceRepository invoiceRepository) {
        this.productRepository = productRepository;
        this.invoiceRepository = invoiceRepository;
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

    public void deleteAllInvoices() {
        invoiceRepository.deleteAll();
    }
}
