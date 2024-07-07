package com.example.get_todo_mvvm;

import android.app.Application;
import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import java.util.ArrayList;
import java.util.List;

public class TodoViewModel extends AndroidViewModel {
    private TodoRepository repository;
    private MutableLiveData<List<Todo>> todos;
    private MutableLiveData<List<Todo>> filteredTodos;
    private MutableLiveData<Boolean> isLoading = new MutableLiveData<>();

    public TodoViewModel(@NonNull Application application) {
        super(application);
        repository = new TodoRepository(application);
        todos = repository.getTodos();
        filteredTodos = new MutableLiveData<>();
        isLoading.setValue(true);
        todos.observeForever(t -> {
            isLoading.setValue(false);
            filteredTodos.setValue(t);
        });
    }

    public LiveData<List<Todo>> getTodos() {
        return todos;
    }

    public LiveData<List<Todo>> getFilteredTodos() {
        return filteredTodos;
    }

    public LiveData<Boolean> getLoadingState() {
        return isLoading;
    }

    public void filterTodos(String query, String filter) {
        if (todos.getValue() == null) return;

        List<Todo> filteredList = new ArrayList<>();
        for (Todo todo : todos.getValue()) {
            boolean matchesQuery = todo.getTodo().toLowerCase().contains(query.toLowerCase());
            boolean matchesFilter = filter.equals("All")
                    || (filter.equals("Completed") && todo.isCompleted())
                    || (filter.equals("Not Completed") && !todo.isCompleted());

            if (matchesQuery && matchesFilter) {
                filteredList.add(todo);
            }
        }
        filteredTodos.setValue(filteredList);
    }
}
