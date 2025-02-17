package com.TechFrotas.TechFrotas.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class LoginController {
    //http://localhost:8080/login
    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody Map<String, String> credentials) {
        String username = credentials.get("username");
        String password = credentials.get("password");

        if ("techFrotas@gmail.com".equals(username) && "admin123".equals(password)) {
            return ResponseEntity.ok("Login realizado com sucesso!");
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Credenciais inválidas!");
        }
    }
}