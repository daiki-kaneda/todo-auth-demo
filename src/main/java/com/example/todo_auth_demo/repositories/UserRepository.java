package com.example.todo_auth_demo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.todo_auth_demo.models.User;

public interface UserRepository extends JpaRepository<User, String> {}
