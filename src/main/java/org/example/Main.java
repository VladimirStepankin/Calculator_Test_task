package org.example;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        System.out.println("Ввод: ");
        Scanner scanner = new Scanner(System.in);
        String inputData = scanner.nextLine();
        try {
            String result = calc(inputData);
            System.out.println("Результат:" + result);
        } catch (Exception e) {
            System.out.println("Ошибка:" + e.getMessage());
        }
        scanner.close();
    }

    public static String calc(String input) throws Exception {
        String[] parts = input.split("[+\\-*/]");
        if (parts.length != 2) {
            throw new Exception("Неверный формат ввода");
        }
        int num1, num2;
        boolean isRoman = false;
        try {
            num1 = Integer.parseInt(parts[0]);
            num2 = Integer.parseInt(parts[1]);
        } catch (NumberFormatException e) {
            num1 = romanToArabic(parts[0]);
            num2 = romanToArabic(parts[1]);
            isRoman = true;
        }
        if (num1 < 1 || num1 > 10 || num2 < 1 || num2 > 10) {
            throw new Exception("Числа должны быть от 1 до 10 включительно");
        }
        char operator = input.charAt(parts[0].length());
        int result = switch (operator) {
            case '+' -> num1 + num2;
            case '-' -> num1 - num2;
            case '*' -> num1 * num2;
            case '/' -> num1 / num2;
            default -> throw new Exception("Неверный оператор");
        };
        if (isRoman) {
            if (result > 0) {
                return arabicToRoman(result);
            } else {
                throw new Exception("Результат операции может быть только положительным");
            }
        }
        return Integer.toString(result);
    }

    public static int romanToArabic(String romanNumber) {
        // Конвертация римских чисел в арабские
        int arabicValue = 0;
        for (int i = 0; i < romanNumber.length(); i++) {
            char currentChar = romanNumber.charAt(i);
            RomanNumber currentNumber = RomanNumber.valueOf(String.valueOf(currentChar));
            int value = currentNumber.getArabicValue();
            if (i < romanNumber.length() - 1) {
                char nextChar = romanNumber.charAt(i + 1);
                RomanNumber nextNumber = RomanNumber.valueOf(String.valueOf(nextChar));
                int nextValue = nextNumber.getArabicValue();
                if (value < nextValue) {
                    arabicValue -= value;
                } else {
                    arabicValue += value;
                }
            } else {
                arabicValue += value;
            }
        }
        return arabicValue;
    }

    public static String arabicToRoman(int arabicNumber) {
        // Конвертация арабских чисел в римские
        StringBuilder romanNumber = new StringBuilder();
        RomanNumber[] values = RomanNumber.values();
        for (int i = values.length - 1; i >= 0; i--) {
            while (arabicNumber >= values[i].getArabicValue()) {
                romanNumber.append(values[i]);
                arabicNumber -= values[i].getArabicValue();
            }
        }
        return romanNumber.toString();
    }
}