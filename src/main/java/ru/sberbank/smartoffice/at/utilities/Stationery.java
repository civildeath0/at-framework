package ru.sberbank.smartoffice.at.utilities;

import io.qameta.allure.Allure;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.OutputType;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

import static com.codeborne.selenide.Selenide.screenshot;
import static io.qameta.allure.Allure.addAttachment;

@Slf4j
public abstract class Stationery {

    public static void attachText(@NonNull String name, @NonNull String content) {
        try (InputStream stream = new ByteArrayInputStream(content.getBytes())) {
            Allure.addAttachment(name, "text/html", stream, "txt");
        } catch (IOException exception) {
            log.warn("Не удалось прикрепить \"{}\" к Allure отчету", name, exception);
        }
        log.debug("Текст \"{}\" успешно прикреплен к Allure отчету", name);
    }

    public static void attachImage(@NonNull String name, @NonNull byte[] bytes) {
        try (InputStream stream = new ByteArrayInputStream(screenshot(OutputType.BYTES))) {
            addAttachment(name, "image/png", stream, "png");
        } catch (IOException exception) {
            log.warn("Не удалось прикрепить \"{}\" к Allure отчету", name, exception);
        }
        log.debug("Картинка \"{}\" успешно прикреплена к Allure отчету", name);
    }
}
