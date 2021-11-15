package com.example.taskmaster;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button addTaskButton = (Button)findViewById(R.id.addTask);
        Button allTaskButton = (Button)findViewById(R.id.allTasks);

        Button codingButton = (Button)findViewById(R.id.coding);
        Button gymButton = (Button)findViewById(R.id.gym);
        Button relaxingButton = (Button)findViewById(R.id.relaxing);
        Button settingsButton = (Button)findViewById(R.id.settingsButton);



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

        codingButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String codingText = codingButton.getText().toString();
                Intent goToTaskDetail =new Intent(MainActivity.this,TaskDetail.class);
                goToTaskDetail.putExtra("taskName", codingText);
                startActivity(goToTaskDetail);
            }
        });
        gymButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String gymText = gymButton.getText().toString();
                Intent goToTaskDetail =new Intent(MainActivity.this,TaskDetail.class);
                goToTaskDetail.putExtra("taskName", gymText);
                startActivity(goToTaskDetail);
            }
        });
        relaxingButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String relaxingText = relaxingButton.getText().toString();
                Intent goToTaskDetail =new Intent(MainActivity.this,TaskDetail.class);
                goToTaskDetail.putExtra("taskName", relaxingText);
                startActivity(goToTaskDetail);
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
        Toast.makeText(this, userName, Toast.LENGTH_LONG).show();
        TextView previewUserName = findViewById(R.id.userNamePrev);
        previewUserName.setText(userName);
    }
}