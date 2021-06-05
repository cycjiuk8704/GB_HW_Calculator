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
    public static int calcAction = 0;
    private int orientation;
    CalcDataAndMethods calcDataAndMethods = new CalcDataAndMethods();
    TextView calcText;
    TextView resultCalcText;
    String result;
    boolean isEmpty = true;
    private boolean isSolid = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        orientation = Configuration.ORIENTATION_PORTRAIT;

        resultCalcText = findViewById(R.id.calcResultTextView);
        calcText = findViewById(R.id.calcTextView);

        Button buttonAC = findViewById(R.id.button_ac);
        Button buttonDel = findViewById(R.id.button_del);
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

        buttonAC.setOnClickListener(v -> {
            calcText.setText("0");
            resultCalcText.setText("");
            result = "0";
            isEmpty = true;
            calcAction = 0;
            calcDataAndMethods.setSolid(true);
        });

        buttonDel.setOnClickListener(v -> {
            calcDataAndMethods.pushButtonDel(calcText);
        });

        buttonPercent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calcDataAndMethods.pushButtonMath(1, calcText, resultCalcText);
            }
        });

        buttonDivide.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calcDataAndMethods.pushButtonMath(2, calcText, resultCalcText);
            }
        });

        buttonOne.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calcDataAndMethods.addNumber("1", calcText);
            }
        });

        buttonTwo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calcDataAndMethods.addNumber("2", calcText);
            }
        });

        buttonThree.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calcDataAndMethods.addNumber("3", calcText);
            }
        });

        buttonPoint.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calcDataAndMethods.pushButtonPoint(calcText);
            }
        });

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