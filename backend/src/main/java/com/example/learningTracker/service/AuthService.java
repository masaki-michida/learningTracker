package com.example.learningTracker.service;

import com.example.learningTracker.model.User;
import com.example.learningTracker.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class AuthService {
    @Autowired
    private UserRepository userRepository;
    
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;
    
    public User registerUser(User user) {
        // メールアドレスの重複チェック
        if (userRepository.findByEmail(user.getEmail()).isPresent()) {
            throw new RuntimeException("このメールアドレスは既に登録されています");
        }
        
        // パスワードのハッシュ化
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }
    
    public User authenticateUser(String email, String password) {
        return userRepository.findByEmail(email)
            .filter(user -> passwordEncoder.matches(password, user.getPassword()))
            .orElseThrow(() -> new RuntimeException("メールアドレスまたはパスワードが間違っています"));
    }
} 