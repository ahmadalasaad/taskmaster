package com.example.taskmaster;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.SharedPreferences;
import android.media.Image;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.preference.PreferenceManager;
import android.util.Half;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.amplifyframework.AmplifyException;
import com.amplifyframework.api.aws.AWSApiPlugin;
import com.amplifyframework.api.graphql.model.ModelMutation;
import com.amplifyframework.api.graphql.model.ModelQuery;
import com.amplifyframework.core.Amplify;
import com.amplifyframework.datastore.AWSDataStorePlugin;
import com.amplifyframework.datastore.generated.model.Team;
import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton;
import com.amplifyframework.datastore.generated.model.Task;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class MainActivity extends AppCompatActivity {
//public     List<Task> listDynamo=new ArrayList<>();
public Set<String> teamNameList=new HashSet<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        try {
            // Add these lines to add the AWSApiPlugin plugins
            Amplify.addPlugin(new AWSApiPlugin());
            Amplify.configure(getApplicationContext());

            Log.i("TaskMaster", "Initialized Amplify");
        } catch (AmplifyException error) {
            Log.e("TaskMaster", "Could not initialize Amplify", error);
        }

        /*----------------------adding teams-----------------------*/
//        Team teamA= Team.builder().teamName("a").build(); //team from aws
//        Amplify.API.mutate(
//                ModelMutation.create(teamA),
//                response -> Log.i("MyAmplifyApp", "Added team with id: " + response.getData().getId()),
//                error -> Log.e("MyAmplifyApp", "Create failed", error)
//        );
//        Team teamB= Team.builder().teamName("b").build(); //team from aws
//        Amplify.API.mutate(
//                ModelMutation.create(teamB),
//                response -> Log.i("MyAmplifyApp", "Added team with id: " + response.getData().getId()),
//                error -> Log.e("MyAmplifyApp", "Create failed", error)
//        );
//
//        Team teamC= Team.builder().teamName("c").build(); //team from aws
//        Amplify.API.mutate(
//                ModelMutation.create(teamC),
//                response -> Log.i("MyAmplifyApp", "Added team with id: " + response.getData().getId()),
//                error -> Log.e("MyAmplifyApp", "Create failed", error)
//        );
        /*---------------------------------------------------*/
        ExtendedFloatingActionButton addTaskButton = findViewById(R.id.extended_fab);
        ExtendedFloatingActionButton allTaskButton = findViewById(R.id.extended_fab_all);
        View settingsButton = findViewById(R.id.settings);


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
    protected  void onResume() {
        super.onResume();

        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(MainActivity.this);
        String userName = sharedPreferences.getString("userName","User Name");
        String teamName = sharedPreferences.getString("teamName","team name");
        TextView previewUserName = findViewById(R.id.userNamePrev);
        TextView previewTeamName = findViewById(R.id.teamNameView);
        previewUserName.setText(userName);
        previewTeamName.setText(teamName);

        /*----------------------------------------------*/
        Amplify.API.query(
                ModelQuery.list(Team.class),
                response -> {
                    for (Team team : response.getData()) {
                        Log.i("teamName",team.getTeamName());
                        teamNameList.add(team.getTeamName());
                    }
                    SharedPreferences sharedPreferences1 = PreferenceManager.getDefaultSharedPreferences(MainActivity.this);
                    sharedPreferences1.edit().putStringSet("teams",teamNameList).apply();
                },

                error -> Log.e("TaskMaster", error.toString(), error)
        );


        /*----------------------------------------------------*/
        RecyclerView recyclerView = findViewById(R.id.allTasksView);
        Handler handler = new Handler(Looper.getMainLooper(), new Handler.Callback() {

            @Override
            public boolean handleMessage(@NonNull Message message) {
                recyclerView.getAdapter().notifyDataSetChanged();
                return false;
            }
        });
        List<Task> allTask = new ArrayList<>();
        Amplify.API.query(
                ModelQuery.list(Task.class),
                response -> {
                    for (Task task : response.getData()) {
                        if (task.getTeamName().equals(teamName)) {
                            allTask.add(task);
                        }
                    }
                    handler.sendEmptyMessage(1);
                },
                error -> Log.e("TaskMaster", error.toString(), error)
        );

        recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));
        recyclerView.setAdapter(new TaskAdapter(allTask));
        /*--------------------------------------*/


    }
}

