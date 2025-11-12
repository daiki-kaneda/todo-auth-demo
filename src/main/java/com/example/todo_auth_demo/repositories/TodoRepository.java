package com.example.todo_auth_demo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.todo_auth_demo.models.Todo;

import java.util.List;


public interface TodoRepository extends JpaRepository<Todo, Long> {
    @Query("SELECT t FROM TODO t WHERE t.user.id = :uid ORDER BY t.id DESC")
    List<Todo> findByUserUid(@Param("uid") String uid);
}
