package com.example.learningTracker.repository;

import com.example.learningTracker.model.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertTrue;

@DataJpaTest
public class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    @Test
    public void testFindByEmail() {
        // 事前にデータベースにユーザーを保存
        User user = new User();
        user.setEmail("test@example.com");
        user.setPassword("password");
        userRepository.save(user);

        // メールアドレスでユーザーを検索
        Optional<User> foundUser = userRepository.findByEmail("test@example.com");

        // ユーザーが見つかることを確認
        assertTrue(foundUser.isPresent());
    }
} 