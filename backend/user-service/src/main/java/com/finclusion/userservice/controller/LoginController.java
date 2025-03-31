package com.finclusion.userservice.controller;

import com.finclusion.userservice.dto.LoginRequest;
import com.finclusion.userservice.entity.User;
import com.finclusion.userservice.repository.UserRepository;
import com.finclusion.userservice.util.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class LoginController {

    private final UserRepository userRepository;
    private final JwtUtil jwtUtil;

    @PostMapping("/login")
    public String login(@RequestBody LoginRequest request) {
        User user = userRepository.getUserByEmail(request.getEmail());

        if (user == null || !user.getPassword().equals(request.getPassword())) {
            throw new RuntimeException("Invalid email or password");
        }

        return jwtUtil.generateToken(user.getUserId(), user.getEmail());
    }
}
