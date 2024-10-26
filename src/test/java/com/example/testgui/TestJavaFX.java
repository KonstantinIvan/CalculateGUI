package com.example.testgui;

import com.example.calculategui.AppMain;
import com.example.calculategui.ControllerMain;
import io.cucumber.java.en.Given;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import javafx.util.Duration;
import org.junit.Assert;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.testfx.api.FxAssert;
import org.testfx.framework.junit5.ApplicationTest;

import java.io.IOException;

import static java.util.concurrent.TimeUnit.SECONDS;
import static org.awaitility.Awaitility.await;
import static org.testfx.api.FxAssert.verifyThat;
import static org.testfx.matcher.control.LabeledMatchers.hasText;


public class TestJavaFX extends ApplicationTest {

    private ControllerMain controller;

    @BeforeEach
    public void setup() {
        if (controller != null) {
            controller.text.setText(""); // Сброс текстового поля
        }
    }

    @BeforeEach
    public void openWindow() throws Exception {
        ApplicationTest.launch(AppMain.class);
        controller = AppMain.getController();
    }

    @Test
    public void testOneButtonClick1() {
        clickOn("#buttonNum1");
        clickOn("#buttonNum2");
        clickOn("#buttonPlus");
        clickOn("#buttonNum4");
        clickOn("#buttonEquals");

        // Ожидание обновления текста
        await().atMost(java.time.Duration.ofMillis(700)).until(() -> {
            String actualText = lookup("#text").queryText().getText();
            return "16,0".equals(actualText); // Проверяем сразу ожидаемое значение
        });

        String actualText = lookup("#text").queryText().getText();
        Assert.assertEquals("16,0", actualText);
    }

    @Test
    public void testOneButtonClick2() {
        Platform.runLater(() -> {
            Button buttonNum1 = lookup("#buttonNum1").queryButton();
            Button buttonNum2 = lookup("#buttonNum2").queryButton();
            Button buttonPlus = lookup("#buttonPlus").queryButton();
            Button buttonNum4 = lookup("#buttonNum4").queryButton();
            Button buttonEquals = lookup("#buttonEquals").queryButton();

            buttonNum1.fire();
            buttonNum2.fire();
            buttonPlus.fire();
            buttonNum4.fire();
            buttonEquals.fire();
        });

        // Ожидание, пока текст обновится
        await().atMost(java.time.Duration.ofMillis(500)).until(() -> {
            String actualText = controller.text.getText();
            return !actualText.isEmpty(); // Ждем, пока текст не станет непустым
        });

        String actualText = lookup("#text").queryText().getText();
        Assert.assertEquals("16,0", actualText);
    }
//
//    @Test
//    public void testOneButtonClick1() {
//        Platform.runLater(() -> {
//            Button buttonNum1 = lookup("#buttonNum1").queryButton();
//            Button buttonNum2 = lookup("#buttonNum2").queryButton();
//            Button buttonPlus = lookup("#buttonPlus").queryButton();
//            Button buttonNum5 = lookup("#buttonNum5").queryButton();
//            Button buttonEquals = lookup("#buttonEquals").queryButton();
//
//            buttonNum1.fire();
//            buttonNum2.fire();
//            buttonPlus.fire();
//            buttonNum5.fire();
//            buttonEquals.fire();
//        });
//
//        // Ожидание, пока текст обновится
//        await().atMost(java.time.Duration.ofSeconds(1)).until(() -> {
//            String actualText = controller.text.getText();
//            return !actualText.isEmpty(); // Ждем, пока текст не станет непустым
//        });
//
////        System.out.println(controller.text.getText());
////        System.out.println(controller.output.getText());
//
//        String actualText = lookup("#text").queryText().getText();
//        Assert.assertEquals("17,0", actualText);
//    }
}
