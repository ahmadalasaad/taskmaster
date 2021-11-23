package com.example.taskmaster;

import android.content.Intent;
import android.os.Parcelable;
import android.provider.ContactsContract;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Update;

import com.amplifyframework.datastore.generated.model.Task;

import java.util.ArrayList;
import java.util.List;

public class TaskAdapter extends RecyclerView.Adapter<TaskAdapter.TaskViewHolder>{
    private List<com.amplifyframework.datastore.generated.model.Task> allTask=new ArrayList<com.amplifyframework.datastore.generated.model.Task>();

    public TaskAdapter(List<com.amplifyframework.datastore.generated.model.Task> allTask) {
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
//
//        ConstraintLayout constraintLayout=holder.itemView.findViewById(R.id.taskCon);
//
//        constraintLayout.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//
//                Intent goToTaskDetail =new Intent(view.getContext(),TaskDetail.class);
//
//                goToTaskDetail.putExtra("id",holder.task);
//                view.getContext().startActivity(goToTaskDetail);
//            }
//        });
//        View deleteButton=holder.itemView.findViewById(R.id.delete);
//        deleteButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                AppDatabase db=AppDatabase.getDbInstance(view.getContext());
//                db.taskDao().delete(holder.task);
//                view.onFinishTemporaryDetach();
//                view.getContext().startActivity(new Intent(view.getContext(),MainActivity.class));
//            }
//        });
    }

    @Override
    public int getItemCount() {
        return allTask.size();
    }

    public List<com.amplifyframework.datastore.generated.model.Task> getAllTask() {
        return allTask;
    }

    public void setAllTask(List<com.amplifyframework.datastore.generated.model.Task> allTask) {
        this.allTask = allTask;
    }
}
