package com.example.calculator;

import android.content.res.Configuration;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import java.math.BigDecimal;

public class MainActivity extends AppCompatActivity {
    public static int calcAction = 0;
    private static final String CALCULATOR = "Calculator";
    private int orientation;
    CalcDataAndMethods calcDataAndMethods = new CalcDataAndMethods();
    TextView calcText;
    TextView resultCalcText;

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
            calcText.setText("");
            resultCalcText.setText("");
            calcAction = 0;
            calcDataAndMethods.setNumber("0");
            calcDataAndMethods.setSolid(true);
            calcDataAndMethods.setInit(true);
            calcDataAndMethods.setMathButtonDummy(false);
            calcDataAndMethods.setFirstVal(BigDecimal.valueOf(Double.parseDouble("0.0")));
            calcDataAndMethods.setSecondVal(BigDecimal.valueOf(Double.parseDouble("0.0")));
            calcDataAndMethods.setResult(BigDecimal.valueOf(Double.parseDouble("0.0")));
        });

        buttonDel.setOnClickListener(v -> calcDataAndMethods.pushButtonDel(calcText));

        buttonPercent.setOnClickListener(v -> calcDataAndMethods.pushButtonMath(1, calcText, resultCalcText));

        buttonDivide.setOnClickListener(v -> calcDataAndMethods.pushButtonMath(2, calcText, resultCalcText));

        buttonOne.setOnClickListener(v -> calcDataAndMethods.addNumber("1", calcText));

        buttonTwo.setOnClickListener(v -> calcDataAndMethods.addNumber("2", calcText));

        buttonThree.setOnClickListener(v -> calcDataAndMethods.addNumber("3", calcText));

        buttonMultiply.setOnClickListener(v -> calcDataAndMethods.pushButtonMath(3, calcText, resultCalcText));

        buttonFour.setOnClickListener(v -> calcDataAndMethods.addNumber("4", calcText));

        buttonFive.setOnClickListener(v -> calcDataAndMethods.addNumber("5", calcText));

        buttonSix.setOnClickListener(v -> calcDataAndMethods.addNumber("6", calcText));

        buttonMinus.setOnClickListener(v -> calcDataAndMethods.pushButtonMath(4, calcText, resultCalcText));

        buttonSeven.setOnClickListener(v -> calcDataAndMethods.addNumber("7", calcText));

        buttonEight.setOnClickListener(v -> calcDataAndMethods.addNumber("8", calcText));

        buttonNine.setOnClickListener(v -> calcDataAndMethods.addNumber("9", calcText));

        buttonPLus.setOnClickListener(v -> calcDataAndMethods.pushButtonMath(5, calcText, resultCalcText));

        buttonZero.setOnClickListener(v -> calcDataAndMethods.addNumber("0", calcText));

        buttonPoint.setOnClickListener(v -> calcDataAndMethods.pushButtonPoint(calcText));

        buttonEqual.setOnClickListener(v -> calcDataAndMethods.pushButtonEquals(resultCalcText, calcText));


    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle  instanceState) {
        super.onSaveInstanceState(instanceState);
        instanceState.putSerializable(CALCULATOR, calcDataAndMethods);
    }

    @Override
    protected void onRestoreInstanceState(@NonNull Bundle  instanceState) {
        super.onRestoreInstanceState(instanceState);
        calcDataAndMethods = (CalcDataAndMethods) instanceState.getSerializable(CALCULATOR);
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