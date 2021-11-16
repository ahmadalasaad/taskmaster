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

//        Button codingButton = (Button)findViewById(R.id.coding);
//        Button gymButton = (Button)findViewById(R.id.gym);
//        Button relaxingButton = (Button)findViewById(R.id.relaxing);
        Button settingsButton = (Button)findViewById(R.id.settingsButton);
/*recycleView*/

        List<Task> allTasks = new ArrayList<Task>();
        allTasks.add(new Task("Coding","don't cry","in progress"));
        allTasks.add(new Task("Gym","work hard don't stop","new"));
        allTasks.add(new Task("Relaxing","get well sleep","complete"));



        RecyclerView recyclerView = findViewById(R.id.allTasksView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(new TaskAdapter(allTasks));



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

//        codingButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                String codingText = codingButton.getText().toString();
//                Intent goToTaskDetail =new Intent(MainActivity.this,TaskDetail.class);
//                goToTaskDetail.putExtra("taskName", codingText);
//                startActivity(goToTaskDetail);
//            }
//        });
//        gymButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                String gymText = gymButton.getText().toString();
//                Intent goToTaskDetail =new Intent(MainActivity.this,TaskDetail.class);
//                goToTaskDetail.putExtra("taskName", gymText);
//                startActivity(goToTaskDetail);
//            }
//        });
//        relaxingButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                String relaxingText = relaxingButton.getText().toString();
//                Intent goToTaskDetail =new Intent(MainActivity.this,TaskDetail.class);
//                goToTaskDetail.putExtra("taskName", relaxingText);
//                startActivity(goToTaskDetail);
//            }
//        });
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
    }
}