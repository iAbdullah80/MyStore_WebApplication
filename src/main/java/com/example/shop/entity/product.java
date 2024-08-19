package com.example.shop.entity;
import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
@Data
@Table
public class product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private int number;
    private String name;
    private String description;
    private String image;
    private int price;
    private int quantity;
    private int review;

    public product() {
    }

    public product(Long id,int number,  String name, String description, String image, int price, int quantity, int review) {
        this.id = id;
        this.number = number;
        this.name = name;
        this.description = description;
        this.image = image;
        this.price = price;
        this.quantity = quantity;
        this.review = review;
    }

public product(int number, String name, String description, String image, int price, int quantity, int review) {
        this.number = number;
        this.name = name;
        this.description = description;
        this.image = image;
        this.price = price;
        this.quantity = quantity;
        this.review = review;
    }

    @Override
    public String toString() {
        return "product{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", image='" + image + '\'' +
                ", price=" + price +
                ", quantity=" + quantity +
                ", review=" + review +
                '}';
    }
}
