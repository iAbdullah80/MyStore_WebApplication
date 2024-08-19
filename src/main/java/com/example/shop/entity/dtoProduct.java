package com.example.shop.entity;

public class dtoProduct {
    private Long id;
    private int number;
    private String name;
    private String description;
    private String image;
    private int price;
    private int review;

    public dtoProduct() {
    }

    public dtoProduct(Long id,int number,  String name, String description, String image, int price, int review) {
        this.id = id;
        this.number = number;
        this.name = name;
        this.description = description;
        this.image = image;
        this.price = price;
        this.review = review;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getReview() {
        return review;
    }

    public void setReview(int review) {
        this.review = review;
    }
}
