package com.example.downloadimageprogress;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    private ProgressDialog progressDialog;
    private ImageView imageView;
    private DownloadTask downloadTask;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        imageView = findViewById(R.id.imageView);

// Initialize the progress dialog
        progressDialog = new ProgressDialog(MainActivity.this);
        progressDialog.setIndeterminate(true);
// Progress dialog horizontal style
        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
// Progress dialog title
        progressDialog.setTitle(
                getResources().getString(R.string.download_dialog_title));
// Progress dialog message
        progressDialog.setMessage(
                getResources().getString(R.string.download_dialog_message));
        Button downloadButton = findViewById(R.id.downloadButton);
        downloadButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Create the async task
                downloadTask = new DownloadTask(
                        progressDialog,
                        imageView,
                        getApplicationContext()
                );
                // now execute the Task and pass the URL to download
                downloadTask.execute(
                        getResources().getString(R.string.download_url_napier_logo)
                );
            }
        });
        Button downloadButton2 = findViewById(R.id.downloadButton2);
        downloadButton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Create the async task
                downloadTask = new DownloadTask(
                        progressDialog,
                        imageView,
                        getApplicationContext()
                );
                // now execute the Task with
                downloadTask.execute(
                        getResources().getString(R.string.download_url_napier_chancellor)
                );
            }
        });


            }

}