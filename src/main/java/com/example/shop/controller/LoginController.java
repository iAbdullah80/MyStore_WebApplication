package com.example.shop.controller;

import com.example.shop.dto.LoginRequest;
import com.example.shop.entity.User;
import com.example.shop.repository.UserRepo;
import com.example.shop.service.TokenService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.stream.Collectors;

@CrossOrigin("*")
@RestController
@RequestMapping("api/user")
public class LoginController {
    private final TokenService tokenService;
    private final UserRepo userRepo;

    public LoginController(TokenService tokenService, UserRepo userRepo) {
        this.tokenService = tokenService;
        this.userRepo = userRepo;
    }

    @GetMapping("/login")
    public ResponseEntity<String> login(Authentication authentication) {
        String token = tokenService.generateToken(authentication);
        return ResponseEntity.ok(token);
    }
    @GetMapping("/data")
    public ResponseEntity<String> data(Authentication authentication) {
        User user = userRepo.findByEmail(authentication.getName());
        return ResponseEntity.ok(user.getName());
    }

    @GetMapping("/test")
    public ResponseEntity<String> abdul(Authentication authentication) {
        String token = tokenService.generateToken(authentication);
        return ResponseEntity.ok(authentication.getName()+" logged out successfully and token is "+ token);
    }

    @GetMapping("/test2")
    public ResponseEntity<String> abdul2(Authentication authentication) {

        return ResponseEntity.ok(authentication.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority).collect(Collectors.joining(" "))+ " Hello " + authentication.getAuthorities());
    }
}

