package com.example.get_todo_mvvm;



import android.app.Application;
import android.widget.Toast;

import androidx.lifecycle.MutableLiveData;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import java.util.List;

public class TodoRepository {
    private TodoApi todoApi;

    public TodoRepository(Application application) {
        todoApi = RetrofitClient.getTodoApi();
    }

    public MutableLiveData<List<Todo>> getTodos() {
        MutableLiveData<List<Todo>> todos = new MutableLiveData<>();
        todoApi.getTodos().enqueue(new Callback<TodoResponse>() {
            @Override
            public void onResponse(Call<TodoResponse> call, Response<TodoResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    todos.setValue(response.body().getTodos());
                }
            }

            @Override
            public void onFailure(Call<TodoResponse> call, Throwable t) {

                // Handle error
            }
        });
        return todos;
    }
}

