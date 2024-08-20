package com.example.shop.repository;

import com.example.shop.entity.Invoice;
import com.example.shop.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface InvoiceRepository extends JpaRepository<Invoice, Long> {
    Optional<Invoice> findBySessionId(String sessionId);

    List<Invoice> findByUserOrderByCreatedAtDesc(User user);
}