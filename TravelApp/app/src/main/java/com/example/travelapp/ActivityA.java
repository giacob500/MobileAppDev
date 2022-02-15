package com.example.travelapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;


public class ActivityA extends MainActivity {
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_a);
        ListView attractionDetails = (ListView) findViewById (R.id.attraction_list);

        Toast.makeText(getBaseContext(), "In Activity A", Toast.LENGTH_LONG).show();

        //Navigation back is via phones back button
        final String[] attractionDetailsArray = new String[] { "Fun fact 1",
                "Fun fact 2",
                "Fun fact 3",
                "4",
                "5",
                "6"};

        final ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1,
                android.R.id.text1,
                attractionDetailsArray);

        attractionDetails.setAdapter(adapter);
        String stringToReturn = "Message back from Activity A";
        Intent returnIntent = new Intent();
        returnIntent.putExtra("String", stringToReturn);
        setResult(1, returnIntent);

        /*String stringToReturn = "Edinburgh Napier University";
        Intent returnIntent = new Intent();
        returnIntent.putExtra("String", stringToReturn);
        setResult(1, returnIntent);
//        finish();
        // if you un-comment the line above,
        // the activity will close automatically.*/


    }
}