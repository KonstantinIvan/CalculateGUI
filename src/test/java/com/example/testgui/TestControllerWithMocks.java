//package com.example.testgui;
//
//import com.example.calculategui.CalculatorViewAlternativelmpl;
//import com.example.calculategui.ControllerMain;
//import com.example.calculategui.Calculatorlmpl;
//import com.example.calculategui.CalculatorPresenterAlternativelmpl;
//import org.junit.Assert;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.MockitoAnnotations;
//
//import static org.mockito.Mockito.*;
//import static org.junit.jupiter.api.Assertions.*;
//
//public class TestControllerWithMocks {
//
//    double delta = 1e-8;
//
//    @Mock
//    private Calculatorlmpl calculatorlmpl;
//
//    @Mock
//    private CalculatorPresenterAlternativelmpl calculatorPresenterAlternativelmpl;
//
//    @Mock
//    private CalculatorViewAlternativelmpl calculatorViewAlternativelmpl;
//
//    @InjectMocks
//    private ControllerMain controller;
//
//    @BeforeEach
//    public void setUp() {
//        MockitoAnnotations.openMocks(this);
//    }
//
//    @Test
//    void testProcessOperationStartNullOperationNull() {
//        controller.operator = "";
//        String inputText = "";
//        String inputOperator = "*";
//
//        String result = controller.processOperatorAlternate(inputText, inputOperator);
//
//        assertEquals("Ничего не произошло.", result);
//        assertEquals("", controller.operator);
//        assertFalse(controller.start);
//
//        verify(calculatorPresenterAlternativelmpl, never()).onPlusClicked();
//        verify(calculatorPresenterAlternativelmpl, never()).onMinusClicked();
//        verify(calculatorPresenterAlternativelmpl, only()).onMultiplyClicked();
//        verify(calculatorPresenterAlternativelmpl, never()).onDivideClicked();
//    }
//
//
//
//@Test
//    void testProcessOperationStartNullOperationNotNull() {
//        controller.operator = "/";
//        String inputText = "";
//        String inputOperator = "*";
//
//        Assert.assertEquals(controller.processOperatorAlternate(inputText, inputOperator), "Записался новый оператор.");
//        Assert.assertEquals(controller.operator, "*");
//        Assert.assertFalse(controller.start);
//
//        verify(calculatorPresenterAlternativelmpl, never()).onPlusClicked();
//        verify(calculatorPresenterAlternativelmpl, never()).onMinusClicked();
//        verify(calculatorPresenterAlternativelmpl, only()).onMultiplyClicked();
//        verify(calculatorPresenterAlternativelmpl, never()).onDivideClicked();
//    }
//
//    @Test
//    void testProcessOperationStartNotNullOperationNullPositive() {
//        controller.operator = "";
//        controller.num1 = 0;
//        String inputText = "2";
//        String inputOperator = "*";
//
//        Assert.assertEquals(controller.processOperatorAlternate(inputText, inputOperator), "Записался первый аргумент.");
//        Assert.assertEquals(controller.operator, "*");
//        Assert.assertEquals(controller.num1, 2.0, delta);
//        Assert.assertFalse(controller.start);
//
//        verify(calculatorPresenterAlternativelmpl, never()).onPlusClicked();
//        verify(calculatorPresenterAlternativelmpl, never()).onMinusClicked();
//        verify(calculatorPresenterAlternativelmpl, only()).onMultiplyClicked();
//        verify(calculatorPresenterAlternativelmpl, never()).onDivideClicked();
//    }
//
//    @Test
//    void testProcessOperationStartNotNullOperationNullNegative() {
//        controller.operator = "";
//        controller.num1 = 0;
//        String inputText = "-="; // Входное значение
//        String inputOperator = "*"; // Оператор
//
//        // Проверяем, что метод выбрасывает исключение
//        Exception exception = Assert.assertThrows(NumberFormatException.class, () -> {
//            controller.conversionToNumber(inputText);
//        });
//
//        // Проверяем сообщение об ошибке
//        Assert.assertEquals("Ошибка: Невозможно преобразовать строку \"" + inputText + "\" в число.", exception.getMessage());
//
//        // Вызываем метод
//        String result = controller.processOperatorAlternate(inputText, inputOperator);
//
//        // Проверяем результаты
//        Assert.assertEquals("Ошибка", result);
//        Assert.assertEquals("", controller.operator);
//        Assert.assertEquals(0.0, controller.num1, delta);
//        Assert.assertFalse(controller.start);
//
//        // Проверяем, что методы не были вызваны
//        verify(calculatorPresenterAlternativelmpl, never()).onPlusClicked();
//        verify(calculatorPresenterAlternativelmpl, never()).onMinusClicked();
//        verify(calculatorPresenterAlternativelmpl, only()).onMultiplyClicked();
//        verify(calculatorPresenterAlternativelmpl, never()).onDivideClicked();
//
//        // Проверяем, что ошибка отображается
//        verify(calculatorViewAlternativelmpl).displayError("Ошибка: Невозможно преобразовать строку \"" + inputText + "\" в число.");
//    }
//
//    @Test
//    void testProcessOperationStartNotNullOperationNotNullNegative() {
//        controller.operator = "-";
//        controller.num1 = 10;
//        String inputText = "GN";
//        String inputOperator = "+";
//
//        // Проверяем, что метод выбрасывает исключение
//        Exception exception = Assert.assertThrows(NumberFormatException.class, () -> {
//            controller.conversionToNumber(inputText);
//        });
//
//        // Проверяем сообщение об ошибке
//        Assert.assertEquals("Ошибка: Невозможно преобразовать строку \"" + inputText + "\" в число.", exception.getMessage());
//
//        Assert.assertEquals(controller.processOperatorAlternate(inputText, inputOperator), "Ошибка");
//        Assert.assertEquals(controller.operator, "");
//
//        Assert.assertEquals(controller.num1, 0.0, delta);
//        Assert.assertTrue(controller.start);
//
//        verify(calculatorPresenterAlternativelmpl, only()).onPlusClicked();
//        verify(calculatorPresenterAlternativelmpl, never()).onMinusClicked();
//        verify(calculatorPresenterAlternativelmpl, never()).onMultiplyClicked();
//        verify(calculatorPresenterAlternativelmpl, never()).onDivideClicked();
//
//        verify(calculatorViewAlternativelmpl, only()).displayError("Ошибка: Невозможно преобразовать строку \"" + inputText + "\" в число.");
//    }
//
//
//    @Test
//    void testProcessOperationStartNotNullOperationNotNullMinus() {
//        controller.operator = "+";
//        controller.num1 = 10;
//        String inputText = "2";
//        String inputOperator = "-";
//
//        when(calculatorlmpl.subtract(controller.num1, 2)).thenReturn(8.0);
//
//        Assert.assertEquals(controller.processOperatorAlternate(inputText, inputOperator), "Записался второй аргумент.");
//        Assert.assertEquals(controller.operator, "-");
//
//        Assert.assertEquals(controller.num1, 8.0, delta);
//        Assert.assertTrue(controller.start);
//
//        verify(calculatorPresenterAlternativelmpl, never()).onPlusClicked();
//        verify(calculatorPresenterAlternativelmpl, only()).onMinusClicked();
//        verify(calculatorPresenterAlternativelmpl, never()).onMultiplyClicked();
//        verify(calculatorPresenterAlternativelmpl, never()).onDivideClicked();
//
//        verify(calculatorViewAlternativelmpl).printResult(8.0);
//    }
//
//    @Test
//    void testProcessOperationStartNotNullOperationNotNullMultiply() {
//        controller.operator = "-";
//        controller.num1 = 10;
//        String inputText = "2";
//        String inputOperator = "*";
//
//        when(calculatorlmpl.multiply(controller.num1, 2)).thenReturn(20.0);
//
//        Assert.assertEquals(controller.processOperatorAlternate(inputText, inputOperator), "Записался второй аргумент.");
//        Assert.assertEquals(controller.operator, "*");
//
//        Assert.assertEquals(controller.num1, 20.0, delta);
//        Assert.assertTrue(controller.start);
//
//        verify(calculatorPresenterAlternativelmpl, never()).onPlusClicked();
//        verify(calculatorPresenterAlternativelmpl, never()).onMinusClicked();
//        verify(calculatorPresenterAlternativelmpl, only()).onMultiplyClicked();
//        verify(calculatorPresenterAlternativelmpl, never()).onDivideClicked();
//
//        verify(calculatorViewAlternativelmpl).printResult(20.0);
//    }
//
//    @Test
//    void testProcessOperationStartNotNullOperationNotNullDividePositive() {
//        controller.operator = "-";
//        controller.num1 = 10;
//        String inputText = "2";
//        String inputOperator = "/";
//
//        when(calculatorlmpl.divide(controller.num1, 2)).thenReturn(5.0);
//
//        Assert.assertEquals(controller.processOperatorAlternate(inputText, inputOperator), "Записался второй аргумент.");
//        Assert.assertEquals(controller.operator, "/");
//
//        Assert.assertEquals(controller.num1, 5.0, delta);
//        Assert.assertTrue(controller.start);
//
//        verify(calculatorPresenterAlternativelmpl, never()).onPlusClicked();
//        verify(calculatorPresenterAlternativelmpl, never()).onMinusClicked();
//        verify(calculatorPresenterAlternativelmpl, never()).onMultiplyClicked();
//        verify(calculatorPresenterAlternativelmpl, only()).onDivideClicked();
//
//        verify(calculatorViewAlternativelmpl).printResult(5.0);
//    }
//
//    @Test
//    void testProcessOperationStartNotNullOperationNotNullDivideNegative() {
//        controller.operator = "-";
//        controller.num1 = 10;
//        String inputText = "0";
//        String inputOperator = "/";
//
//        when(calculatorlmpl.divide(controller.num1, 0)).thenThrow(new ArithmeticException("Ошибка: Значение второго аргумента b слишком мало: |b| < 10e-8"));
//
//        Assert.assertEquals(controller.processOperatorAlternate(inputText, inputOperator), "Ошибка");
//        Assert.assertEquals(controller.operator, "");
//
//        Assert.assertEquals(controller.num1, 0, delta);
//        Assert.assertTrue(controller.start);
//
//        verify(calculatorPresenterAlternativelmpl, never()).onPlusClicked();
//        verify(calculatorPresenterAlternativelmpl, never()).onMinusClicked();
//        verify(calculatorPresenterAlternativelmpl, never()).onMultiplyClicked();
//        verify(calculatorPresenterAlternativelmpl, only()).onDivideClicked();
//
//        verify(calculatorViewAlternativelmpl).displayError("Ошибка: Значение второго аргумента b слишком мало: |b| < 10e-8");
//    }
//}