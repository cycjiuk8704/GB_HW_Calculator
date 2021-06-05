package com.example.calculator;

import android.annotation.SuppressLint;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

import java.math.BigDecimal;
import java.util.Locale;

public class CalcDataAndMethods extends AppCompatActivity {
    boolean isEmpty = true;
    private boolean isSolid = true;
    private int decimalCounter = 0;
    private String number;
    private char lastChar;
    private BigDecimal firstVal;
    private TextView resultCalcText;
    private int calcAction = 0;

    public void setSolid(boolean solid) {
        isSolid = solid;
    }

    public void addNumber (String num, TextView calcText){
            number = number + num;
            calcText.append(num);
    }

    @SuppressLint("SetTextI18n")
    public void pushButtonMath (int calcAction, TextView calcText, TextView resultCalcText) {
        if (calcText.getText().toString().equals("")) {
            return;
        } else if (number.equals("0") && MainActivity.calcAction != 0 && calcAction == 2) {
            resultCalcText.setText("division by zero");
            MainActivity.calcAction = 0;
        } else if (MainActivity.calcAction != 0){
            BigDecimal result = calculate(MainActivity.calcAction);
            firstVal = result;
            resultCalcText.setText(String.format(Locale.getDefault(), "%.9f", result));


        } else {
            BigDecimal firstVal = BigDecimal.valueOf(Double.parseDouble(number));
            number = "0";
        }
        MainActivity.calcAction = calcAction;
        isEmpty = true;
        calcText.setText("");
    }

    @SuppressLint("SetTextI18n")
    private BigDecimal calculate(int calcAction) {
        BigDecimal secondVal = BigDecimal.valueOf(Double.parseDouble(number));
        number = "0";
        switch (calcAction) {
            case (1):
                return firstVal.multiply(secondVal.divide(BigDecimal.valueOf(100.0d),9));
            case (2):
                return firstVal.divide(secondVal,9);
            case (3):
                return firstVal.multiply(secondVal);
            case (4):
                return firstVal.subtract(secondVal);
            case (5):
                return firstVal.add(secondVal);
            default: throw new UnsupportedOperationException("unsupported calcAction value");
        }
    }

    public void pushButtonDel (TextView calcText) {
        if (calcText.getText().toString().equals("")) {
            return;
        } else {
            calcText.setText(calcText.getText().subSequence(0, calcText.getText().length() - 1));
            number.substring(0, number.length() - 1);
            isSolid = true;
        }
    }

    public void pushButtonPoint (TextView calcText) {
        if (isSolid) {
            isSolid = false;
            calcText.append(",");
            number += ".";
        } else {
            return;
        }
    }
}
