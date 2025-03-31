package com.finclusion.userservice.service;

import com.finclusion.userservice.dto.LoginRequest;
import com.finclusion.userservice.dto.RegisterRequest;
import com.finclusion.userservice.entity.User;
import com.finclusion.userservice.repository.UserRepository;
import com.finclusion.userservice.util.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final JwtUtil jwtUtil;


    public void registerUser(RegisterRequest request) {
        String userId = UUID.randomUUID().toString();

        User user = new User();
        user.setUserId(userId);
        user.setFullName(request.getFullName());
        user.setEmail(request.getEmail());
        user.setPassword(request.getPassword()); // (Later → hash password)

        userRepository.saveUser(user);
    }

    public String login(LoginRequest request) {
        User user = userRepository.getUserByEmail(request.getEmail());

        if (user == null || !user.getPassword().equals(request.getPassword())) {
            throw new RuntimeException("Invalid email or password");
        }

        // Generate JWT Token
        return jwtUtil.generateToken(user.getUserId(), user.getEmail());
    }
}
