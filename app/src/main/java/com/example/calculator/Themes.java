package com.example.calculator;

public enum Themes {
    LIGHT("Light"),
    DARK("Dark");

    private final String value;

    Themes (String value) {this.value = value;}

    public String getValue() {
        return value;
    }
}
