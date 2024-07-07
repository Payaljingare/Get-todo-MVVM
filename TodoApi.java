package com.example.get_todo_mvvm;



import java.util.List;
import retrofit2.Call;
import retrofit2.http.GET;

public interface TodoApi {
    @GET("todos")
    Call<TodoResponse> getTodos();
}

