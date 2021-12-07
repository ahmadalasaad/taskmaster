package com.example.taskmaster;

import android.content.Intent;
import android.os.Parcelable;
import android.provider.ContactsContract;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Update;

import com.amplifyframework.api.graphql.model.ModelMutation;
import com.amplifyframework.core.Amplify;
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
        ConstraintLayout constraintLayout=holder.itemView.findViewById(R.id.taskCon);

        constraintLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent goToTaskDetail =new Intent(view.getContext(),TaskDetail.class);

                goToTaskDetail.putExtra("title",holder.task.getTitle());
                goToTaskDetail.putExtra("body",holder.task.getBody());
                goToTaskDetail.putExtra("state",holder.task.getState());
                goToTaskDetail.putExtra("key",holder.task.getFile());
                goToTaskDetail.putExtra("lat",holder.task.getLat());
                goToTaskDetail.putExtra("lon",holder.task.getLon());
                view.getContext().startActivity(goToTaskDetail);
            }
        });
/*--------------------delete function-------------------------------*/
          ImageView  deleteButton=holder.itemView.findViewById(R.id.delete);

          deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Amplify.API.mutate(
                        ModelMutation.delete(holder.task),
                        response -> Log.i("MyAmplifyApp", "delete Task with id: " + response.getData()),
                        error -> Log.e("MyAmplifyApp", "Create failed", error)
                );

                Toast.makeText(view.getContext(), "Task deleted", Toast.LENGTH_LONG).show();

                view.onFinishTemporaryDetach();
                view.getContext().startActivity(new Intent(view.getContext(),MainActivity.class));
            }
        });

      /*-----------------------------------------------*/
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
