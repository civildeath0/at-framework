package ru.sberbank.smartoffice.at.pageelements.buttons;

import com.codeborne.selenide.SelenideElement;
import ru.sberbank.smartoffice.at.pageobjects.core.button.Button;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$x;
import static java.lang.String.format;

/**
 * <h1>Репрезентация кнопки с последующим появлением или исчесновение элементов</h1>
 */
public class WrappedRadioButton extends Button {

    SelenideElement hiddenCheckBox;
    SelenideElement hiddenPlaceHolder;

    public WrappedRadioButton(String buttonText, String hiddenCheckBoxName, String hiddenPlaceHolderName) {
        super(buttonText, $x(format("//span[text()='%s']/..", buttonText)));
        this.hiddenCheckBox = $x(format("//span[text()='%s']", hiddenCheckBoxName));
        this.hiddenPlaceHolder = $x(format("//span[text()='%s']", hiddenPlaceHolderName));
    }

    @Override
    public void click() {
        getElement().click();
        hiddenCheckBox.shouldBe(visible);
        hiddenPlaceHolder.shouldBe(visible);
    }
}
