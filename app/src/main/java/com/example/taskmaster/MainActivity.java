package com.example.taskmaster;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button addTaskButton = (Button)findViewById(R.id.addTask);
        Button allTaskButton = (Button)findViewById(R.id.allTasks);
        Button settingsButton = (Button)findViewById(R.id.settingsButton);
/*recycleView*/
//        AppDatabase db=AppDatabase.getDbInstance(this.getApplicationContext());
//        RecyclerView recyclerView = findViewById(R.id.allTasksView);
//        recyclerView.setLayoutManager(new LinearLayoutManager(this));
//        recyclerView.setAdapter(new TaskAdapter(db.taskDao().getAll()));

        addTaskButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this,AddTask.class));
            }
        });
        allTaskButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this,AllTask.class));
            }
        });

        settingsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this,Settings.class));
            }
        });
    }
    @Override
    protected void onResume() {
        super.onResume();
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(MainActivity.this);
        String userName = sharedPreferences.getString("userName","User Name");
        TextView previewUserName = findViewById(R.id.userNamePrev);
        previewUserName.setText(userName);
        AppDatabase db=AppDatabase.getDbInstance(this.getApplicationContext());
        RecyclerView recyclerView = findViewById(R.id.allTasksView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(new TaskAdapter(db.taskDao().getAll()));

    }
}