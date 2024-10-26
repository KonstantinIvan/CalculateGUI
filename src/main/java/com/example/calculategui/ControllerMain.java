package com.example.calculategui;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.text.Text;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Objects;

public class ControllerMain {

    public Text output = new Text();

    @FXML
    public Text text = new Text();

    public double num1 = 0;
    public double num2 = 0;
    public String operator = "";
    public boolean start = false;

    Calculatorlmpl calculatorlmpl = new Calculatorlmpl();
    CalculatorPresenterAlternativelmpl calculatorPresenterAlternativelmpl = new CalculatorPresenterAlternativelmpl();
    CalculatorViewAlternativelmpl calculatorViewAlternativelmpl = new CalculatorViewAlternativelmpl();

    public CalculatorViewlmpl calculatorViewlmpl = new CalculatorViewlmpl();

    @FXML
    public void processNum(ActionEvent event) {
//        System.out.println("Я здесь в num");
        if (Objects.equals(text.getText(), String.valueOf(num1))){
            text.setText("");
        }
        String value = ((Button) event.getSource()).getText();
        // Обработка запятой
        if (value.equals(",")) {
            if (text.getText().isEmpty()){
                text.setText( text.getText() + ",0");
                output.setText("0," + output.getText());
            }
            // Проверяем, есть ли уже запятая в тексте
            else if (!text.getText().contains(",")) {
                text.setText(value + text.getText());
                output.setText(output.getText() + value);
            }

        }
        else if(!text.getText().isEmpty()) {
            if (text.getText().charAt(0) == '0') {
                if (text.getText().contains(",")){
                    output.setText(output.getText() + value);
                    text.setText(rearrangeMinusForText(output.getText()));
                }else{
                    output.setText(output.getText().replace("0", "") + value);
                    text.setText(rearrangeMinusForText(output.getText()));
                }
            }else{
                output.setText(output.getText() + value);
                text.setText(rearrangeMinusForText(output.getText()));
            }
        }else{
            output.setText(output.getText() + value);
            text.setText(rearrangeMinusForText(output.getText()));
        }


        start = true;


    }

    @FXML
    private void processMinus(ActionEvent event) {
        if (start){
            output.setText(rearrangeMinusForOutput("-" + output.getText()));
            text.setText(rearrangeMinusForText(rearrangeComma(output.getText())));
        }
    }

    @FXML
    private void processOperator(ActionEvent event) {
//        System.out.println("Я здесь в Operator");
        notifyAboutOperation(((Button) event.getSource()).getText());
        if (start && (operator.isEmpty())) {
            try {
                num1 = conversionToNumber(output.getText());
                operator = ((Button) event.getSource()).getText();
            } catch (NumberFormatException e) {
                calculatorViewlmpl.displayError(e.getMessage());
            } finally{
                output.setText(""); // Очистка вывода
                text.setText(""); // Очистка вывода
                start = false;
            }
        } else if ((!start) && (!operator.isEmpty())) {
            output.setText(""); // Очистка вывода
            operator = ((Button) event.getSource()).getText();
        } else if (start){
            try {
                num2 = conversionToNumber(output.getText());
                num1 = operate(num1, num2, operator);
                operator = ((Button) event.getSource()).getText();
                output.setText("");
                text.setText(rearrangeMinusForText(String.valueOf(num1).replace(".", ",")));
                //output.setText(rearrangeMinusForOutput(String.valueOf(num1).replace(".", ",")));
            } catch (NumberFormatException | ArithmeticException e) {
                num1 = 0;
                operator = "";
                text.setText("");
                output.setText("");
                calculatorViewlmpl.displayError(e.getMessage());
            }
        }
//        System.out.println("Я сваливаю из Operator");
    }

    public String processOperatorAlternate(String inputText, String inputOperator) {
        if (!inputText.isEmpty()) start = true;
        notifyAboutOperationAlternative(inputOperator);
        if (start && operator.isEmpty()) {
            try {
                num1 = conversionToNumber(inputText);
                operator = inputOperator;
                return "Записался первый аргумент.";
            } catch (NumberFormatException e) {
                calculatorViewAlternativelmpl.displayError(e.getMessage());
                return "Ошибка";
            } finally {
                start = false;
            }
        } else if (!start && !operator.isEmpty()) {
            operator = inputOperator;
            return "Записался новый оператор.";
        } else if (start) {
            try {
                num2 = conversionToNumber(inputText);
                operator = inputOperator;
                num1 = operateAlternative(num1, num2, operator);
                return "Записался второй аргумент.";
            } catch (NumberFormatException | ArithmeticException e) {
                num1 = 0;
                operator = "";
                calculatorViewAlternativelmpl.displayError(e.getMessage());
                return "Ошибка";
            }
        }
        return "Ничего не произошло.";
    }


    @FXML
private void processEquals(ActionEvent event) {
//        System.out.println("Я здесь в Equals");
    try {
        if (!operator.isEmpty()) {
            if (!start) {
                num2 = num1;
            } else {
                num2 = conversionToNumber(output.getText());
            }
            num1 = operate(num1, num2, operator);
            operator = "";
            text.setText(rearrangeMinusForText(String.valueOf(num1).replace(".", ",")));
            output.setText(rearrangeMinusForOutput(String.valueOf(num1).replace(".", ",")));
        }
    } catch (NumberFormatException | ArithmeticException e){
        num1 = 0;
        operator = "";
        text.setText("");
        output.setText("");
        start = false;
        calculatorViewlmpl.displayError(e.getMessage());
    }
}


    @FXML
    private void processClear(ActionEvent event) {
        output.setText(""); // Очистка вывода
        text.setText(""); // Очистка вывода
        num1 = 0;
        num2 = 0;
        operator = "";
        start = false;
    }

    @FXML
    private void processDelete(ActionEvent event) {
        String currentOutput = output.getText();
        if (!currentOutput.isEmpty()) {
            output.setText(currentOutput.substring(0, currentOutput.length() - 1)); // Удаление последнего символа
            text.setText(rearrangeMinusForText(rearrangeComma(output.getText())));
            if (text.getText().isEmpty()){
                start = false;
            }else if (Objects.equals(text.getText(), "-")){
                text.setText("");
                output.setText("");
                start = false;
            }
        }
    }

    public Double conversionToNumber(String str){
        try {
            str = str.replace(",", ".");
            return Double.parseDouble(str);
        } catch (NumberFormatException e) {
            throw new NumberFormatException("Ошибка: Невозможно преобразовать строку \"" + str + "\" в число.");
        }
    }

    private String  rearrangeMinusForText(String str){
        int minusCount = str.length() - str.replace(String.valueOf("-"), "").length();
        if (minusCount == 1){
            return str.replace("-", "") + "-";
        }
        else{
            return str.replace("-", "");
        }
    }

    static private String  rearrangeMinusForOutput(String str){
        int minusCount = str.length() - str.replace(String.valueOf("-"), "").length();
        if (minusCount == 1){
            return "-" + str.replace("-", "");
        }
        else{
            return str.replace("-", "");
        }
    }

    static private String rearrangeComma(String str){
        if (!str.isEmpty()){
            if (str.charAt(str.length() - 1) == ','){
                return "," + str.replace(",", "");
            }
            return str;
        } else{
            return str;
        }
    }

    public Double operate(Double num1, Double num2, String operator){
        CalculatorViewlmpl calculatorViewlmpl = new CalculatorViewlmpl(num1, num2);
        double result = switch (operator) {
            case "+" -> calculatorlmpl.sum(num1, num2);
            case "-" -> calculatorlmpl.subtract(num1, num2);
            case "*" -> calculatorlmpl.multiply(num1, num2);
            case "/" -> calculatorlmpl.divide(num1, num2);
            default -> 0;
        };
        result = new BigDecimal(result).setScale(14, RoundingMode.HALF_UP).doubleValue();
        calculatorViewlmpl.printResult(result);
        return result;
    }

    public void notifyAboutOperation(String operator){
        CalculatorPresenterlmpl calculatorPresenterlmpl = new CalculatorPresenterlmpl();
        switch (operator){
            case "+":
                calculatorPresenterlmpl.onPlusClicked();
                break;
            case "-":
                calculatorPresenterlmpl.onMinusClicked();
                break;
            case "*":
                calculatorPresenterlmpl.onMultiplyClicked();
                break;
            case "/":
                calculatorPresenterlmpl.onDivideClicked();
                break;
        }
    }

    public Double operateAlternative(Double num1, Double num2, String operator){
        double result = switch (operator) {
            case "+" -> calculatorlmpl.sum(num1, num2);
            case "-" -> calculatorlmpl.subtract(num1, num2);
            case "*" -> calculatorlmpl.multiply(num1, num2);
            case "/" -> calculatorlmpl.divide(num1, num2);
            default -> 0;
        };

        result = new BigDecimal(result).setScale(14, RoundingMode.HALF_UP).doubleValue();
        calculatorViewAlternativelmpl.printResult(result);
        return result;
    }

    public void notifyAboutOperationAlternative(String operator){
        switch (operator){
            case "+":
                calculatorPresenterAlternativelmpl.onPlusClicked();
                break;
            case "-":
                calculatorPresenterAlternativelmpl.onMinusClicked();
                break;
            case "*":
                calculatorPresenterAlternativelmpl.onMultiplyClicked();
                break;
            case "/":
                calculatorPresenterAlternativelmpl.onDivideClicked();
                break;
        }
    }

}

