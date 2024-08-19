package com.example.shop.dto;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {
    private Long id;
    @NotEmpty
    private String firstName;
    @NotEmpty
    private String lastName;
    @NotEmpty(message = "Email cannot be empty")
    @Email
    private String email;
    @NotEmpty(message = "Password cannot be empty")
    private String password;
}
