package com.example.shop.controller;

import com.example.shop.dto.InvoiceDetailsDTO;
import com.example.shop.dto.ProductDetailsDTO;
import com.example.shop.entity.Invoice;
import com.example.shop.entity.User;
import com.example.shop.repository.InvoiceRepository;
import com.example.shop.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.stream.Collectors;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/invoices")
public class InvoiceController {

    private final InvoiceRepository invoiceRepository;
    private final UserRepo userRepository;

    @Autowired
    public InvoiceController(InvoiceRepository invoiceRepository, UserRepo userRepository) {
        this.invoiceRepository = invoiceRepository;
        this.userRepository = userRepository;
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<InvoiceDetailsDTO>> getUserInvoices(@PathVariable Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found"));

        List<Invoice> userInvoices = invoiceRepository.findByUserOrderByCreatedAtDesc(user);

        List<InvoiceDetailsDTO> invoiceDetailsDTOs = userInvoices.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());

        return ResponseEntity.ok(invoiceDetailsDTOs);
    }

    private InvoiceDetailsDTO convertToDTO(Invoice invoice) {
        InvoiceDetailsDTO dto = new InvoiceDetailsDTO();
        dto.setInvoiceNumber(invoice.getInvoiceNumber());
        dto.setTotalPrice(invoice.getTotalPrice());
        dto.setPaid(invoice.isPaid());

        List<ProductDetailsDTO> productDTOs = invoice.getItems().stream()
                .map(item -> {
                    ProductDetailsDTO productDTO = new ProductDetailsDTO();
                    productDTO.setName(item.getProductName());
                    productDTO.setQuantity(item.getQuantity());
                    productDTO.setPrice(item.getPrice());
                    return productDTO;
                })
                .collect(Collectors.toList());

        dto.setProducts(productDTOs);

        return dto;
    }
}