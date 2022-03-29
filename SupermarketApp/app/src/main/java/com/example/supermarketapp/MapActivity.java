package com.example.supermarketapp;

import static android.content.ContentValues.TAG;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.AsyncTask;
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
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

public class MapActivity extends AppCompatActivity implements OnMapReadyCallback {
    //private ActivityMapBinding binding;
    FirebaseFirestore db;
    Spinner spType;
    String defaultTextForSpinner = "Select supermarket below";
    Button btnSelect;
    SupportMapFragment supportMapFragment;
    GoogleMap map;
    FusedLocationProviderClient fusedLocationProviderClient;
    double currentLat = 0, currentLong = 0;
    ArrayList<String> placeNameList;
    String selectedSupermarket;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);

        /*binding = ActivityMapBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());*/

        // Assign variable
        selectedSupermarket = "";
        spType = findViewById(R.id.sp_type);
        btnSelect = findViewById(R.id.bt_find);
        supportMapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.google_map);
        supportMapFragment.getMapAsync(this);

        // Initialize array of place name
        placeNameList = new ArrayList<String>();

        String[] simpleArray = {"Broughton", "Slateford Road", "Darly Road", "Edinburgh Easter Road", "Leith", "Leven", "Whitburn", "Granton", "Nicolson St"};

// Initialize Cloud Firestore
        db = FirebaseFirestore.getInstance();
        /*
        if (placeNameList.isEmpty()){
            db.collection("supermarkets")
                    .get()
                    .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                        @Override
                        public void onComplete(@NonNull Task<QuerySnapshot> task) {
                            if (task.isSuccessful()) {
                                for (QueryDocumentSnapshot document : task.getResult()) {
                                    Log.d("prova3", placeNameList.toString());
                                    placeNameList.add((String)document.get("name"));
                                }
                            } else {
                                Log.d(TAG, "Error getting documents: ", task.getException());
                            }
                            Log.d("prova3", "FINE: " + placeNameList.toString());
                        }
                    });
        }
       // Log.d("prova3", "yo");
// Convert ArrayList<String> in string[]
        simpleArray = new String[ placeNameList.size() ];
        placeNameList.toArray( simpleArray );
        for(int i = 0; i < simpleArray.length; i++){
            Log.d("prova3", simpleArray[i]);
        }*/
       // Log.d("prova3", String.valueOf(simpleArray.length) + " " + placeNameList.size());
// Set adapter on spinner
        spType.setAdapter(new ArrayAdapter<>(MapActivity.this, android.R.layout.simple_spinner_dropdown_item, simpleArray));

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

        btnSelect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // get selected position of marker
                int i = spType.getSelectedItemPosition();
                // Initialize url
                String url = "https://maps.googleapis.com/maps/api/place/nearbysearch/json" +  // Url
                "?location=" + currentLat + "," + currentLong + // Location latitude and longitude
                "&radius=5000" + // Nearby radius
                "&types=" + simpleArray[i] + // Place type
                "&sensor=true" + // Sensor
                "&key=" + getResources().getString(R.string.google_map_key); // Google map key

                ArrayAdapter myAdap = (ArrayAdapter) spType.getAdapter(); //cast to an ArrayAdapter
                int spinnerPosition = myAdap.getPosition(selectedSupermarket);
                spType.setSelection(spinnerPosition);
                //dioboiaseiqui

                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                intent.putExtra("chosenSupermarket", getString(R.string.chosen_supermarket_textView) + " " + selectedSupermarket);

                Intent intent2 = new Intent(getApplicationContext(), MainActivity.class);// New activity
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
                finish(); // Call once you redirect to another activity
                /*
                Intent myIntent = new Intent();
                myIntent.setClassName("com.example.supermarketapp", "com.example.supermarketapp.MainActivity");
                // for ex: your package name can be "com.example"
                // your activity name will be "com.example.Contact_Developer"
                startActivity(myIntent);
                */
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
                            // When marker is pressed
                            map.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {
                                @Override
                                public boolean onMarkerClick(@NonNull Marker marker) {
                                    selectedSupermarket = marker.getTitle();
                                    return false;
                                }
                            });
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

        // Check if data on Firestore already exist
        db.collection("supermarkets")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                //placeNameList.add((String) document.getData().get("name"));
                                LatLng supermarket = new LatLng(Double.parseDouble((String) document.getData().get("lat")), Double.parseDouble((String) document.getData().get("lng")));

                                googleMap.addMarker(new MarkerOptions()
                                        .position(supermarket)
                                        .title("" + (String) document.getData().get("name")));
                            }
                        } else {
                            Log.w(TAG, "Error getting documents.", task.getException());
                        }
                    }
                });


        // [START_EXCLUDE silent]
        //googleMap.moveCamera(CameraUpdateFactory.newLatLng(supermarket));
    }

    private String downloadUrl(String string) throws IOException {
        // Initialize url
        URL url = new URL(string);
        // Initialize connection
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        // Connect connection
        connection.connect();
        // Initialize input stream
        InputStream stream = connection.getInputStream();
        // Initialize buffer reader
        BufferedReader reader = new BufferedReader(new InputStreamReader(stream));
        // Initialize string builder
        StringBuilder builder = new StringBuilder();
        // Initialize string variable
        String line = "";
        // Use while loop
        while ((line = reader.readLine()) != null){
            // Append line
            builder.append(line);
        }
        // Get append data
        String data = builder.toString();
        // Close reader
        reader.close();
        // Return data
        return data;
    }
}

