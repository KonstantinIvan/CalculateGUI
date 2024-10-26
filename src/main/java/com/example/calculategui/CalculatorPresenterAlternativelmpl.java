package com.example.calculategui;

public class CalculatorPresenterAlternativelmpl implements CalculatorPresenter {


    public CalculatorPresenterAlternativelmpl() {
    }

    @Override
    public void onPlusClicked() {
        System.out.println("Была выполнена операция сложения.");
    }

    @Override
    public void onMinusClicked() {
        System.out.println("Была выполнена операция разности.");
    }

    @Override
    public void onMultiplyClicked() {
        System.out.println("Была выполнена операция умножения.");
    }

    @Override
    public void onDivideClicked() {
        System.out.println("Была выполнена операция произведения.");
    }
}
