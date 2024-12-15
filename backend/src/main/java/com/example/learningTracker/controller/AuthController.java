package com.example.learningTracker.controller;

import com.example.learningTracker.model.User;
import com.example.learningTracker.repository.UserRepository;
import com.example.learningTracker.dto.LoginRequest;
import com.example.learningTracker.dto.LoginResponse;
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

    private static final org.slf4j.Logger log = org.slf4j.LoggerFactory.getLogger(AuthController.class);

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody User user) {
        try {
            log.info("Registering new user with email: {}", user.getEmail());
            
            if (user.getEmail() == null || user.getPassword() == null || user.getUsername() == null) {
                return ResponseEntity
                    .badRequest()
                    .body(new RegisterResponse("必須項目が入力されていません"));
            }

            // メールアドレスの重複チェック
            if (userRepository.findByEmail(user.getEmail()).isPresent()) {
                log.info("Email already exists: {}", user.getEmail());
                return ResponseEntity
                    .badRequest()
                    .body(new RegisterResponse("このメールアドレスは既に登録されています"));
            }

            // パスワードのハッシュ化
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            User savedUser = userRepository.save(user);
            log.info("User registered successfully with id: {}", savedUser.getId());
            
            return ResponseEntity.ok(new RegisterResponse("ユーザー登録が完了しました"));
            
        } catch (Exception e) {
            log.error("Error during user registration", e);
            return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(new RegisterResponse("登録中にエラーが発生しました: " + e.getMessage()));
        }
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest) {
        return userRepository.findByEmail(loginRequest.getEmail())
            .filter(user -> passwordEncoder.matches(loginRequest.getPassword(), user.getPassword()))
            .map(user -> ResponseEntity.ok().body(new LoginResponse("ログイン成功")))
            .orElse(ResponseEntity.badRequest().body(new LoginResponse("メールアドレスまたはパスワードが間違っています")));
    }
} 