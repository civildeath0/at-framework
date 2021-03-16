package ru.sberbank.smartoffice.at.errors;

import com.codeborne.selenide.SelenideElement;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;

import javax.annotation.Nonnull;

import static com.codeborne.selenide.Selenide.screenshot;
import static java.lang.String.format;
import static org.openqa.selenium.OutputType.BYTES;
import static ru.sberbank.smartoffice.at.utilities.Stationery.attachImage;

/**
 * <h1>Исключение, которое вызывается во время
 * ошибки в тестовом продукте</h1>
 */
@Slf4j @SuppressWarnings("unused")
public class TestError extends AssertionError {

    public TestError(String message, @Nonnull Throwable throwable, boolean takeScreenshot) {
        super(message, throwable);
        log.error(message, throwable);
        if (takeScreenshot) {
            takePageScreenshot();
        }
    }

    public TestError(String message, @NonNull Throwable throwable) {
        this(message, throwable, true);
    }

    public TestError(@NonNull String message, boolean takeScreenshot) {
        super(message);
        log.error(getMessage());
        if (takeScreenshot) {
            takePageScreenshot();
        }
    }

    public TestError(@NonNull TestErrorMessage message, @NonNull Object... parameters) {
        this(format(message.getMessage(), parameters), true);
    }

    public TestError(@NonNull TestErrorMessage message, @NonNull Throwable throwable) {
        this(message.getMessage(), throwable);
    }

    public TestError(@NonNull TestErrorMessage message, @NonNull Throwable throwable, @NonNull Object... parameters) {
        this(format(message.getMessage(), parameters), throwable);
    }

    /**
     * Делает скриншот страницы и прикрепляет его к Allure
     * отчету, если был успешно сделан.
     */
    private void takePageScreenshot() {
        attachImage("Screenshot", screenshot(BYTES));
    }

    /**
     * Делает скриншот страницы и прикрепляет его к Allure
     * отчету, предварительно пролистывая страницу то видимости {@link SelenideElement веб-элемента}
     */
    private void takePageScreenshot(@Nonnull SelenideElement element) {
        if (element.exists()) {
            element.scrollIntoView("{behavior: \"smooth\", block: \"center\", inline: \"center\"}").hover();
        }
        takePageScreenshot();
    }
}
