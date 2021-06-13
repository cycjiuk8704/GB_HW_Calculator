package com.example.calculator;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.CompoundButton;

import com.google.android.material.switchmaterial.SwitchMaterial;

public class SettingsActivity extends AppCompatActivity implements CompoundButton.OnCheckedChangeListener, Constants {

    String theme;

    private SharedPreferences currentTheme;
    private SharedPreferences sharedPrefTheme;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        sharedPrefTheme = getSharedPreferences(THEME, Context.MODE_PRIVATE);
        currentTheme = getSharedPreferences(CURRENT_THEME, Context.MODE_PRIVATE);

        theme = sharedPrefTheme.getString(THEME, currentTheme.getString(CURRENT_THEME, Themes.LIGHT.getValue()));

        if (theme.equals(Themes.DARK.getValue())) {
            setTheme(R.style.NightTheme_Calculator);
        } else setTheme(R.style.Theme_Calculator);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        SwitchMaterial switchTheme = findViewById(R.id.switch1);

        switchTheme.setChecked(sharedPrefTheme.getString(THEME, currentTheme.getString(CURRENT_THEME, Themes.LIGHT.getValue())).equals(Themes.DARK.getValue()));

        switchTheme.setOnCheckedChangeListener(this);

        Button btnReturn = findViewById(R.id.button_settings_return);
        btnReturn.setOnClickListener(v -> {
            if (!currentTheme.getString(CURRENT_THEME, Themes.LIGHT.getValue()).equals(sharedPrefTheme.getString(THEME, Themes.LIGHT.getValue()))) {
                Intent intentResult = new Intent();
                setResult(RESULT_OK, intentResult);
            }
            finish();
        });
    }



    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        theme = (isChecked ? Themes.DARK.getValue() : Themes.LIGHT.getValue());
        sharedPrefTheme = getSharedPreferences(THEME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPrefTheme.edit();
        editor.putString(THEME, theme);
        editor.apply();
        recreate();
    }
}