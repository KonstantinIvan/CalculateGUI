package com.example.calculategui;

import javafx.animation.PauseTransition;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.util.Objects;

public class CalculatorPresenterlmpl implements CalculatorPresenter{

    public CalculatorPresenterlmpl() {}

    @FXML
    private Label typeOfOperation = new Label();

    static private Stage currentStage; // Поле для хранения текущего окна
    static private PauseTransition pause; // Таймер для закрытия окна

    @Override
    public void onPlusClicked() {
        openOperationWindow("операция сложения.");
    }

    @Override
    public void onMinusClicked() {
        openOperationWindow("операция разности.");
    }

    @Override
    public void onMultiplyClicked() {
        openOperationWindow("операция умножения.");
    }

    @Override
    public void onDivideClicked() {
        openOperationWindow("операция деления.");
    }

    static private void openOperationWindow(String typeOfOperation) {
        try {
            // Закрываем текущее окно, если оно открыто
            if (currentStage != null) {
                if (currentStage.isShowing()) {
                    // Обнуляем предыдущий таймер
                    if (pause != null) {
                        pause.stop();
                    }
                    Platform.runLater(() -> {
                        currentStage.close();
                        currentStage = null; // Обнуляем currentStage после закрытия
                        createNewStage(typeOfOperation); // Создаем новое окно после закрытия
                    });
                    return; // Выходим, чтобы не продолжать выполнение
                }
            } else {
                // Если текущее окно закрыто, создаем новое
                createNewStage(typeOfOperation);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void createNewStage(String typeOfOperation) {
        try {
            FXMLLoader loader = new FXMLLoader(Objects.requireNonNull(CalculatorPresenterlmpl.class.getResource("DisplayOfOperation.fxml")));
            Parent newRoot = loader.load();

            // Получаем контроллер из загруженного FXML
            CalculatorPresenterlmpl controller = loader.getController();
            controller.setTypeOfOperationText("Была введена " + typeOfOperation);

            currentStage = new Stage(); // Создаем новое окно
            currentStage.setTitle("Арифметическая операция");
            currentStage.setResizable(false);
            currentStage.setScene(new Scene(newRoot, 200, 100));

            // Устанавливаем позицию окна
            currentStage.setX(1000);
            currentStage.setY(450);

            currentStage.setOnCloseRequest(event -> {
                currentStage = null; // Сбрасываем currentStage при закрытии
                if (pause != null) {
                    pause.stop(); // Останавливаем таймер при закрытии окна
                }
            });

            // Закрывается окно через 5 секунд
            pause = new PauseTransition(Duration.seconds(5));
            pause.setOnFinished(event -> {
                if (currentStage != null && currentStage.isShowing()) {
                    Platform.runLater(() -> {
                        currentStage.close();
                        currentStage = null; // Обнуляем currentStage после закрытия
                    });
                }
            });
            pause.play(); // Запускаем таймер

            currentStage.show(); // Показываем новое окно
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Добавьте этот метод в ваш класс
    public void setTypeOfOperationText(String text) {
        this.typeOfOperation.setText(text);
    }


}
