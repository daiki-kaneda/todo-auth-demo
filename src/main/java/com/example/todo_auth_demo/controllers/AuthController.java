package com.example.todo_auth_demo.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.todo_auth_demo.dtos.LoginRequest;
import com.example.todo_auth_demo.dtos.LoginResponse;
import com.example.todo_auth_demo.models.User;
import com.example.todo_auth_demo.services.UserService;
import com.google.firebase.auth.FirebaseAuthException;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/auth")
public class AuthController {
    private final UserService userManager;

    public AuthController(UserService userManager) {
        this.userManager = userManager;
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest request) throws FirebaseAuthException {
        User user = userManager.loadOrCreateUser(request.idToken());

        return ResponseEntity.ok(new LoginResponse(user.getUid(), "Login success!"));
    }
}
