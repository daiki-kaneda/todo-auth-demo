package com.example.todo_auth_demo.dtos;

import java.util.List;

import com.example.todo_auth_demo.models.Todo;

public record TodosResponse(List<Todo> todos){}
