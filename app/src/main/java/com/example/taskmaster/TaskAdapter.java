package com.example.taskmaster;

import android.content.Intent;
import android.os.Parcelable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class TaskAdapter extends RecyclerView.Adapter<TaskAdapter.TaskViewHolder>{
    private List<Task> allTask=new ArrayList<Task>();

    public TaskAdapter(List<Task> allTask) {
        this.allTask = allTask;
    }



    public static class TaskViewHolder extends RecyclerView.ViewHolder{
        public Task task;
        public View itemView;

        public TaskViewHolder(@NonNull View itemView) {
            super(itemView);
            this.itemView=itemView;
        }
    }
    @NonNull
    @Override
    public TaskViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_task,parent,false);
        TaskViewHolder taskViewHolder =new TaskViewHolder(view);
        return taskViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull TaskViewHolder holder, int position) {
        holder.task=allTask.get(position);

        TextView taskTitle = holder.itemView.findViewById(R.id.taskTitleInFragment);
        TextView taskBody = holder.itemView.findViewById(R.id.taskBodyInFragment);
        TextView taskState = holder.itemView.findViewById(R.id.taskStateInFragment);

        taskTitle.setText(holder.task.getTitle());
        taskBody.setText(holder.task.getBody());
        taskState.setText(holder.task.getState());

        ConstraintLayout constraintLayout=holder.itemView.findViewById(R.id.taskCon);

        constraintLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent goToTaskDetail =new Intent(view.getContext(),TaskDetail.class);
                goToTaskDetail.putExtra("taskName", holder.task.getTitle());
                view.getContext().startActivity(goToTaskDetail);
            }
        });

    }

    @Override
    public int getItemCount() {
        return allTask.size();
    }

    public List<Task> getAllTask() {
        return allTask;
    }

    public void setAllTask(List<Task> allTask) {
        this.allTask = allTask;
    }
}
