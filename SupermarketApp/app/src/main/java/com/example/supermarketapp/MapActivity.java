package com.example.supermarketapp;

import static android.content.ContentValues.TAG;

import android.Manifest;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class MapActivity extends AppCompatActivity implements OnMapReadyCallback {
    //private ActivityMapBinding binding;
    FirebaseFirestore db;
    Spinner spType;
    Button btFind;
    SupportMapFragment supportMapFragment;
    GoogleMap map;
    FusedLocationProviderClient fusedLocationProviderClient;
    double currentLat = 0, currentLong = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);

        /*binding = ActivityMapBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());*/

        // Assign variable
        spType = findViewById(R.id.sp_type);
        btFind = findViewById(R.id.bt_find);
        supportMapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.google_map);
        supportMapFragment.getMapAsync(this);

        // Until here the map works and sets a marker on sydeny


        // Initialize array of place type
        String[] placeTypeList = {"atm", "bank", "hospital", "movie_theater", "restaurant"};
        // Initialize array of place name
        String[] placeNameList = {"ATM", "Bank", "Hospital", "Movie Theater", "Restaurant"};

        // Set adapter on spinner
        spType.setAdapter(new ArrayAdapter<>(MapActivity.this, android.R.layout.simple_spinner_dropdown_item, placeNameList));

        // Initialize fused Location provider client
        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this);

        // Check permission
        if (ActivityCompat.checkSelfPermission(MapActivity.this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            // When permission granted
            // Call method
            getCurrentLocation();
        } else {
            // When permission denied
            // request permission
            ActivityCompat.requestPermissions(MapActivity.this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 44);
        }


/*// Add missing data
        Map<String, Object> supermarket = new HashMap<>();
        supermarket.put("name", "Darly Road");
        supermarket.put("lat", "55.94142632346264");
        supermarket.put("lng", "-3.221565654074121");
        supermarket.put("opening_times", "mon:8.00-22.00");

        db.collection("supermarkets_test")
                .add(supermarket);*/


        btFind.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // get selected position of marker

            }
        });
    }


    private void getCurrentLocation() {
        // Initialize task location
        Task<Location> task = fusedLocationProviderClient.getLastLocation();
        task.addOnSuccessListener(new OnSuccessListener<Location>() {
            @Override
            public void onSuccess(Location location) {
                //When success
                if (location != null) {
                    // When location is not equal to null
                    // Get current latitude
                    currentLat = location.getLatitude();
                    // Get current longitude
                    currentLong = location.getLongitude();
                    //Sync map
                    supportMapFragment.getMapAsync(new OnMapReadyCallback() {
                        @Override
                        public void onMapReady(@NonNull GoogleMap googleMap) {
                            // When map is ready
                            map = googleMap;
                            // Zoom current location on map
                            map.animateCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(currentLat, currentLong), 10));
                        }
                    });
                }
            }
        });
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == 44) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                // When permission granted
                // Call method
                getCurrentLocation();
            }
        }
    }

    @Override
    public void onMapReady(@NonNull GoogleMap googleMap) {
        // Initialize Cloud Firestore
        db = FirebaseFirestore.getInstance();

        // Check if data on Firestore already exist
        db.collection("supermarkets")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                // if document.getdata != supermarket in the list, add supermarket
                                Log.d("diocan2", "=>" + document.getData().get("lat"));

                                //String lat = (String) document.getData().get("lat");

                                LatLng supermarket = new LatLng(Double.parseDouble((String) document.getData().get("lat")), Double.parseDouble((String) document.getData().get("lng")));

                                googleMap.addMarker(new MarkerOptions()
                                        .position(supermarket)
                                        .title("Supermarket in " + (String) document.getData().get("name")));

                            }
                        } else {
                            Log.w(TAG, "Error getting documents.", task.getException());
                        }
                    }
                });



        // [START_EXCLUDE silent]
        //googleMap.moveCamera(CameraUpdateFactory.newLatLng(supermarket));
    }
}

