package com.example.todo_auth_demo.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.todo_auth_demo.dtos.TodosResponse;
import com.example.todo_auth_demo.models.Todo;
import com.example.todo_auth_demo.services.TodoService;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;

@RestController
@RequestMapping("/todos")
public class TodoController {

    private final TodoService todoService;

    public TodoController(TodoService todoService) {
        this.todoService = todoService;
    }

    @GetMapping
    public ResponseEntity<TodosResponse> todos(Authentication authentication) {
        String uid = (String) authentication.getPrincipal();
        List<Todo> todos = todoService.todos(uid);
        return ResponseEntity.ok(new TodosResponse(todos));
    }
}
