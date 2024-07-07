package com.example.get_todo_mvvm;



import java.util.List;

public class TodoResponse {
    private List<Todo> todos;

    public List<Todo> getTodos() {
        return todos;
    }

    public void setTodos(List<Todo> todos) {
        this.todos = todos;
    }
}

