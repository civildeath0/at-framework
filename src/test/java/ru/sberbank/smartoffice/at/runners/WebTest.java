package ru.sberbank.smartoffice.at.runners;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import ru.sberbank.smartoffice.at.utilities.Settings;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = {
                "src/test/resources/features"
        },
        plugin = {
                "io.qameta.allure.cucumber6jvm.AllureCucumber6Jvm"
        },
        glue = {
                "ru.sberbank.smartoffice.at.hooks",
                "ru.sberbank.smartoffice.at.steps",
        },
        tags="@tmsLink=T1041"
)
public class WebTest {

    @BeforeClass
    public static void setUp() {
        Settings.setBasicSettings();
    }
}
