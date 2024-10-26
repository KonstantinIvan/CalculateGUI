package com.example.testgui;

import com.example.calculategui.CalculatorViewlmpl;
import com.example.calculategui.Calculatorlmpl;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;

public class TestCalculator {

    Calculatorlmpl calculatorlmpl;
    double delta = 1e-8;

    @Before
    public void init(){
        calculatorlmpl = new Calculatorlmpl();
    }


    @Test
    @DisplayName("Тестирование суммы с положительными аргументами")
    public void testSum1(){
        Assert.assertEquals("Tест суммы провалился.", calculatorlmpl.sum(1.1, 2.2301), 3.3301, delta);
    }

    @Test
    @DisplayName("Тестирование суммы с отрицательным аргументом")
    public void testSum2(){
        Assert.assertEquals("Tест суммы с отрицательным аргументом провалился.", calculatorlmpl.sum(1.1, -2.2301), -1.1301, delta);
    }

    @Test
    @DisplayName("Тестирование разности с положительными аргументами")
    public void testSubtract1(){
        Assert.assertEquals("Tест разности провалился провалился.", calculatorlmpl.subtract(3.498, 1.4269), 2.0711, delta);
    }

    @Test
    @DisplayName("Тестирование разности с отрицательным аргументом")
    public void testSubtract2(){
        Assert.assertEquals("Tест разности с отрицательным аргументом провалился провалился.", calculatorlmpl.subtract(3.498, -1.4269), 4.9249, delta);
    }

    @Test
    @DisplayName("Тестирование произведения с положительными аргументами")
    public void testMultiply1(){
        Assert.assertEquals("Tест произведения провалился.", calculatorlmpl.multiply(1.23, 4.56), 5.6088, delta);
    }

    @Test
    @DisplayName("Тестирование произведения с отрицательным аргументом")
    public void testMultiply2(){
        Assert.assertEquals("Tест суммы провалился с отрицательным аргументом провалился.", calculatorlmpl.multiply(1.23, -4.56), -5.6088, delta);
    }

    @Test
    @DisplayName("Тестирование произведения с отрицательными аргументами")
    public void testMultiply3(){
        Assert.assertEquals("Tест суммы провалился с отрицательными аргументами провалился.", calculatorlmpl.multiply(-1.23, -4.56), 5.6088, delta);
    }

    @Test
    @DisplayName("Тестирование деления с ненулевыми положительными аргументами")
    public void testDivide1(){
        Assert.assertEquals("Tест деления с ненулевыми положительными аргументами провалился.", calculatorlmpl.divide(1.23, 4.56), 0.26973684210526316, delta);
    }

    @Test
    @DisplayName("Тестирование деления с ненулевыми положительным и отрицательным аргументами")
    public void testDivide2(){
        Assert.assertEquals("Tест деления с ненулевыми положительным и отрицательным аргументами провалился.", calculatorlmpl.divide(-4.56,  1.23), -3.7073170731707314, delta);
    }

    @Test
    @DisplayName("Тестирование деления на ноль")
    public void testDivideByZero(){
        assertThrows("Тест деления на ноль провалился", ArithmeticException.class, () -> {calculatorlmpl.divide(11.111, 0.0);});
    }

    @Test
    @DisplayName("Тестирование деления на очень маленькое число")
    public void testDivideBySmallNumber(){
        assertThrows("Тест деления на очень маленькое число провалился", ArithmeticException.class, () -> {calculatorlmpl.divide(11.111, 1e-9);});
    }

    @Test
    @DisplayName("Тестирование конструктора и геттеров класса CalculatorViewlmpl")
    public void testCalculatorViewGetArguments(){
        CalculatorViewlmpl calculatorViewlmpl = new CalculatorViewlmpl(1.0, 2.0);
        assertEquals("Тест геттера первого аргуменента класса CalculatorViewlmpl провалился", calculatorViewlmpl.getFirstArgumentAsString(), "1,0");
        assertEquals("Тест геттера второго аргуменента класса CalculatorViewlmpl провалился", calculatorViewlmpl.getSecondArgumentAsString(), "2,0");
    }
}
