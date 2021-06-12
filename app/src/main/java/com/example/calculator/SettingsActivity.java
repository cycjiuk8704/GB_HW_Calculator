package com.example.calculator;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.CompoundButton;

import com.google.android.material.switchmaterial.SwitchMaterial;

public class SettingsActivity extends AppCompatActivity implements CompoundButton.OnCheckedChangeListener, Constants{

    String theme;

    SharedPreferences currentTheme;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        currentTheme = getSharedPreferences(THEME, Context.MODE_PRIVATE);
        theme = currentTheme.getString(THEME, Themes.LIGHT.getValue());

        SwitchMaterial switchTheme = findViewById(R.id.switch1);

        switchTheme.setChecked(theme.equals("Dark"));

        switchTheme.setOnCheckedChangeListener(this);

        Button btnReturn = findViewById(R.id.button_settings_return);
        btnReturn.setOnClickListener(v -> finish());

    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        theme = (isChecked ? Themes.DARK.getValue() : Themes.LIGHT.getValue());
        currentTheme = getSharedPreferences(THEME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = currentTheme.edit();
        editor.putString(THEME, theme);
        editor.apply();
        onRestart();
    }
}