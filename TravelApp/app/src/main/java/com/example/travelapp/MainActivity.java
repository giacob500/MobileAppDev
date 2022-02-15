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

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ArrayList<Place> places;

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

        Place hopewell_rocks = new Place(
                R.string.location_hopewell_rocks_name,
                R.drawable.hopewell_rocks,
                R.string.location_hopewell_rocks_info);

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

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        //Check which request we're responding to - only 1!
        if (requestCode == 1) {
            //Make sure request was successful
            if (resultCode == 1) {
                String returnString = data.getStringExtra("String");
                Toast.makeText(getBaseContext(),
                        "In main activity. String returned = " +
                                returnString, Toast.LENGTH_LONG).show();
            }
        }
    }

    private void loadPlaces() {
        places = new ArrayList<Place>();

        Place hopewell_rocks = new Place(
                R.string.location_hopewell_rocks_name,
                R.drawable.hopewell_rocks,
                R.string.location_hopewell_rocks_info);

        Place parliament = new Place(
                R.string.location_parliament_hill_name,
                R.drawable.parliament_hill,
                R.string.location_parliament_hill_info);

        Place niagara = new Place(
                R.string.location_niagara_falls_name,
                R.drawable.niagara_falls,
                R.string.location_niagara_falls_info);

        places.add(hopewell_rocks);
        places.add(parliament);
        places.add(niagara);
        // we repeat the same here, so we have more to see and get
        // a longer list to scroll through. But feel free to
        // add some more places on your own.
        places.add(hopewell_rocks);
        places.add(parliament);
        places.add(niagara);
        places.add(hopewell_rocks);
        places.add(parliament);
        places.add(niagara);
        places.add(hopewell_rocks);
        places.add(parliament);
        places.add(niagara);
        places.add(hopewell_rocks);
        places.add(parliament);
        places.add(niagara);
    }

}