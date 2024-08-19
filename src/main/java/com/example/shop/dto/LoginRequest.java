package com.example.shop.dto;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@Setter

public class LoginRequest {

    private String username;
    private String password;
}
