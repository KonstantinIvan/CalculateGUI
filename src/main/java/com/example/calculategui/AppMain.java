package com.example.calculategui;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.Objects;

public class AppMain extends Application {

    static ControllerMain controller;

    @Override
    public void start(Stage primaryStage) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/calculategui/MainWindowOfCalculator.fxml"));

        // Загрузка root и инициализация контроллера
        Parent root = loader.load();
        controller = loader.getController(); // Инициализация контроллера

        primaryStage.setTitle("Calculator");
        primaryStage.setResizable(false);
        primaryStage.setScene(new Scene(root, 325, 400));
        primaryStage.show();
    }

    public static ControllerMain getController() {
        return controller;
    }

    public static void main(String[] args) {
        Application.launch(args);
    }
}
