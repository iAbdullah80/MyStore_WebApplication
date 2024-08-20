package com.example.shop.dto;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;


@Getter
@Setter
public class InvoiceDetailsDTO {
    private int invoiceNumber;
    private List<ProductDetailsDTO> products;
    private BigDecimal totalPrice;
    private boolean paid;

}
