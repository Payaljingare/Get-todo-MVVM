package com.example.get_todo_mvvm;



import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

public class TodoAdapter extends RecyclerView.Adapter<TodoAdapter.TodoViewHolder> {

    private List<Todo> todos;

    public void setTodos(List<Todo> todos) {
        this.todos = todos;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public TodoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_todo, parent, false);
        return new TodoViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull TodoViewHolder holder, int position) {
        Todo todo = todos.get(position);
        holder.todoTextView.setText(todo.getTodo());
        holder.completedTextView.setText(todo.isCompleted() ? "Completed" : "Not Completed");
    }

    @Override
    public int getItemCount() {
        return todos == null ? 0 : todos.size();
    }

    class TodoViewHolder extends RecyclerView.ViewHolder {
        private TextView todoTextView;
        private TextView completedTextView;

        public TodoViewHolder(@NonNull View itemView) {
            super(itemView);
            todoTextView = itemView.findViewById(R.id.todoTextView);
            completedTextView = itemView.findViewById(R.id.completedTextView);
        }
    }
}
