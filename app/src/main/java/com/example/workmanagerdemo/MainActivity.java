package com.example.workmanagerdemo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.work.Data;
import androidx.work.OneTimeWorkRequest;
import androidx.work.WorkManager;
import androidx.work.WorkRequest;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import java.io.File;

public class MainActivity extends AppCompatActivity {

    Button beginBtn;
    String TAG = "MainAct";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        beginBtn = (Button) findViewById(R.id.beginBtn);

        beginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                WorkRequest GreyScaleWorker =
                        new OneTimeWorkRequest.Builder(MyWorkerClass.class)
                                //add data to work request
                                .setInputData(
                                        new Data.Builder()
                                                .putString("MSG", "WorkManager Operating")
                                                .build()
                                )
                                //build work request
                                .build();
                //Start workmanager
                WorkManager
                        .getInstance(getApplicationContext())
                        .enqueue(GreyScaleWorker);
            }
        });
    }
}