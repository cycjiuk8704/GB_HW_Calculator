package com.example.calculator;

import android.annotation.SuppressLint;
import android.text.TextUtils;
import android.widget.TextView;

import java.io.Serializable;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.Locale;

public class CalcDataAndMethods implements Serializable {
    private String calcAction = "";
    private String calcTextData;
    private String resultCalcTextData;
    private boolean init = true;
    private boolean isInteger = true;
    private final String ZERO_VAL_STR = "0";
    private String number = ZERO_VAL_STR;
    private boolean calcResume = false;
    private BigDecimal firstVal = BigDecimal.ZERO;
    private BigDecimal secondVal = BigDecimal.ZERO;
    private BigDecimal result = BigDecimal.ZERO;

    public String getCalcTextData() {
        return calcTextData;
    }

    public void setCalcTextData(String calcTextData) {
        this.calcTextData = calcTextData;
    }

    public String getResultCalcTextData() {
        return resultCalcTextData;
    }

    public void setResultCalcTextData(String resultCalcTextData) {
        this.resultCalcTextData = resultCalcTextData;
    }

    public void addNumber(String num, TextView calcText) {
        if (number.equals(ZERO_VAL_STR)) {
            if (init) {
                number = num;
                calcText.setText(num);
                init = false;
                calcResume = false;
            } else {
                number = num;
                calcText.append(num);
            }
        } else {
            number = number + num;
            calcText.append(num);
        }

    }

    @SuppressLint("SetTextI18n")
    public void pushButtonMath(String calcAct, TextView calcText, TextView resultCalcText) {

        if (calcAction.isEmpty() && !firstVal.equals(BigDecimal.ZERO) && calcText.getText().toString().equals("")) {
            init = false;
            calcText.setText(String.format(Locale.getDefault(), "%s", formatOutput(firstVal)));
        } else if (calcText.getText().toString().equals("")) {
            return;
        } else if (calcText.getText().toString().equals(ZERO_VAL_STR) && calcAction.equals(CalcActions.DIVIDE.getValue())) {
            resultCalcText.setText("division by zero");
            calcAction = "";
            number = ZERO_VAL_STR;
            isInteger = true;
            firstVal = BigDecimal.ZERO;
            secondVal = BigDecimal.ZERO;
            result = BigDecimal.ZERO;
            init = true;
            calcText.setText("");

        } else if (!calcAction.isEmpty() && result.equals(BigDecimal.ZERO)) {
            result = calculate(calcAction);
            resultCalcText.setText(String.format(Locale.getDefault(), "%s", formatOutput(result)));
            firstVal = result;
            result = BigDecimal.ZERO;

        } else if (!calcAction.isEmpty()) {
            firstVal = result;
            result = calculate(calcAction);
            resultCalcText.setText(String.format(Locale.getDefault(), "%s", formatOutput(result)));
            result = BigDecimal.ZERO;

        } else if (!firstVal.equals(BigDecimal.ZERO) && calcResume) {
            calcText.setText(String.format(Locale.getDefault(), "%s", formatOutput(firstVal)));

        } else {
            firstVal = BigDecimal.valueOf(Double.parseDouble(number));
            number = ZERO_VAL_STR;
            isInteger = true;
        }
        calcResume = true;

        calcAction = calcAct;
        String calcActionText = calcText.getText().toString();
        if (!TextUtils.isEmpty(calcActionText)) {
            String lastChar = calcActionText.substring(calcActionText.length() - 1);
            if (CalcActions.contains(lastChar)){
                calcText.setText(calcText.getText().subSequence(0, calcText.getText().length() - 1));
            }
        }
        switch (calcAct) {
            case ("%"):
                calcText.append("%");
                break;
            case ("/"):
                calcText.append("/");
                break;
            case ("x"):
                calcText.append("x");
                break;
            case ("-"):
                calcText.append("-");
                break;
            case ("+"):
                calcText.append("+");
                break;
            default:
                throw new UnsupportedOperationException("unsupported calcAction value");
        }
    }

    @SuppressLint("SetTextI18n")
    private BigDecimal calculate(String calcAction) {
        if (number.equals(ZERO_VAL_STR)) {
            return firstVal;
        } else {
            secondVal = BigDecimal.valueOf(Double.parseDouble(number));
            number = ZERO_VAL_STR;
            switch (calcAction) {
                case ("%"):
                    return firstVal.multiply(secondVal.divide(BigDecimal.valueOf(100.0d), 9, BigDecimal.ROUND_HALF_UP));
                case ("/"):
                    return firstVal.divide(secondVal, 9, BigDecimal.ROUND_HALF_UP);
                case ("x"):
                    return firstVal.multiply(secondVal);
                case ("-"):
                    return firstVal.subtract(secondVal);
                case ("+"):
                    return firstVal.add(secondVal);
                default:
                    throw new UnsupportedOperationException("unsupported calcAction value");
            }
        }
    }

    public void pushButtonDel(TextView calcText) {
        if (!calcText.getText().toString().equals("") && !number.equals("")) {
            calcText.setText(calcText.getText().toString().subSequence(0, calcText.getText().toString().length() - 1));
            number = number.substring(0, number.length() - 1);
            isInteger = true;
        }
    }

    public void pushButtonPoint(TextView calcText) {
        if (isInteger) {
            isInteger = false;
            init = false;
            if (number.equals("") || calcText.getText().toString().equals("")) {
                calcText.append("0,");
                number += "0.";
            } else {
                calcText.append(",");
                number += ".";
            }
        }
    }

    public void pushButtonEquals(TextView resultCalcText, TextView calcText) {
        if (firstVal.equals(BigDecimal.ZERO)) {
            return;
        } else {
            result = calculate(calcAction);
            resultCalcText.setText(String.format(Locale.getDefault(), "%s", formatOutput(result)));
            firstVal = result;
            calcText.setText("");
        }
        number = ZERO_VAL_STR;
        isInteger = true;
        init = true;
        calcAction = "";
        secondVal = BigDecimal.ZERO;
        result = BigDecimal.ZERO;
        calcResume = true;
    }

    public void pushButtonAC(TextView resultCalcText, TextView calcText) {
        calcText.setText("");
        resultCalcText.setText("");
        calcAction = "";
        number = ZERO_VAL_STR;
        isInteger = true;
        init = true;
        firstVal = BigDecimal.ZERO;
        secondVal = BigDecimal.ZERO;
        result = BigDecimal.ZERO;
        calcResume = false;
    }

    private String formatOutput(BigDecimal bd) {
        DecimalFormat df = new DecimalFormat();
        df.setMaximumFractionDigits(9);
        df.setMinimumFractionDigits(0);
        df.setGroupingUsed(false);
        return df.format(bd);
    }


}
