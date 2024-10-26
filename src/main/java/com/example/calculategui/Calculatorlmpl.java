package com.example.calculategui;

public class Calculatorlmpl implements Calculator{

    public Calculatorlmpl() {}

    @Override
    public double sum(double num1, double num2) {
        return num1+num2;
    }

    @Override
    public double subtract(double num1, double num2) {
        return num1-num2;
    }

    @Override
    public double multiply(double num1, double num2) {
        return num1*num2;
    }

    @Override
    public double divide(double num1, double num2){
        if (Math.abs(num2) < 1e-8) {
            throw new ArithmeticException("Значение второго аргумента b слишком мало: |b| < 10e-8");
        } else{
            return num1/num2;
        }
    }
}
