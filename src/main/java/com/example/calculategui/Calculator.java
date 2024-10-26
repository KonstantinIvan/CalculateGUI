package com.example.calculategui;

public interface Calculator {

    /**
     * Вычисляет сумму двух чисел
     */
    double sum(double num1, double num2);


    /**
     * Вычисляет разность двух чисел a - b
     */
    double subtract(double num1, double num2);

    /**
     * Вычисляет произведение двух чисел
     */
    double multiply(double num1, double num2);

    /**
     * Вычисляет отношение числа а к числу b.
     * Должен выбросить {@link ArithmeticException} если |b| < 10e-8
     */
    double divide(double num1, double num2);
}

