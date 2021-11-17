package com.example.taskmaster;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

public class TaskDetail extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task_detail);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

        TextView taskTitleLabel = findViewById(R.id.taskLabel);
        TextView bodyTask = findViewById(R.id.bodyTaask);
        TextView stateTask = findViewById(R.id.state);
        AppDatabase db=AppDatabase.getDbInstance(this.getApplicationContext());
        int id=getIntent().getIntExtra("id",0);
        Task task=db.taskDao().getTask(id);

        taskTitleLabel.setText(task.getTitle());
        bodyTask.setText(task.getBody());
        stateTask.setText(task.getState());
    }
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                this.finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}