package com.example.testgui;

import com.example.calculategui.AppMain;
import com.example.calculategui.CalculatorView;
import com.example.calculategui.CalculatorViewlmpl;
import com.example.calculategui.ControllerMain;
import javafx.application.Platform;
import javafx.scene.control.Button;
import org.junit.Assert;
import org.junit.Before;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.testfx.framework.junit5.ApplicationTest;

import static org.awaitility.Awaitility.await;
import static org.mockito.Mockito.when;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import org.mockito.Mock;


public class TestJavaFXWithCucumber extends ApplicationTest {

    private ControllerMain controller;

    @BeforeEach
    public void setUp() {
        // Установка мок-объекта
    }

    @Given("Открываю главное окно калькулятора")
    public void openWindowOfCalculator() throws Exception {
        ApplicationTest.launch(AppMain.class);
        controller = AppMain.getController();
    }


    @When("Я нажимаю на калькуляторе цифры {string}")
    public void enterDigits(String buttonIds) {
        String[] buttonIdArray = buttonIds.split(",\\s*"); // Разделяем по запятой и пробелу
        Platform.runLater(() -> {
            for (String buttonId : buttonIdArray) {
                Button buttonNum = lookup(buttonId).queryButton();
                buttonNum.fire();
            }
        });

        // Ожидание, пока текст обновится
        await().atMost(java.time.Duration.ofMillis(3000)).until(() -> {
            String actualText = controller.text.getText();
            return !actualText.isEmpty(); // Ждем, пока текст не станет непустым
        });
    }

    @When("Я ввожу операцию {string}")
    public void enterOperations(String buttonId) {
        Platform.runLater(() -> {
            Button buttonOp = lookup(buttonId).queryButton();
            buttonOp.fire();
        });

//        await().atMost(java.time.Duration.ofMillis(3000)).until(() -> {
//            String actualText = controller.text.getText();
//            return !actualText.isEmpty(); // Ждем, пока текст не станет непустым
//        });
    }

    @When("Я нажимаю на равно")
    public void pressOnEquals() {
        String buttonId = "#buttonEquals";
        Platform.runLater(() -> {
            Button buttonOp = lookup(buttonId).queryButton();
            buttonOp.fire();
        });
        // Ожидание, пока текст обновится
//        await().atMost(java.time.Duration.ofMillis(3000)).until(() -> {
//            String actualText = controller.text.getText();
//            return !actualText.isEmpty(); // Ждем, пока текст не станет непустым
//        });
    }

    @Then("Я вижу результат {string}")
    public void seeResult(String expectedResult) {
        await().atMost(java.time.Duration.ofMillis(3000)).until(() -> {
            String actualText = controller.text.getText();
            return !actualText.isEmpty(); // Ждем, пока текст не станет непустым
        });
        String actualText = controller.output.getText();
        Assert.assertEquals(expectedResult, actualText);
    }


    @Then("Я вижу сообщение об ошибке {string}")
    public void seeErrorMessage(String expectedMessage) {
        await().atMost(java.time.Duration.ofMillis(3000)).until(() -> {
            return !CalculatorViewlmpl.errorTextTxt.isEmpty(); // Ждем, пока текст не станет непустым
        });
        Assert.assertEquals(CalculatorViewlmpl.errorTextTxt, expectedMessage);
    }
}
