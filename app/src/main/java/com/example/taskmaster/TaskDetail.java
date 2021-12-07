package com.example.taskmaster;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.amplifyframework.core.Amplify;
import com.amplifyframework.datastore.generated.model.Task;
import com.amplifyframework.storage.options.StorageDownloadFileOptions;

import java.io.File;
import java.util.Locale;

public class TaskDetail extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task_detail);


        TextView taskTitleLabel = findViewById(R.id.taskLabel);
        TextView bodyTask = findViewById(R.id.bodyTaask);
        TextView stateTask = findViewById(R.id.state);

        Double altitude=getIntent().getDoubleExtra("lat",0);
        Double longitude = getIntent().getDoubleExtra("lon",0);
        Log.i("ahmad",altitude+"long");
        Log.i("ahmad",longitude+"lat");

        taskTitleLabel.setText(getIntent().getStringExtra("title"));
        bodyTask.setText(getIntent().getStringExtra("body"));
        stateTask.setText(getIntent().getStringExtra("state"));
        String key=getIntent().getStringExtra("key");
        TextView link=findViewById(R.id.link);
        if (key==null){
            link.setVisibility(View.INVISIBLE);
        }
        link.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Amplify.Storage.getUrl(
                        key,
                        result -> {
                            Log.i("MyAmplifyApp", "Successfully generated: " + result.getUrl());
                            Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(result.getUrl().toString()));
                            startActivity(browserIntent);
                        },
                        error -> Log.e("MyAmplifyApp", "URL generation failure", error)
                );

            }
        });

        Amplify.Storage.downloadFile(
                key,
                new File(getApplicationContext().getFilesDir() + "/download.txt"),
                result ->{ Log.i("MyAmplifyApp", "Successfully downloaded: " + result.getFile().getAbsolutePath());

                    File imgFile = new  File(result.getFile().getAbsolutePath());

                    if(imgFile.exists()){

                        Bitmap myBitmap = BitmapFactory.decodeFile(imgFile.getAbsolutePath());

                        ImageView taskimage=findViewById(R.id.taskimage);
                        taskimage.setImageBitmap(myBitmap);

                    }

                },
                error -> Log.e("MyAmplifyApp",  "Download Failure", error)
        );

        Button loc=findViewById(R.id.loc);
        loc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String uri = String.format(Locale.ENGLISH, "http://maps.google.com/maps?q=loc:%f,%f", altitude,longitude);
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(uri));
                startActivity(intent);
            }
        });

    }

}