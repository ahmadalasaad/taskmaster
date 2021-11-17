package com.example.taskmaster;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;

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

//        TextView taskTitleLabel = findViewById(R.id.taskLabel);

        String title = getIntent().getExtras().get("title").toString();
        String body = getIntent().getExtras().get("body").toString();
        String state = getIntent().getExtras().get("state").toString();

        taskTitleLabel.setText(title);
        bodyTask.setText(body);
        stateTask.setText(state);
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