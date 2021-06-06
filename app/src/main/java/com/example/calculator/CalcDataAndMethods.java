package com.example.calculator;

import android.annotation.SuppressLint;
import android.widget.TextView;

import java.io.Serializable;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.Locale;

public class CalcDataAndMethods implements Serializable {
    private int calcAction = 0;
    private String calcTextData;
    private String resultCalcTextData;
    private boolean mathButtonDummy = false;
    private boolean init = true;
    private boolean isSolid = true;
    private String number = "0";
    private BigDecimal firstVal = new BigDecimal("0.0");
    private BigDecimal secondVal = new BigDecimal("0.0");
    private BigDecimal result = new BigDecimal("0.0");

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
        if (init && number.equals("0")) {
            number = num;
            calcText.setText(num);
            init = false;
        } else if (number.equals("0")) {
            number = num;
            calcText.append(num);
        } else {
            number = number + num;
            calcText.append(num);
        }

    }

    @SuppressLint("SetTextI18n")
    public void pushButtonMath(int calcAct, TextView calcText, TextView resultCalcText) {
        if (calcAction == 0 && !firstVal.equals(BigDecimal.valueOf(Double.parseDouble("0.0")))) {
            init = false;
            calcText.setText(String.format(Locale.getDefault(), "%s", formatOutput(firstVal)));
        } else if (calcText.getText().toString().equals("")) {
//            mathButtonDummy = true;
            return;
        } else if (number.equals("0") && calcAction == 2) {
            resultCalcText.setText("division by zero");
            calcAction = 0;
            number = "0";
            isSolid = true;
            firstVal = BigDecimal.valueOf(Double.parseDouble("0.0"));
            secondVal = BigDecimal.valueOf(Double.parseDouble("0.0"));
            result = BigDecimal.valueOf(Double.parseDouble("0.0"));
            init = true;
            calcText.setText("");
//            mathButtonDummy = false;

        } else if (calcAction != 0 && result.equals(BigDecimal.valueOf(Double.parseDouble("0.0")))) {
            result = calculate(calcAction);
            firstVal = result;
            resultCalcText.setText(String.format(Locale.getDefault(), "%s", formatOutput(result)));
//            isSolid = true;
            result = BigDecimal.valueOf(Double.parseDouble("0.0"));

        } else if (calcAction != 0) {
            firstVal = result;
            result = calculate(calcAction);
            resultCalcText.setText(String.format(Locale.getDefault(), "%s", formatOutput(result)));
//            isSolid = true;
            result = BigDecimal.valueOf(Double.parseDouble("0.0"));

        } else if (mathButtonDummy) {
            calcText.setText(calcText.getText().subSequence(0, calcText.getText().length() - 1));

        } else if (!firstVal.equals(BigDecimal.valueOf(Double.parseDouble("0.0")))) {
            calcText.setText(String.format(Locale.getDefault(), "%s", formatOutput(firstVal)));
//            mathButtonDummy = true;

        } else {
            firstVal = BigDecimal.valueOf(Double.parseDouble(number));
            number = "0";
            isSolid = true;
//            mathButtonDummy = true;
        }

        calcAction = calcAct;
        String chars = "%/x-+";
        if (chars.indexOf(calcText.getText().toString().charAt(calcText.getText().toString().length() - 1)) >= 0 ) {
            calcText.setText(calcText.getText().subSequence(0, calcText.getText().length() - 1));
        }
        switch (calcAct) {
            case (1):
                calcText.append("%");
                break;
            case (2):
                calcText.append("/");
                break;
            case (3):
                calcText.append("x");
                break;
            case (4):
                calcText.append("-");
                break;
            case (5):
                calcText.append("+");
                break;
            default:
                throw new UnsupportedOperationException("unsupported calcAction value");
        }
    }

    @SuppressLint("SetTextI18n")
    private BigDecimal calculate(int calcAction) {
        if (number.equals("0")) {
            return firstVal;
        } else {
            secondVal = BigDecimal.valueOf(Double.parseDouble(number));
//            mathButtonDummy = false;
            number = "0";
            switch (calcAction) {
                case (1):
                    return firstVal.multiply(secondVal.divide(BigDecimal.valueOf(100.0d), 9, BigDecimal.ROUND_HALF_UP));
                case (2):
                    return firstVal.divide(secondVal, 9, BigDecimal.ROUND_HALF_UP);
                case (3):
                    return firstVal.multiply(secondVal);
                case (4):
                    return firstVal.subtract(secondVal);
                case (5):
                    return firstVal.add(secondVal);
                default:
                    throw new UnsupportedOperationException("unsupported calcAction value");
            }
        }
    }

    public void pushButtonDel(TextView calcText) {
        if (!calcText.getText().toString().equals("") && !number.equals("")) {
            calcText.setText(calcText.getText().subSequence(0, calcText.getText().length() - 1));
            number = number.substring(0, number.length() - 1);
            isSolid = true;
        }
    }

    public void pushButtonPoint(TextView calcText) {
        if (isSolid) {
            isSolid = false;
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
        if (firstVal.equals(BigDecimal.valueOf(Double.parseDouble("0.0")))) {
            return;
        } else {
            result = calculate(calcAction);
            resultCalcText.setText(String.format(Locale.getDefault(), "%s", formatOutput(result)));
            firstVal = result;
            calcText.setText("");
        }
        number = "0";
        isSolid = true;
        init = true;
        calcAction = 0;
        secondVal = BigDecimal.valueOf(Double.parseDouble("0.0"));
        result = BigDecimal.valueOf(Double.parseDouble("0.0"));
    }

    public void pushButtonAC(TextView resultCalcText, TextView calcText) {
        calcText.setText("");
        resultCalcText.setText("");
        calcAction = 0;
        number = "0";
        isSolid = true;
        init = true;
        mathButtonDummy = false;
        firstVal = BigDecimal.valueOf(Double.parseDouble("0.0"));
        secondVal = BigDecimal.valueOf(Double.parseDouble("0.0"));
        result = BigDecimal.valueOf(Double.parseDouble("0.0"));
    }

    private String formatOutput(BigDecimal bd) {
        DecimalFormat df = new DecimalFormat();
        df.setMaximumFractionDigits(9);
        df.setMinimumFractionDigits(0);
        df.setGroupingUsed(false);
        return df.format(bd);
    }


}
