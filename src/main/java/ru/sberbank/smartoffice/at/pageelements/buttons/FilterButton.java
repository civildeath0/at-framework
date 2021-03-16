package ru.sberbank.smartoffice.at.pageelements.buttons;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import ru.sberbank.smartoffice.at.pageobjects.core.button.Button;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.$x;

/**
 * <h1>Репрезентация кнопки с последующим появлением или исчесновение окна фильтрации</h1>
 */
public class FilterButton extends Button {

    SelenideElement hiddenModal = $x("//div[contains(@class, 'ant-modal-mask-hidden')]");

    public FilterButton(String buttonText) {
        super(buttonText);
    }

    public FilterButton(String buttonText, SelenideElement element) {
        super(buttonText, element);
    }

    @Override
    public void click() {
        Selenide.actions().pause(250).click(getElement()).perform();
        hiddenModal.shouldBe(hiddenModal.exists() ?
                visible : not(exist));
    }
}
