package com.example.androidapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Date;
import java.util.concurrent.atomic.AtomicReference;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button buttonB = (Button)findViewById(R.id.button);
        buttonB.setOnClickListener(new View.OnClickListener(){
            //annonymous inner method called when buttonB clicked....
            public void onClick(View v) {

                TextView tv1 = (TextView)findViewById(R.id.textView);
                Log.d("FirstAppTag","onClick() called - start button");
                Toast.makeText(MainActivity.this, "The Start button was clicked.",
                        Toast.LENGTH_LONG).show();  //see below to find out more
                tv1.setText(new Date().toString());
            }
        });
    }

}
