package com.example.taskmaster;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.preference.PreferenceManager;
import android.text.InputType;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.amplifyframework.api.graphql.model.ModelMutation;
import com.amplifyframework.api.graphql.model.ModelQuery;
import com.amplifyframework.core.Amplify;
import com.amplifyframework.datastore.generated.model.Task;
import com.amplifyframework.datastore.generated.model.Team;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

public class AddTask extends AppCompatActivity {
    public   Task t;
    String key=null;
    Uri uri = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_task);
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(AddTask.this);
        Set<String> allTeams= sharedPreferences.getStringSet("teams",new HashSet<String>());


        AutoCompleteTextView menuView = findViewById(R.id.menu);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, R.layout.list_item, new ArrayList<>(allTeams));
        menuView.setAdapter(adapter);
        menuView.setInputType(InputType.TYPE_NULL);

        Button addTask=findViewById(R.id.addB);

        addTask.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText title=findViewById(R.id.titleF);
                EditText body=findViewById(R.id.bodyF);
                EditText state=findViewById(R.id.stateF);
                t =Task.builder().teamName(menuView.getText().toString()).title(title.getText().toString()).body(body.getText().toString()).state(state.getText().toString()).file(key).build();
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


    @Override
    public void onActivityResult(int requestCode, int resultCode,
                                 Intent resultData) {
        super.onActivityResult(requestCode, resultCode, resultData);
        if (requestCode == 1 && resultCode == Activity.RESULT_OK) {
            if (resultData != null) {
                uri = resultData.getData();
                uploadInputStream(uri);
                Toast.makeText(getApplicationContext(),uri.getPath(),Toast.LENGTH_SHORT).show();
            }
        }
    }

    public void openfilechooser(View view){
        Intent intent=new Intent(Intent.ACTION_GET_CONTENT);
        intent.setType("*/*");
        startActivityForResult(intent,1);

    }
    private void uploadInputStream(Uri uri) {
        try {
            InputStream exampleInputStream = getContentResolver().openInputStream(uri);
            key=Double.toString(Math.random());
            Amplify.Storage.uploadInputStream(
                    key,
                    exampleInputStream,
                    result -> Log.i("MyAmplifyApp", "Successfully uploaded: " + result.getKey()),
                    storageFailure -> Log.e("MyAmplifyApp", "Upload failed", storageFailure)
            );
        }  catch (FileNotFoundException error) {
            Log.e("MyAmplifyApp", "Could not find file to open for input stream.", error);
        }
    }

}


//       Amplify.API.query(ModelQuery.get(Team.class,"1fbbd53f-0100-4e13-8a45-e4e1fe2c2910"),
//        response ->{
////                    Log.i("teamName",response.getData().getTeamName());
//        t=Task.builder().team(response.getData()).body("aaaaa").state("aa").title("aaaa").build();
//        },
//        error->Log.e("TaskMaster",error.toString(),error)
//        );
