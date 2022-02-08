package com.example.inputapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.InputFilter;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import android.content.Context;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.util.Log;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity {

    // Create a global variable for the system Location Manager and Listener
    private LocationManager locationManager;
    private LocationListener locationListener;
    // The identifier for which permission request has been answered.
    private static final int ACCESS_REQUEST_LOCATION = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.my_layout);

        EditText nameText = (EditText) findViewById(R.id.editText1);

        nameText.setFilters(new InputFilter[]{
                new InputFilter.LengthFilter(10),
                new InputFilter.AllCaps()
        });

        final EditText passwordField = (EditText) findViewById(R.id.editText2);

        Button submitButton = (Button) findViewById(R.id.button);
        submitButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                String text = passwordField.getText().toString();

                boolean valid = true;
                boolean hasNumbers = false;
                boolean hasLetters = false;
                int hasUppercaseLetter = 0;

                for (int i = 0; i < text.length(); i++) {
                    if (Character.isDigit(text.charAt(i))) {
                        hasNumbers = true;
                    } else if (Character.isLetter(text.charAt(i))) {
                        hasLetters = true;
                        if (Character.isUpperCase(text.charAt(i))) {
                            hasUppercaseLetter++;
                        }
                    } else {
                        valid = false;
                        break;
                    }
                }

                if (valid && hasLetters && hasNumbers && hasUppercaseLetter == 1) {
                    Toast.makeText(getBaseContext(), "Password " + text + " is valid.", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getBaseContext(), "Password " + text + " is not valid", Toast.LENGTH_SHORT).show();
                }
            }
        });

        // Acquire a reference to the system Location Manager
        locationManager = (LocationManager) this.getSystemService(Context.LOCATION_SERVICE);
        // Define a listener that responds to location updates
        locationListener = new LocationListener() {
            public void onLocationChanged(Location location) {
                // Called when a new location is found by the network location provider.
                if (location != null) {
                    String locationString =
                            "Location changed: Lat: " + location.getLatitude() +
                                    " Lng: " + location.getLongitude();

                    Toast.makeText(getBaseContext(), locationString, Toast.LENGTH_LONG).show();
                }
            }

            public void onStatusChanged(String provider, int status, Bundle extras) {
            }

            public void onProviderEnabled(String provider) {
            }

            public void onProviderDisabled(String provider) {
            }
        };

    }
}