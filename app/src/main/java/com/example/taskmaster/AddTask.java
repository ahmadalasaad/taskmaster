package com.example.taskmaster;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.amplifyframework.api.graphql.model.ModelMutation;
import com.amplifyframework.core.Amplify;
import com.amplifyframework.datastore.generated.model.Task;
import com.amplifyframework.datastore.generated.model.Team;

public class AddTask extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_task);

//        AppDatabase db=AppDatabase.getDbInstance(this.getApplicationContext());
        Button button =findViewById(R.id.addB);
button.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {
        EditText taskTitle=findViewById(R.id.titleF);
        EditText taskBody=findViewById(R.id.bodyF);
        EditText taskState=findViewById(R.id.stateF);

        String titleText=taskTitle.getText().toString();
        String bodyText=taskBody.getText().toString();
        String stateText=taskState.getText().toString();

        /*----------------------adding teams-----------------------*/
        Team teamA= Team.builder().teamName("a").build(); //team from aws
        Amplify.API.mutate(
                ModelMutation.create(teamA),
                response -> Log.i("MyAmplifyApp", "Added team with id: " + response.getData().getId()),
                error -> Log.e("MyAmplifyApp", "Create failed", error)
        );
        Team teamB= Team.builder().teamName("b").build(); //team from aws
        Amplify.API.mutate(
                ModelMutation.create(teamB),
                response -> Log.i("MyAmplifyApp", "Added team with id: " + response.getData().getId()),
                error -> Log.e("MyAmplifyApp", "Create failed", error)
        );

        Team teamC= Team.builder().teamName("c").build(); //team from aws
        Amplify.API.mutate(
                ModelMutation.create(teamC),
                response -> Log.i("MyAmplifyApp", "Added team with id: " + response.getData().getId()),
                error -> Log.e("MyAmplifyApp", "Create failed", error)
        );
        /*---------------------------------------------------*/
        RadioGroup teams=findViewById(R.id.teams);
        int teamNumber=teams.getCheckedRadioButtonId();
        RadioButton radioButton=findViewById(teamNumber);

        System.out.println("teamssssssssssssssssssssss"+teams.getCheckedRadioButtonId());
        Task t;
        if(radioButton.getText()=="A"){
             t = Task.builder().team(teamA).title(titleText).body(bodyText).state(stateText).build(); //task from aws

        }else if(radioButton.getText()=="B"){
             t = Task.builder().team(teamB).title(titleText).body(bodyText).state(stateText).build(); //task from aws

        }else {
             t = Task.builder().team(teamC).title(titleText).body(bodyText).state(stateText).build(); //task from aws
        }

        Amplify.API.mutate(
                ModelMutation.create(t),
                response -> Log.i("MyAmplifyApp", "Added Task with id: " + response.getData().getId()),
                error -> Log.e("MyAmplifyApp", "Create failed", error)
        );

        Toast.makeText(getApplicationContext(), "Task Added", Toast.LENGTH_LONG).show();
        finish();

    }
});

    }

}