package com.example.calculategui;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.util.Objects;

public class CalculatorViewlmpl implements CalculatorView{

    public static String errorTextTxt;

    @FXML
    private Label errorText;

    @FXML
    private Label resultText;

    @FXML
    private Label firstArgumentText;

    @FXML
    private Label secondArgumentText;

    static private Stage currentStageError; // Поле для хранения текущего окна
    static private Stage currentStageResult; // Поле для хранения текущего окна

    private String firstArgumentAsString;
    private String secondArgumentAsString;

    public CalculatorViewlmpl() {}

    public CalculatorViewlmpl(Double firstArgumentAsString, Double secondArgumentAsString) {
        this.firstArgumentAsString = String.valueOf(firstArgumentAsString).replace(".", ",");
        this.secondArgumentAsString = String.valueOf(secondArgumentAsString).replace(".", ",");
    }

    @Override
    public void printResult(double result) {
        openResultWindow(String.valueOf(result).replace(".", ","));
    }

    @Override
    public void displayError(String message) {
        openErrorWindow(message);
    }

    @Override
    public String getFirstArgumentAsString() {
        return this.firstArgumentAsString;
    }

    @Override
    public String getSecondArgumentAsString() {
        return this.secondArgumentAsString;
    }

    private void openErrorWindow(String errorText) {
        try {
            if (currentStageResult != null) {
                if (currentStageResult.isShowing()) {
                    Platform.runLater(() -> {
                        currentStageResult.close();
                        currentStageResult = null; // Обнуляем currentStage после закрытия
                    });
                }
            }
            if (currentStageError != null) {
                if (currentStageError.isShowing()) {
                    Platform.runLater(() -> {
                        currentStageError.close();
                        currentStageError = null; // Обнуляем currentStage после закрытия
                        createNewStageError(errorText); // Создаем новое окно после закрытия
                    });
                    return; // Выходим, чтобы не продолжать выполнение
                }
            } else {
                createNewStageError(errorText);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void openResultWindow(String resultText) {
        try {
            if (currentStageError != null) {
                if (currentStageError.isShowing()) {
                    Platform.runLater(() -> {
                        currentStageError.close();
                        currentStageError = null; // Обнуляем currentStage после закрытия
                    });
                }
            }
            if (currentStageResult != null) {
                if (currentStageResult.isShowing()) {
                    Platform.runLater(() -> {
                        currentStageResult.close();
                        currentStageResult = null; // Обнуляем currentStage после закрытия
                        createNewStageResult(resultText); // Создаем новое окно после закрытия
                    });
                    return; // Выходим, чтобы не продолжать выполнение
                }
            } else {
                createNewStageResult(resultText);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void createNewStageError(String errorText) {
        try {
            FXMLLoader loader = new FXMLLoader(Objects.requireNonNull(CalculatorViewlmpl.class.getResource("DisplayError.fxml")));
            Parent newRoot = loader.load();

            CalculatorViewlmpl controller = loader.getController();
            controller.setErrorText("Ошибка: " + errorText);

            currentStageError = new Stage(); // Создаем новое окно
            currentStageError.setTitle("Сообщение об ошибке");
            currentStageError.setResizable(false);
            currentStageError.setScene(new Scene(newRoot, 300, 200)); // Устанавливаем сцену

            currentStageError.setX(200);
            currentStageError.setY(200);

            currentStageError.setOnCloseRequest(event -> {
                currentStageError = null; // Сбрасываем currentStage при закрытии
            });
            currentStageError.show(); // Показываем новое окно

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void createNewStageResult(String resultText) {
        try {
            FXMLLoader loader = new FXMLLoader(Objects.requireNonNull(CalculatorViewlmpl.class.getResource("DisplayResult.fxml")));
            Parent newRoot = loader.load();

            CalculatorViewlmpl controller = loader.getController();
            controller.setResultText("Результат вычисления: " + resultText);
            controller.setFirstArgumentText("Первый аргумент: " + this.firstArgumentAsString);
            controller.setSecondArgumentText("Второй аргумент: " + this.secondArgumentAsString);

            currentStageResult = new Stage(); // Создаем новое окно
            currentStageResult.setTitle("Результат вычисления");
            currentStageResult.setResizable(false);
            currentStageResult.setScene(new Scene(newRoot, 250, 150)); // Устанавливаем сцену

            currentStageResult.setX(1000);
            currentStageResult.setY(200);

            currentStageResult.setOnCloseRequest(event -> {
                currentStageResult = null; // Сбрасываем currentStage при закрытии
            });
            currentStageResult.show(); // Показываем новое окно
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void handleOkErrorButton() {
        Stage stage = (Stage) errorText.getScene().getWindow();
        stage.close();
        Platform.runLater(() -> {
            currentStageError = null; // Обнуляем currentStage после закрытия
        });
    }

    @FXML
    private void handleOkResultButton() {
        Stage stage = (Stage) resultText.getScene().getWindow();
        stage.close();
        Platform.runLater(() -> {
            currentStageResult = null; // Обнуляем currentStage после закрытия
        });
    }

    public void setErrorText(String text) {
        this.errorText.setText(text);
        errorTextTxt = text;
    }

    public void setResultText(String text) {
        this.resultText.setText(text);
    }

    public void setFirstArgumentText(String text) {
        this.firstArgumentText.setText(text);
    }

    public void setSecondArgumentText(String text) {
        this.secondArgumentText.setText(text);
    }

    public Label getErrorText() {
        return errorText;
    }

    public Label getResultText() {
        return resultText;
    }

}
