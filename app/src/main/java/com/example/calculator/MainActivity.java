package com.example.calculator;

import android.content.res.Configuration;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

public class MainActivity extends AppCompatActivity {

    private static final String CALCULATOR = "Calculator";
    private CalcDataAndMethods calcDataAndMethods = new CalcDataAndMethods();
    private TextView calcText;
    private TextView resultCalcText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setBackgroundImage(getResources().getConfiguration().orientation);

        resultCalcText = findViewById(R.id.calcResultTextView);
        calcText = findViewById(R.id.calcTextView);

        createButtons();


    }

    private void createButtons() {
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

        buttonAC.setOnClickListener(v -> calcDataAndMethods.pushButtonAC(resultCalcText, calcText));

        buttonDel.setOnClickListener(v -> calcDataAndMethods.pushButtonDel(calcText));

        buttonPercent.setOnClickListener(v -> calcDataAndMethods.pushButtonMath(CalcActions.PERCENT.getValue(), calcText, resultCalcText));

        buttonDivide.setOnClickListener(v -> calcDataAndMethods.pushButtonMath(CalcActions.DIVIDE.getValue(), calcText, resultCalcText));

        buttonOne.setOnClickListener(v -> calcDataAndMethods.addNumber(Numbers.ONE.getValue(), calcText));

        buttonTwo.setOnClickListener(v -> calcDataAndMethods.addNumber(Numbers.TWO.getValue(), calcText));

        buttonThree.setOnClickListener(v -> calcDataAndMethods.addNumber(Numbers.THREE.getValue(), calcText));

        buttonMultiply.setOnClickListener(v -> calcDataAndMethods.pushButtonMath(CalcActions.MULTIPLY.getValue(), calcText, resultCalcText));

        buttonFour.setOnClickListener(v -> calcDataAndMethods.addNumber(Numbers.FOUR.getValue(), calcText));

        buttonFive.setOnClickListener(v -> calcDataAndMethods.addNumber(Numbers.FIVE.getValue(), calcText));

        buttonSix.setOnClickListener(v -> calcDataAndMethods.addNumber(Numbers.SIX.getValue(), calcText));

        buttonMinus.setOnClickListener(v -> calcDataAndMethods.pushButtonMath(CalcActions.SUBTRACT.getValue(), calcText, resultCalcText));

        buttonSeven.setOnClickListener(v -> calcDataAndMethods.addNumber(Numbers.SEVEN.getValue(), calcText));

        buttonEight.setOnClickListener(v -> calcDataAndMethods.addNumber(Numbers.EIGHT.getValue(), calcText));

        buttonNine.setOnClickListener(v -> calcDataAndMethods.addNumber(Numbers.NINE.getValue(), calcText));

        buttonPLus.setOnClickListener(v -> calcDataAndMethods.pushButtonMath(CalcActions.ADD.getValue(), calcText, resultCalcText));

        buttonZero.setOnClickListener(v -> calcDataAndMethods.addNumber(Numbers.ZERO.getValue(), calcText));

        buttonPoint.setOnClickListener(v -> calcDataAndMethods.pushButtonPoint(calcText));

        buttonEqual.setOnClickListener(v -> calcDataAndMethods.pushButtonEquals(resultCalcText, calcText));
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle  instanceState) {
        super.onSaveInstanceState(instanceState);
        instanceState.putSerializable(CALCULATOR, calcDataAndMethods);
        calcDataAndMethods.setCalcTextData(calcText.getText().toString());
        calcDataAndMethods.setResultCalcTextData(resultCalcText.getText().toString());
    }

    @Override
    protected void onRestoreInstanceState(@NonNull Bundle  instanceState) {
        super.onRestoreInstanceState(instanceState);
        calcDataAndMethods = (CalcDataAndMethods) instanceState.getSerializable(CALCULATOR);
        calcText.setText(calcDataAndMethods.getCalcTextData());
        resultCalcText.setText(calcDataAndMethods.getResultCalcTextData());
    }

    @Override
    public void onConfigurationChanged(@NonNull Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        setBackgroundImage(newConfig.orientation);
    }

    private void setBackgroundImage(final int orientation) {
        ConstraintLayout layout;
        layout = findViewById(R.id.mainLayout);

        if (orientation == Configuration.ORIENTATION_LANDSCAPE)
            layout.setBackgroundResource(R.drawable.calc_bg_h);
        else if (orientation == Configuration.ORIENTATION_PORTRAIT)
            layout.setBackgroundResource(R.drawable.calc_bg_v);
    }


}