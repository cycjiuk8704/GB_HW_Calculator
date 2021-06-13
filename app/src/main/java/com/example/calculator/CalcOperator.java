package com.example.calculator;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.math.BigDecimal;
import java.util.Objects;

public enum CalcOperator {
    PERCENT("%") {
        @Override
        public BigDecimal apply(BigDecimal op1, BigDecimal op2) {
            return op1.multiply(op2.divide(BigDecimal.valueOf(100.0d), 9, BigDecimal.ROUND_HALF_UP));
        }
    },
    DIVIDE("/"){
        @Override
        public BigDecimal apply(BigDecimal op1, BigDecimal op2) {
            return op1.divide(op2, 9, BigDecimal.ROUND_HALF_UP);
        }
    },
    MULTIPLY("x"){
        @Override
        public BigDecimal apply(BigDecimal op1, BigDecimal op2) {
            return op1.multiply(op2);
        }
    },
    SUBTRACT("-") {
        @Override
        public BigDecimal apply(BigDecimal op1, BigDecimal op2) {
            return op1.subtract(op2);
        }
    },
    ADD("+"){
        @Override
        public BigDecimal apply(BigDecimal op1, BigDecimal op2) {
            return op1.add(op2);
        }
    };

    private final String value;

    CalcOperator(String value) {
        this.value = value;
    }

    @Nullable
    public static CalcOperator getInstance(String value) {
        for (CalcOperator c : CalcOperator.values()) {
            if (c.getValue().equalsIgnoreCase(value)) {
                return c;
            }
        }

        return null;
    }

    @NonNull
    public static CalcOperator requireInstance(String value) {
        return Objects.requireNonNull(getInstance(value), "Operator not supported: " + value);
    }

    public static boolean contains(String value) {
        return getInstance(value) != null;
    }

    abstract BigDecimal apply(BigDecimal op1, BigDecimal op2);

    public String getValue() {
        return value;
    }
}
