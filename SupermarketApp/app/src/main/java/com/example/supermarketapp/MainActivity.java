package com.example.supermarketapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.example.supermarketapp.databinding.ActivityMainBinding;
import com.example.supermarketapp.ui.home.HomeFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.firestore.FirebaseFirestore;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    Button button;
    FloatingActionButton fab;
    FirebaseFirestore db;
    String fetoAmbizioso;
    Bundle b;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        BottomNavigationView navView = findViewById(R.id.nav_view);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.navigation_home, R.id.navigation_leaflets, R.id.navigation_coupons)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_activity_main);
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        NavigationUI.setupWithNavController(binding.navView, navController);

        button = (Button) findViewById(R.id.choose_supermarket_button);
        fab = findViewById(R.id.fab);

        // Initialize Cloud Firestore
        db = FirebaseFirestore.getInstance();
    }

    private void loadFragment(HomeFragment fragment) {
        Bundle bundle = new Bundle();
        fetoAmbizioso = "" + getIntent().getStringExtra("chosenSupermarket");
        bundle.putString("chosenSupermarket1", fetoAmbizioso);
        fragment.setArguments(bundle);
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fm.beginTransaction();
        fragmentTransaction.replace(R.id.nav_host_fragment_container, fragment);
        fragmentTransaction.commit();
    }

    public void buttonPressed4(View v) {
        switch(v.getId()) {
            case R.id.choose_supermarket_button:
                Intent myIntent = new Intent();
                myIntent.setClassName("com.example.supermarketapp", "com.example.supermarketapp.MapActivity");
                // for ex: your package name can be "com.example"
                // your activity name will be "com.example.Contact_Developer"
                startActivity(myIntent);
                break;
        }
    }

    public void fabPressed (View v){
        // Open qr code
        Intent myIntent = new Intent();
        myIntent.setClassName("com.example.supermarketapp", "com.example.supermarketapp.QrCardActivity");
        // for ex: your package name can be "com.example"
        // your activity name will be "com.example.Contact_Developer"
        startActivity(myIntent);
    }

}