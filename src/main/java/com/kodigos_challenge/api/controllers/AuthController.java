package com.kodigos_challenge.api.controllers;

import com.kodigos_challenge.api.config.JwtUtil;
import com.kodigos_challenge.api.entities.User;
import com.kodigos_challenge.api.entities.UserRequestDto;
import com.kodigos_challenge.api.repositories.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private UsersRepository usersRepository;

    @Autowired
    private JwtUtil jwtUtil;

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody UserRequestDto user) {
        Optional<User> existingUser = usersRepository.findByUsername(user.username());
        if (existingUser.isPresent()) {
            return ResponseEntity.badRequest().body("User already exists");
        }

        User newUser = new User();
        newUser.setUsername(user.username());
        newUser.setPassword(user.password());

        usersRepository.save(newUser);
        return ResponseEntity.ok("User registered successfully");
    }

    @PostMapping("/login")
    public String login(@RequestBody User user) {
        Optional<User> foundUser = usersRepository.findByUsername(user.getUsername());
        if (foundUser.isPresent() && foundUser.get().getPassword().equals(user.getPassword())) {
            String token = jwtUtil.generateToken(user.getUsername());
            System.out.println("Token gerado: " + token);
            return token;
        } else {
            throw new RuntimeException("Credenciais inválidas");
        }
    }
}
