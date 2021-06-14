package com.example.calclauncherbyintentfilter;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button buttonCalc = findViewById(R.id.calcLaunchButton);


        buttonCalc.setOnClickListener(v -> {
            Intent intent = new Intent("GB.HW.LMU.calculateSomething");
            startActivity(intent);
        });

    }
}