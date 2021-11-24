package com.example.taskmaster;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import com.amplifyframework.datastore.generated.model.Task;

public class TaskDetail extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task_detail);


        TextView taskTitleLabel = findViewById(R.id.taskLabel);
        TextView bodyTask = findViewById(R.id.bodyTaask);
        TextView stateTask = findViewById(R.id.state);
        int id=getIntent().getIntExtra("id",0);

        taskTitleLabel.setText(getIntent().getStringExtra("title"));
        bodyTask.setText(getIntent().getStringExtra("body"));
        stateTask.setText(getIntent().getStringExtra("state"));
    }

}