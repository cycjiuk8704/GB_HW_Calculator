package com.example.calculator;

import android.annotation.SuppressLint;
import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;


import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.Locale;

public class CalcDataAndMethods implements Parcelable {
    private String calcAction = "";
    private String calcTextData;
    private String resultCalcTextData;
    private boolean init = true;
    private boolean isInteger = true;
    public static final Creator<CalcDataAndMethods> CREATOR = new Creator<CalcDataAndMethods>() {
        @Override
        public CalcDataAndMethods createFromParcel(Parcel in) {
            return new CalcDataAndMethods(in);
        }

        @Override
        public CalcDataAndMethods[] newArray(int size) {
            return new CalcDataAndMethods[size];
        }
    };
    private String number = ZERO_VAL_STR;
    private boolean calcResume = false;
    private BigDecimal firstVal = BigDecimal.ZERO;
    private BigDecimal secondVal = BigDecimal.ZERO;
    private BigDecimal result = BigDecimal.ZERO;
    private String ZERO_VAL_STR = "0";

    public  CalcDataAndMethods(){
    }

    protected CalcDataAndMethods(Parcel in) {
        calcAction = in.readString();
        calcTextData = in.readString();
        resultCalcTextData = in.readString();
        init = in.readByte() != 0;
        isInteger = in.readByte() != 0;
        ZERO_VAL_STR = in.readString();
        number = in.readString();
        calcResume = in.readByte() != 0;
        firstVal = new BigDecimal(in.readString());
        secondVal = new BigDecimal(in.readString());
        result = new BigDecimal(in.readString());

    }

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

    public String addNumber(String num, String calcText) {
        if (number.equals(ZERO_VAL_STR)) {
            if (init) {
                number = num;
                calcText = num;
                init = false;
                calcResume = false;
            } else {
                number = num;
                calcText = calcText + num;
            }
        } else {
            number = number + num;
            calcText = calcText + num;
        }
        return calcText;
    }

    @SuppressLint("SetTextI18n")
    public String[] pushButtonMath(String calcAct, String calcText, String resultCalcText) {

        if (calcAction.isEmpty() && !firstVal.equals(BigDecimal.ZERO) && calcText.equals("")) {
            init = false;
            calcText = String.format(Locale.getDefault(), "%s", formatOutput(firstVal));
        } else if (calcText.equals("")) {
            calcText = "";
        } else if (calcText.equals(ZERO_VAL_STR) && calcAction.equals(CalcActions.DIVIDE.getValue())) {
            resultCalcText = "division by zero";
            calcAction = "";
            number = ZERO_VAL_STR;
            isInteger = true;
            firstVal = BigDecimal.ZERO;
            secondVal = BigDecimal.ZERO;
            result = BigDecimal.ZERO;
            init = true;
            calcText = "";

        } else if (!calcAction.isEmpty() && result.equals(BigDecimal.ZERO)) {
            result = calculate(calcAction);
            resultCalcText = String.format(Locale.getDefault(), "%s", formatOutput(result));
            firstVal = result;
            result = BigDecimal.ZERO;

        } else if (!calcAction.isEmpty()) {
            firstVal = result;
            result = calculate(calcAction);
            resultCalcText = String.format(Locale.getDefault(), "%s", formatOutput(result));
            result = BigDecimal.ZERO;

        } else if (!firstVal.equals(BigDecimal.ZERO) && calcResume) {
            calcText = String.format(Locale.getDefault(), "%s", formatOutput(firstVal));

        } else {
            firstVal = BigDecimal.valueOf(Double.parseDouble(number));
            number = ZERO_VAL_STR;
            isInteger = true;
        }
        calcResume = true;

        calcAction = calcAct;
        String calcActionText = calcText;
        if (!TextUtils.isEmpty(calcActionText)) {
            String lastChar = calcActionText.substring(calcActionText.length() - 1);
            if (CalcActions.contains(lastChar)){
                calcText = calcText.substring(0, calcText.length() - 1);
            }
        }
        switch (calcAct) {
            case ("%"):
                calcText = calcText + "%";
                break;
            case ("/"):
                calcText = calcText + "/";
                break;
            case ("x"):
                calcText = calcText + "x";
                break;
            case ("-"):
                calcText = calcText + "-";
                break;
            case ("+"):
                calcText = calcText + "+";
                break;
            default:
                throw new UnsupportedOperationException("unsupported calcAction value");
        }
        return new String[]{calcText, resultCalcText};
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

    public String pushButtonDel(String calcText) {
        if (!calcText.equals("") && !number.equals("")) {
            calcText = calcText.substring(0, calcText.length() - 1);
            number = number.substring(0, number.length() - 1);
            isInteger = true;
        }
        return calcText;
    }

    public String pushButtonPoint(String calcText) {
        if (isInteger) {
            isInteger = false;
            init = false;
            if (number.equals("") || calcText.equals("")) {
                calcText += "0,";
                number += "0.";
            } else {
                calcText += ",";
                number += ".";
            }
        }
        return calcText;
    }

    public String[] pushButtonEquals(String calcText, String resultCalcText) {
        if (!firstVal.equals(BigDecimal.ZERO)) {
            result = calculate(calcAction);
            resultCalcText = String.format(Locale.getDefault(), "%s", formatOutput(result));
            firstVal = result;
            calcText = "";
        }
        number = ZERO_VAL_STR;
        isInteger = true;
        init = true;
        calcAction = "";
        secondVal = BigDecimal.ZERO;
        result = BigDecimal.ZERO;
        calcResume = true;
        return new String[] {calcText, resultCalcText};
    }

    public void pushButtonAC() {
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


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(calcAction);
        dest.writeString(calcTextData);
        dest.writeString(resultCalcTextData);
        dest.writeByte((byte) (init ? 1 : 0));
        dest.writeByte((byte) (isInteger ? 1 : 0));
        dest.writeString(ZERO_VAL_STR);
        dest.writeString(number);
        dest.writeByte((byte) (calcResume ? 1 : 0));
        dest.writeString(firstVal.toString());
        dest.writeString(secondVal.toString());
        dest.writeString(result.toString());
    }
}
