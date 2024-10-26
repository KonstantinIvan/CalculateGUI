package com.example.calculategui;

public class CalculatorViewAlternativelmpl implements CalculatorView{

    public CalculatorViewAlternativelmpl() {
    }

    @Override
    public void printResult(double result) {
        System.out.println("Результат операции: " + result);
    }

    @Override
    public void displayError(String message) {
        System.out.println("Ошибка: " + message);
    }

    @Override
    public String getFirstArgumentAsString() {
        return "";
    }

    @Override
    public String getSecondArgumentAsString() {
        return "";
    }
}
