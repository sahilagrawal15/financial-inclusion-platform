package com.finclusion.userservice.controller;

import com.finclusion.userservice.dto.LoginRequest;
import com.finclusion.userservice.dto.RegisterRequest;
import com.finclusion.userservice.service.UserService;
import com.finclusion.userservice.util.JwtUtil;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.finclusion.userservice.util.JwtUtil;
import io.jsonwebtoken.Claims;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    private final JwtUtil jwtUtil;

    @PostMapping("/register")
    public ResponseEntity<String> registerUser(@Valid @RequestBody RegisterRequest request) {
        userService.registerUser(request);
        return ResponseEntity.ok("User Registered Successfully!");
    }

    @PostMapping("/login")
    public ResponseEntity<String> loginUser(@Valid @RequestBody LoginRequest request) {
        String token = userService.login(request);
        return ResponseEntity.ok(token);
    }

    @GetMapping("/user/verify/{userId}")
    public ResponseEntity<Boolean> verifyUser(@PathVariable String userId, @RequestHeader("Authorization") String authHeader) {
        Claims claims = jwtUtil.validateToken(authHeader.substring(7));
        String tokenUserId = claims.getSubject();
        return ResponseEntity.ok(userId.equals(tokenUserId));
    }



    //Test api for security
    @GetMapping("/hello")
    public String hello() {
        return "Hello";
    }

}
