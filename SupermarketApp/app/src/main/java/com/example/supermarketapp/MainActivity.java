package com.example.supermarketapp;

import static android.content.ContentValues.TAG;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.supermarketapp.ui.SupermarketsHelperClass;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.recyclerview.widget.RecyclerView;

import com.example.supermarketapp.databinding.ActivityMainBinding;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;

    Button button;
    FirebaseFirestore db;

    // storing images and descriptions in list RecyclerView https://youtu.be/18VcnYN5_LM?t=285
    RecyclerView recyclerView;
    String s1[], s2[];
    int images[] = {R.drawable.first_leaflet, R.drawable.second_leaflet};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        BottomNavigationView navView = findViewById(R.id.nav_view);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.navigation_home, R.id.navigation_dashboard, R.id.navigation_notifications)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_activity_main);
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        NavigationUI.setupWithNavController(binding.navView, navController);

        button = (Button) findViewById(R.id.button2);

        // Initialize Cloud Firestore
        db = FirebaseFirestore.getInstance();

        recyclerView = findViewById(R.id.leaflets_list);
        s1 = getResources().getStringArray(R.array.leaflets);
        s2 = getResources().getStringArray(R.array.description);
    }

    // old attempt for database - DOESN'T WORK
    public void buttonPressed(View view) {
        Context context = getApplicationContext();
        CharSequence text = "Data uploaded";
        int duration = Toast.LENGTH_SHORT;

        // Write a message to the database
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("message");
        myRef.setValue("Hello, World!");
       /*
       SUGGESTED FROM THE INDIAN GUY TUTORIAL
       //connect to database
        FirebaseDatabase rootNode = FirebaseDatabase.getInstance();
        DatabaseReference reference = rootNode.getReference("supermarkets");

        //populate database
        SupermarketsHelperClass supermarket = new SupermarketsHelperClass("1", "Darly Road", "55.9415415332603, -3.2215872512215684", "mon:8.00-22.00");
        reference.child("1").setValue(supermarket);*/

        Toast toast = Toast.makeText(context, text, duration);
        toast.show();
    }

    // upload data on database
    public void buttonPressed2(View view) {
        Context context = getApplicationContext();
        CharSequence text = "Data uploaded";
        int duration = Toast.LENGTH_SHORT;

        // Create a new user with a first and last name
        Map<String, Object> user = new HashMap<>();
        user.put("first", "Ada");
        user.put("last", "Lovelace");
        user.put("born", 1815);

        // Add a new document with a generated ID
        db.collection("users")
                .add(user)
                .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                    @Override
                    public void onSuccess(DocumentReference documentReference) {
                        Log.d(TAG, "DocumentSnapshot added with ID: " + documentReference.getId());
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.w(TAG, "Error adding document", e);
                    }
                });
        Toast toast = Toast.makeText(context, text, duration);
        toast.show();

    }

    // check data on database
    public void buttonPressed3(View view) {
        db.collection("users")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                Log.d(TAG, "oooo: " + " => " + document.getData());
                            }
                        } else {
                            Log.w(TAG, "Error getting documents.", task.getException());
                        }
                    }
                });
    }

    public void downloadPDF1(View view) {
        File file = new File(Environment.getExternalStorageDirectory().getAbsolutePath()+"/storage/emulated/0/Download/sample.pdf");
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setDataAndType(Uri.fromFile(file), "application/pdf");
        intent.setFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
        startActivity(intent);
    }
}