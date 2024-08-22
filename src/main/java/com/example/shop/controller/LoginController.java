package com.example.shop.controller;


import com.example.shop.entity.User;
import com.example.shop.repository.UserRepo;
import com.example.shop.service.TokenService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import java.util.HashMap;
import java.util.Map;

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
    @GetMapping("/data")
    public ResponseEntity<Map<String, Object>> data(Authentication authentication) {
        User user = userRepo.findByEmail(authentication.getName());
        Map<String, Object> responseData = new HashMap<>();
        responseData.put("id", user.getId());
        responseData.put("email", user.getEmail());
        responseData.put("name", user.getName());
        return ResponseEntity.ok(responseData);
    }
    @GetMapping("/logout")
    public ResponseEntity<String> logout(@RequestHeader("Authorization") String token) {
        if (token != null && token.startsWith("Bearer ")) {
            String jwtToken = token.substring(7);
            tokenService.revokeToken(jwtToken);
            return ResponseEntity.ok("Logged out successfully");
        }
        return ResponseEntity.badRequest().body("Invalid token");
    }
    @GetMapping("checkToken")
    public ResponseEntity<Boolean> checkToken() {
        return ResponseEntity.ok(true);
    }
}

