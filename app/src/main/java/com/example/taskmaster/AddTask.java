package com.example.taskmaster;

import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.location.Location;
import android.net.Uri;
import android.os.Build;
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
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnSuccessListener;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

public class AddTask extends AppCompatActivity {
    public Task t;
    String key = null;
    Uri uri = null;
    private double lat;
    private double lon;
    private FusedLocationProviderClient fusedLocationClient;

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_task);

        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this);
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            requestPermissions(new String[]{Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION}, 1212);

        }else {
            fusedLocationClient.getLastLocation()
                    .addOnSuccessListener(this, new OnSuccessListener<Location>() {
                        @Override
                        public void onSuccess(Location location) {
                            if (location != null) {
                                    lat=location.getLatitude();
                                    lon=location.getLongitude();
                                Toast.makeText(AddTask.this, "Location: "+location, Toast.LENGTH_SHORT).show();
                            }
                            else{
                                Toast.makeText(AddTask.this, "null", Toast.LENGTH_SHORT).show();
                            }

                        }});


        }




        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(AddTask.this);
        Set<String> allTeams = sharedPreferences.getStringSet("teams", new HashSet<String>());


        AutoCompleteTextView menuView = findViewById(R.id.menu);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, R.layout.list_item, new ArrayList<>(allTeams));
        menuView.setAdapter(adapter);
        menuView.setInputType(InputType.TYPE_NULL);

        Button addTask = findViewById(R.id.addB);

        addTask.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText title = findViewById(R.id.titleF);
                EditText body = findViewById(R.id.bodyF);
                EditText state = findViewById(R.id.stateF);
                t = Task.builder().teamName(menuView.getText().toString()).title(title.getText().toString()).body(body.getText().toString()).state(state.getText().toString()).file(key).lat(lat).lon(lon).build();
                Amplify.API.mutate(
                        ModelMutation.create(t),
                        response -> Log.i("MyAmplifyApp", "Added Task with id: " + response.getData().getId()),
                        error -> Log.e("MyAmplifyApp", "Create failed", error)
                );
                Toast.makeText(getApplicationContext(), "Task Added", Toast.LENGTH_LONG).show();
                finish();
            }
        });


        Intent intent = getIntent();
        String action = intent.getAction();
        String type = intent.getType();
        if (Intent.ACTION_SEND.equals(action) && type != null) {
            if (type.startsWith("image/")) {
                handleSendImage(intent); // Handle single image being sent
            }
        }
    }

    void handleSendImage(Intent intent) {
        Uri imageUri = (Uri) intent.getParcelableExtra(Intent.EXTRA_STREAM);
        if (imageUri != null) {
            uploadInputStream(imageUri);
            Toast.makeText(getApplicationContext(),imageUri.getPath(),Toast.LENGTH_SHORT).show();
        }
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

            File file=new File(uri.getPath());
            key=file.getName();
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
