package com.example.calculator;

public enum CalcActions {
    PERCENT("%"),
    DIVIDE("/"),
    MULTIPLY("x"),
    SUBTRACT("-"),
    ADD("+");

    private final String value;

    CalcActions(String value) {
        this.value = value;
    }

    public static boolean contains(String value) {
        if (value != null) {
            for (CalcActions c : CalcActions.values()) {
                if (c.getValue().equalsIgnoreCase(value)) {
                    return true;
                }
            }
        }

        return false;
    }

    public String getValue() {
        return value;
    }
}
