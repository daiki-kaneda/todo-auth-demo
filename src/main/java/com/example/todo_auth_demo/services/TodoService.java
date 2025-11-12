package com.example.todo_auth_demo.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.todo_auth_demo.models.Todo;
import com.example.todo_auth_demo.repositories.TodoRepository;

@Service
public class TodoService {
    private final TodoRepository todoRepository;

    public TodoService(TodoRepository todoRepository) {
        this.todoRepository = todoRepository;
    }

    public List<Todo> todos(String uid) {
        return todoRepository.findByUserUid(uid);
    }
}
