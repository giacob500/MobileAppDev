package com.example.travelapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;
import android.widget.RadioButton;
import android.widget.RadioGroup;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RadioButton rb0 = (RadioButton) findViewById(R.id.radioButton);
        rb0.setOnClickListener(radioGroupClick);

        RadioButton rb1 = (RadioButton) findViewById(R.id.radioButton2);
        rb1.setOnClickListener(radioGroupClick);

        RadioButton rb2 = (RadioButton) findViewById(R.id.radioButton3);
        rb2.setOnClickListener(radioGroupClick);

        Button button1 = (Button) findViewById(R.id.button);
        button1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent activityA = new Intent(MainActivity.this, ActivityA.class);
                startActivity(activityA);
            }
        });

    }

    private View.OnClickListener radioGroupClick = new View.OnClickListener() {
        public void onClick(View view) {
            RadioButton rb = (RadioButton) view;
            ImageView iv = (ImageView) findViewById(R.id.imageView);

            if (rb.getText().equals("Hopewell Rocks")) {
                iv.setImageResource(R.drawable.hopewell_rocks);
            } else if (rb.getText().equals("Niagara Falls")) {
                iv.setImageResource(R.drawable.niagara_falls);
            } else if (rb.getText().equals("Parliament Hill")) {
                iv.setImageResource(R.drawable.parliament_hill);
            }
        }
    };
}