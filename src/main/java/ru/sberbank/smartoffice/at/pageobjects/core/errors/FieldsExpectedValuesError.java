package ru.sberbank.smartoffice.at.pageobjects.core.errors;

import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.OutputType;

import java.io.ByteArrayInputStream;
import java.util.Set;

import static com.codeborne.selenide.Selenide.screenshot;
import static io.qameta.allure.Allure.addAttachment;

@Slf4j
public class FieldsExpectedValuesError extends Error {
    public FieldsExpectedValuesError(String message, Set<String> actualValues, Set<String> expectedValues) {
        super(message + "\nАктуальный список: " + actualValues + "\nОжидаемый список:  " + expectedValues);
        addErrorMessageToLogs();
        takePageScreenshot();
    }

    private void addErrorMessageToLogs() {
        log.error(getMessage(), getCause());
    }

    private void takePageScreenshot() {
        byte[] bytes = screenshot(OutputType.BYTES);
        if (bytes != null) {
            addAttachment("Screenshot", "image/png",
                    new ByteArrayInputStream(bytes), "png");
            log.debug("Сделан снимок страницы \"%s\"");
        } else {
            log.warn("Драйвер не способен сделать снимок страницы");
        }
    }
}
