package com.example.todo_auth_demo.security;

import java.io.IOException;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import com.example.todo_auth_demo.drivers.FirebaseAuthDriver;
import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.auth.FirebaseToken;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class FirebaseAuthFilter extends OncePerRequestFilter {

    private final FirebaseAuthenticationProvider authProvider;

    public FirebaseAuthFilter(FirebaseAuthenticationProvider authProvider){
        this.authProvider = authProvider;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        try {
            FirebaseToken token = FirebaseAuthDriver.verifyIdToken(resolveToken(request));
            Authentication auth = authProvider.createAuthentication(token);
            SecurityContextHolder.getContext().setAuthentication(auth);
        } catch (FirebaseAuthException e) {}
        filterChain.doFilter(request, response);
    }

    private String resolveToken(HttpServletRequest request) {
        String bearerToken = request.getHeader("Authorization");
        if (bearerToken != null && bearerToken.startsWith("Bearer ")) {
            return bearerToken.substring(7);
        }
        return null;
    }
}
