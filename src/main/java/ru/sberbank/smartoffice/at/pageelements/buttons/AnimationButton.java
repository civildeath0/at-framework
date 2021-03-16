package ru.sberbank.smartoffice.at.pageelements.buttons;

import com.codeborne.selenide.SelenideElement;
import ru.sberbank.smartoffice.at.pageobjects.core.button.Button;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$x;

/**
 * <h1>Репрезентация кнопки с последующей анимацией</h1>
 */
public class AnimationButton extends Button {

    SelenideElement spinner;
    SelenideElement alertWindow = $("div[class=ant-modal-body]");

    /**
     * Конструктор класса {@link AnimationButton}
     *
     * @param buttonText название кнопки
     */
    public AnimationButton(String buttonText) {
        super(buttonText);
        spinner = $x("//i[contains(@aria-label, \"icon: loading\")]");
    }

    @Override
    public void click() {
        getElement().click();
        spinner.shouldBe(visible);
        if (!isWarningAppear()) {
            spinner.shouldBe(not(exist));
        }
    }

    private boolean isWarningAppear() {
        try {
            alertWindow.waitUntil(visible, 5000);
        } catch (AssertionError error) {
            return false;
        }
        return true;
    }
}
