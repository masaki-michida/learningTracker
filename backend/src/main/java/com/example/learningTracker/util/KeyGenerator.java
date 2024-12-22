package com.example.learningTracker.util;

import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.SignatureAlgorithm;

public class KeyGenerator {
    public static void main(String[] args) {
        byte[] key = Keys.secretKeyFor(SignatureAlgorithm.HS256).getEncoded();
        String base64Key = java.util.Base64.getEncoder().encodeToString(key);
        System.out.println("Generated Key: " + base64Key);
    }
} 