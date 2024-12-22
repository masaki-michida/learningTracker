package com.example.learningTracker.controller;

import com.example.learningTracker.model.User;
import com.example.learningTracker.service.AuthService;
import com.example.learningTracker.dto.LoginRequest;
import com.example.learningTracker.dto.LoginResponse;
import com.example.learningTracker.dto.RegisterRequest;
import com.example.learningTracker.dto.RegisterResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "http://localhost:5173", allowCredentials = "true")
public class AuthController {

    @Autowired
    private AuthService authService;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody RegisterRequest request) {
        try {
            User user = convertToEntity(request);
            authService.registerUser(user);
            return ResponseEntity.ok(new RegisterResponse("ユーザー登録が完了しました"));
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(new RegisterResponse(e.getMessage()));
        }
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest) {
        try {
            User user = authService.authenticateUser(loginRequest.getEmail(), loginRequest.getPassword());
            String token = authService.generateToken(user);
            return ResponseEntity.ok(new LoginResponse("ログイン成功", token));
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new LoginResponse(e.getMessage(), null));
        }
    }

    private User convertToEntity(RegisterRequest request) {
        User user = new User();
        user.setUsername(request.getUsername());
        user.setEmail(request.getEmail());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        return user;
    }
} 