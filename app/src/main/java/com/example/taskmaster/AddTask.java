package com.example.taskmaster;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.amplifyframework.api.graphql.model.ModelMutation;
import com.amplifyframework.core.Amplify;
import com.amplifyframework.datastore.generated.model.Task;

public class AddTask extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_task);

        AppDatabase db=AppDatabase.getDbInstance(this.getApplicationContext());
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

        Task t= Task.builder().title(titleText).body(bodyText).state(stateText).build(); //task from aws

        Amplify.API.mutate(
                ModelMutation.create(t),
                response -> Log.i("MyAmplifyApp", "Added Task with id: " + response.getData().getId()),
                error -> Log.e("MyAmplifyApp", "Create failed", error)
        );

//        db.taskDao().insertTask(new Task(titleText,bodyText,stateText));
        Toast.makeText(getApplicationContext(), "Task Added", Toast.LENGTH_LONG).show();
        finish();
    }
});

    }

}