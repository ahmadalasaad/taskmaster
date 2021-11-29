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
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.amplifyframework.core.Amplify;
import com.amplifyframework.datastore.generated.model.Task;
import com.amplifyframework.storage.options.StorageDownloadFileOptions;

import java.io.File;

public class TaskDetail extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task_detail);


        TextView taskTitleLabel = findViewById(R.id.taskLabel);
        TextView bodyTask = findViewById(R.id.bodyTaask);
        TextView stateTask = findViewById(R.id.state);
        int id=getIntent().getIntExtra("id",0);

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
    }

}