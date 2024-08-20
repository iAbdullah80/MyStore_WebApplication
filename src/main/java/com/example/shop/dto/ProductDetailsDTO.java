package com.example.shop.dto;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;


@Getter
@Setter
public class ProductDetailsDTO {
    private String name;
    private int quantity;
    private BigDecimal price;
}
