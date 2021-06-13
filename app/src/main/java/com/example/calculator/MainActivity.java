package com.example.calculator;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.widget.Button;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

public class MainActivity extends AppCompatActivity implements Parcelable, Constants{

    private static final String CALCULATOR = "Calculator";
    private CalcDataAndMethods calcDataAndMethods = new CalcDataAndMethods();
    public static final Creator<MainActivity> CREATOR = new Creator<MainActivity>() {
        @Override
        public MainActivity createFromParcel(Parcel in) {
            return new MainActivity(in);
        }

        @Override
        public MainActivity[] newArray(int size) {
            return new MainActivity[size];
        }
    };
    private TextView calcText;
    private TextView resultCalcText;
    private String[] calcState = {"", "0"};
    private String theme;
    private SharedPreferences currentTheme;

    public MainActivity() {
    }

    protected MainActivity(Parcel in) {
        calcState = in.createStringArray();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        currentTheme = getSharedPreferences(CURRENT_THEME, Context.MODE_PRIVATE);

        theme = currentTheme.getString(CURRENT_THEME, Themes.LIGHT.getValue());

        if (theme.equals(Themes.DARK.getValue())) {
            setTheme(R.style.themeNightCalculator);
        } else setTheme(R.style.themeCalculator);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setBackgroundImage(getResources().getConfiguration().orientation);

        resultCalcText = findViewById(R.id.calcResultTextView);
        calcText = findViewById(R.id.calcTextView);

        calcText.setText(calcState[0]);
        resultCalcText.setText(calcState[1]);

        createButtons();


    }

    private void createButtons() {
        calcState[0] = calcText.getText().toString();
        calcState[1] = resultCalcText.getText().toString();
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
        Button buttonSettings = findViewById(R.id.button_settings);


        buttonAC.setOnClickListener(v -> {
            calcDataAndMethods.pushButtonAC();
            calcText.setText("");
            resultCalcText.setText("");
        });

        buttonDel.setOnClickListener(v -> {calcState[0] = calcDataAndMethods.pushButtonDel(calcState[0]);
            calcText.setText(calcState[0]);});

        buttonPercent.setOnClickListener(v -> {
            calcState = calcDataAndMethods.pushButtonMath(CalcOperator.PERCENT.getValue(), calcState[0], calcState[1]);
            calcText.setText(calcState[0]);
            resultCalcText.setText(calcState[1]);
        });

        buttonDivide.setOnClickListener(v -> {
            calcState = calcDataAndMethods.pushButtonMath(CalcOperator.DIVIDE.getValue(), calcState[0], calcState[1]);
            calcText.setText(calcState[0]);
            resultCalcText.setText(calcState[1]);
        });

        buttonOne.setOnClickListener(v -> {
            calcState[0] = calcDataAndMethods.addNumber(Numbers.ONE.getValue(), calcState[0]);
            calcText.setText(calcState[0]);
        });

        buttonTwo.setOnClickListener(v -> {
            calcState[0] = calcDataAndMethods.addNumber(Numbers.TWO.getValue(), calcState[0]);
            calcText.setText(calcState[0]);
        });

        buttonThree.setOnClickListener(v -> {
            calcState[0] = calcDataAndMethods.addNumber(Numbers.THREE.getValue(), calcState[0]);
            calcText.setText(calcState[0]);
        });

        buttonMultiply.setOnClickListener(v -> {
            calcState = calcDataAndMethods.pushButtonMath(CalcOperator.MULTIPLY.getValue(), calcState[0], calcState[1]);
            calcText.setText(calcState[0]);
            resultCalcText.setText(calcState[1]);
        });

        buttonFour.setOnClickListener(v -> {
            calcState[0] = calcDataAndMethods.addNumber(Numbers.FOUR.getValue(), calcState[0]);
            calcText.setText(calcState[0]);
        });

        buttonFive.setOnClickListener(v -> {
            calcState[0] = calcDataAndMethods.addNumber(Numbers.FIVE.getValue(), calcState[0]);
            calcText.setText(calcState[0]);
        });

        buttonSix.setOnClickListener(v -> {
            calcState[0] = calcDataAndMethods.addNumber(Numbers.SIX.getValue(), calcState[0]);
            calcText.setText(calcState[0]);
        });

        buttonMinus.setOnClickListener(v -> {
            calcState = calcDataAndMethods.pushButtonMath(CalcOperator.SUBTRACT.getValue(), calcState[0], calcState[1]);
            calcText.setText(calcState[0]);
            resultCalcText.setText(calcState[1]);
        });

        buttonSeven.setOnClickListener(v -> {
            calcState[0] = calcDataAndMethods.addNumber(Numbers.SEVEN.getValue(), calcState[0]);
            calcText.setText(calcState[0]);
        });

        buttonEight.setOnClickListener(v -> {
            calcState[0] = calcDataAndMethods.addNumber(Numbers.EIGHT.getValue(), calcState[0]);
            calcText.setText(calcState[0]);
        });

        buttonNine.setOnClickListener(v -> {
            calcState[0] = calcDataAndMethods.addNumber(Numbers.NINE.getValue(), calcState[0]);
            calcText.setText(calcState[0]);
        });

        buttonPLus.setOnClickListener(v -> {
            calcState = calcDataAndMethods.pushButtonMath(CalcOperator.ADD.getValue(), calcState[0], calcState[1]);
            calcText.setText(calcState[0]);
            resultCalcText.setText(calcState[1]);
        });

        buttonZero.setOnClickListener(v -> {
            calcState[0] = calcDataAndMethods.addNumber(Numbers.ZERO.getValue(), calcState[0]);
            calcText.setText(calcState[0]);
        });

        buttonPoint.setOnClickListener(v -> {calcState[0] = calcDataAndMethods.pushButtonPoint(calcState[0]);
            calcText.setText(calcState[0]);
        });

        buttonEqual.setOnClickListener(v -> {calcState = calcDataAndMethods.pushButtonEquals(calcState[0], calcState[1]);
            calcText.setText(calcState[0]);
            resultCalcText.setText(calcState[1]);});

        buttonSettings.setOnClickListener(v -> {

//            mStartForResult.launch(new Intent(this, SettingsActivity.class));
            Intent runSettings = new Intent(MainActivity.this, SettingsActivity.class);
            startActivityForResult(runSettings, REQUEST_CODE_SETTING_ACTIVITY);
        });

    }

//    ActivityResultLauncher<Intent> mStartForResult = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(),
//            new ActivityResultCallback<ActivityResult>() {
//                @Override
//                public void onActivityResult(ActivityResult result) {
//                    if (result.getResultCode() == Activity.RESULT_OK) {
//                        recreate();
//                    }
//                }
//            });

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode != REQUEST_CODE_SETTING_ACTIVITY) {
            super.onActivityResult(requestCode, resultCode, data);
            return;
        }

        if (resultCode == RESULT_OK){
            currentTheme = getSharedPreferences(CURRENT_THEME, Context.MODE_PRIVATE);
            if (theme.equals(Themes.LIGHT.getValue())) {
                theme = Themes.DARK.getValue();
            } else {
                theme = Themes.LIGHT.getValue();
            }
            SharedPreferences.Editor editor = currentTheme.edit();
            editor.putString(CURRENT_THEME, theme);
            editor.apply();
            recreate();
        }
    }


    @Override
    protected void onSaveInstanceState(@NonNull Bundle instanceState) {
        super.onSaveInstanceState(instanceState);
        calcDataAndMethods.setCalcTextData(calcState[0]);
        calcDataAndMethods.setResultCalcTextData(calcState[1]);
        instanceState.putParcelable(CALCULATOR, calcDataAndMethods);
    }

    @Override
    protected void onRestoreInstanceState(@NonNull Bundle instanceState) {
        super.onRestoreInstanceState(instanceState);
        calcDataAndMethods = (CalcDataAndMethods) instanceState.getParcelable(CALCULATOR);
        calcState[0] = calcDataAndMethods.getCalcTextData();
        calcState[1] = calcDataAndMethods.getResultCalcTextData();
        calcText.setText(calcState[0]);
        resultCalcText.setText(calcState[1]);
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



    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeStringArray(calcState);
    }
}