package org.example;

public enum RomanNumber {
    I(1), IV(4), V(5), IX(9), X(10), XL(40),
    L(50), XC(90), C(100);

    private final int arabicValue;

    RomanNumber(int arabicValue) {

        this.arabicValue = arabicValue;
    }

    public int getArabicValue() {

        return arabicValue;
    }

}
