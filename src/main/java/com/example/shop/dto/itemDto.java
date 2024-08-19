package com.example.shop.dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class itemDto {
    private Long id;
    private int number;
    private String name;
    private int price;
    private int basketCounter;

    public itemDto() {
    }

}
