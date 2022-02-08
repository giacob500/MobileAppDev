package com.example.androidappb;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        CheckBox cbox1 = (CheckBox) findViewById(R.id.cbox1);
        cbox1.setOnClickListener(startListener);
        CheckBox cbox2 = (CheckBox) findViewById(R.id.cbox2);
        cbox1.setOnClickListener(startListener);
        CheckBox cbox3 = (CheckBox) findViewById(R.id.cbox3);
        cbox1.setOnClickListener(startListener);
    }

    private View.OnClickListener startListener = new View.OnClickListener() {
        //sharing a listener so need to check which clicked
        public void onClick(View v) {
            CheckBox cbox1 = (CheckBox) findViewById(R.id.cbox1);
            if (cbox1.isChecked()) {
                Toast.makeText(MainActivity.this, "checkbox1",
                        Toast.LENGTH_LONG).show();
                cbox1.setText("Yes - Checked");
            } else {
                cbox1.setText("No - Not checked");
            }
        }
    };

}