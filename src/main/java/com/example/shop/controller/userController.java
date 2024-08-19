package com.example.shop.controller;

import com.example.shop.dto.UserDto;
import com.example.shop.entity.User;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import com.example.shop.service.UserService;

@CrossOrigin("*")
@RestController
@RequestMapping("/user")
public class userController {
    private final UserService userService;

    public userController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/signup")
    public ResponseEntity<String> signUp(@RequestBody UserDto user){
        System.out.println(user);
        if (userService.findByEmail(user.getEmail()) != null){
            return ResponseEntity.badRequest().body("There is already an account registered with that email");
        }

        userService.saveUser(user);
        return ResponseEntity.ok("User registered successfully");
    }
}
