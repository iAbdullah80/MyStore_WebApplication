package com.example.shop.repository;

import com.example.shop.entity.product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface productRepository extends JpaRepository<product, Long> {

    void deleteByNumber(int number);
}
