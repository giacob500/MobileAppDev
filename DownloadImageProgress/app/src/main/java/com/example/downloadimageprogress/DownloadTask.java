package com.example.downloadimageprogress;

import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;


public class DownloadTask extends AsyncTask<String, Void, Bitmap> {

    private Context context;
    private ProgressDialog progressDialog;
    private ImageView imageView;

    public DownloadTask(ProgressDialog progressDialog, ImageView imageview, Context context) {
        this.imageView = imageview;
        this.context = context;

        this.progressDialog = progressDialog;
    }

    // Before the tasks execution
    protected void onPreExecute() {
        super.onPreExecute();
        // Display the progress dialog on async task start
        progressDialog.show();
    }

    // Do the task in background/non UI thread
    protected Bitmap doInBackground(String... urls) {

        HttpURLConnection connection;
        connection = null;

        URL url = stringToURL(urls[0]);

        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        try {
            // Initialize a new http url connection
            connection = (HttpURLConnection) url.openConnection();
            connection.connect();
            // Get the input stream from http url connection
            InputStream inputStream = connection.getInputStream();
            // Initialize a new BufferedInputStream from InputStream
            BufferedInputStream bufferedInputStream =
                    new BufferedInputStream(inputStream);
            // Convert BufferedInputStream to Bitmap object
            Bitmap bmp = BitmapFactory.decodeStream(bufferedInputStream);
            // Return the downloaded bitmap
            return bmp;

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            // Disconnect the http url connection
            connection.disconnect();
        }
        return null;
    }


    // When all async task have finished
    protected void onPostExecute(Bitmap result) {
        // Hide the progress dialog
        progressDialog.dismiss();

        // sanity check, whether we actually received the image.
        if (result != null) {
            // Display the downloaded image
            imageView.setImageBitmap(result);
        } else {
            // Something went wrong while downloading the image
            Toast.makeText(context, "Error", Toast.LENGTH_LONG).show();
        }
    }

    // A little helper method to convert string to url
    protected URL stringToURL(String urlString) {
        try {
            URL url = new URL(urlString);
            return url;
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        return null;
    }
}