package com.example.testgui;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        plugin = {"pretty", "html:target/cucumber"},
        features = "src/test/resources/scenarios/outline.feature",
        glue = "com.example.testgui"
)
public class RunCucumberTest {
}
