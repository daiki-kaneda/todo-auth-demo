package com.example.todo_auth_demo.services;

import org.springframework.stereotype.Service;

import com.example.todo_auth_demo.drivers.FirebaseAuthDriver;
import com.example.todo_auth_demo.models.User;
import com.example.todo_auth_demo.repositories.UserRepository;
import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.auth.FirebaseToken;

@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User loadOrCreateUser(String idToken) throws FirebaseAuthException {
        FirebaseToken token = FirebaseAuthDriver.verifyIdToken(idToken);
        return userRepository.findById(token.getUid())
                .orElseGet(() -> {
                    User newUser = new User();
                    newUser.setUid(token.getUid());
                    newUser.setName((String) token.getClaims().get("name"));
                    return userRepository.save(newUser);
                });
    }

}
