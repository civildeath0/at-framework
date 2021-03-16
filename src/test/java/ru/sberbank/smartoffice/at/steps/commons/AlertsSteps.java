package ru.sberbank.smartoffice.at.steps.commons;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import io.cucumber.java.ru.А;
import io.cucumber.java.ru.И;
import io.cucumber.java.ru.Когда;
import ru.sberbank.smartoffice.at.errors.TestError;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.$;
import static java.lang.String.format;
import static ru.sberbank.smartoffice.at.errors.TestErrorMessage.*;

public final class AlertsSteps {

    private final SelenideElement alert;

    public AlertsSteps() {
        alert = $("div[class=ant-modal-body]");
    }

    @И("на странице появляется предупреждение")
    @И("на странице появилось всплывающее окно")
    public void alertAppearedOnThePage() {
        try {
            alert.shouldBe(visible);
        } catch (AssertionError error) {
            throw new TestError(MISSING_ALERT, error);
        }
    }

    @И("в заглавии этого предупреждения написано:")
    @И("в заглавии этого предупреждения написано {string}")
    public void headingOfThisWarning(String title) {
        try {
            alert.$x(".//span[@class]")
                    .shouldHave(exactText(title));
        } catch (AssertionError error) {
            throw new TestError(INVALID_ALERT_TITLE, error);
        }
    }

    @А("в содержании этого предупреждения написано:")
    @А("в содержании этого предупреждения написано {string}")
    public void contentsOfThisWarning(String content) {
        try {
            alert.$x(".//div[@class = 'ant-modal-confirm-content']")
                    .shouldHave(exactText(content));
        } catch (AssertionError error) {
            throw new TestError(INVALID_ALERT_CONTENT, error);
        }
    }

    @Когда("нажимаю кнопку {string} у этого предупреждения")
    @Когда("я нажимаю кнопку {string} у этого предупреждения")
    public void pushTheButtonOfThisWarning(String buttonText) {
        Selenide
                .actions()
                .pause(100)
                .click(alert.$x(format(".//button[.='%s']", buttonText)).shouldBe(enabled))
                .perform();
    }
}
