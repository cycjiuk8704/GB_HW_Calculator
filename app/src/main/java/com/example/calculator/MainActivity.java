package com.example.calculator;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.res.Configuration;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Locale;

public class MainActivity extends AppCompatActivity {
    private int orientation;
    private float firstVal;
    private float secondVal;
    private int calcAction = 0;
    String result;
    private final TextView calcText = findViewById(R.id.calcTextView);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        orientation = Configuration.ORIENTATION_PORTRAIT;

        Button buttonAC = findViewById(R.id.button_AC);
        Button buttonDel = findViewById(R.id.button_Del);
        Button buttonPercent = findViewById(R.id.button_percent);
        Button buttonDivide = findViewById(R.id.button_divide);
        Button buttonOne = findViewById(R.id.button_1);
        Button buttonTwo = findViewById(R.id.button_2);
        Button buttonThree = findViewById(R.id.button_3);
        Button buttonMultiply = findViewById(R.id.button_multiply);
        Button buttonFour = findViewById(R.id.button_4);
        Button buttonFive = findViewById(R.id.button_5);
        Button buttonSix = findViewById(R.id.button_6);
        Button buttonMinus = findViewById(R.id.button_minus);
        Button buttonSeven = findViewById(R.id.button_7);
        Button buttonEight = findViewById(R.id.button_8);
        Button buttonNine = findViewById(R.id.button_9);
        Button buttonPLus = findViewById(R.id.button_plus);
        Button buttonZero = findViewById(R.id.button_0);
        Button buttonPoint = findViewById(R.id.button_point);
        Button buttonEqual = findViewById(R.id.button_equals);

        buttonAC.setOnClickListener(v -> calcText.setText("0"));

        buttonDel.setOnClickListener(v -> {
            if (calcAction != 0) {
                result = calculate();
                calcText.setText(result);
                firstVal = Float.parseFloat(calcText.getText().toString());
            }
            calcAction = 1;
            calcText.setText("0");
        });

        buttonPercent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        buttonDivide.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        buttonOne.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (calcText.getText().equals("0")) {
                    calcText.setText("1");
                } else {
                    calcText.append("1");
                }
            }
        });

        buttonOne.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (calcText.getText().equals("0")) {
                    calcText.setText("2");
                } else {
                    calcText.append("2");
                }
            }
        });

        buttonOne.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (calcText.getText().equals("0")) {
                    calcText.setText("3");
                } else {
                    calcText.append("3");
                }
            }
        });

    }

    private String calculate() {
        secondVal = Float.parseFloat(calcText.getText().toString());
        switch (calcAction) {
            case (1):
                return String.format(Locale.getDefault(), "%.9f", (firstVal * secondVal / 100));
            case (2):
                if (secondVal == 0){
                    calcAction = 0;
                    return "division by zero";
                } else {
                    return String.format(Locale.getDefault(), "%.9f", (firstVal / secondVal));
                }
            case (3):
                return String.format(Locale.getDefault(), "%.9f", (firstVal * secondVal));
            case (4):
                return String.format(Locale.getDefault(), "%.9f", (firstVal - secondVal));
            case (5):
                return String.format(Locale.getDefault(), "%.9f", (firstVal + secondVal));
        }
        return "0";
    }

    @Override
    public void onConfigurationChanged(@NonNull Configuration newConfig) {
        super.onConfigurationChanged(newConfig);

        orientation = newConfig.orientation;
        setBackgroundImage(newConfig.orientation);
    }

    private void setBackgroundImage(final int orientation) {
        ConstraintLayout layout;
        layout = findViewById(R.id.mainLayout);

        if (orientation == Configuration.ORIENTATION_LANDSCAPE)
            layout.setBackgroundResource(R.drawable.calc_bg_v);
        else if (orientation == Configuration.ORIENTATION_PORTRAIT)
            layout.setBackgroundResource(R.drawable.calc_bg_h);
    }

    @Override
    public void onResume() {
        super.onResume();
        setBackgroundImage(orientation);
    }
}