package ru.sberbank.smartoffice.at.steps.commons;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import io.cucumber.java.ru.И;
import lombok.experimental.FieldDefaults;
import ru.sberbank.smartoffice.at.errors.TestError;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$x;
import static java.lang.String.format;
import static lombok.AccessLevel.PRIVATE;
import static ru.sberbank.smartoffice.at.errors.TestErrorMessage.MISSING_NOTIFICATION;

@FieldDefaults(level = PRIVATE, makeFinal = true)
public final class NotificationsSteps {

    @И("на странице присутствует уведомление с текстом {string}")
    public void notificationExist(String text) {
        if (!getNotification(text).isDisplayed()) {
            throw new TestError(MISSING_NOTIFICATION, text);
        }
    }

    @И("закрываю уведомление с текстом {string}")
    public void closeNotification(String text) {
        Selenide
                .actions()
                .pause(150)
                .click(appearedNotification(text).$x(".//a"))
                .perform();
    }

    @И("появляется уведомление с текстом {string}")
    public SelenideElement appearedNotification(String text) {
        try {
            return $x(format("//div[contains(@class, 'notification-notice') and .='%s']",
                    text)).shouldBe(visible);
        } catch (AssertionError error) {
            throw new TestError(MISSING_NOTIFICATION, error, text);
        }
    }

    private SelenideElement getNotification(String text) {
        return $x(format("//div[contains(@class, 'notification-notice')][.='%s']", text));
    }
}
