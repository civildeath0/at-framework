package ru.sberbank.smartoffice.at.pageelements.checkboxes;

import ru.sberbank.smartoffice.at.pageobjects.core.checkbox.Checkbox;

import static com.codeborne.selenide.Condition.attributeMatching;
import static com.codeborne.selenide.Selenide.$x;
import static java.lang.String.format;

/**
 * <h1>Репрезентация чекбокса с последующим появлением или исчесновение элемента</h1>
 */
public class CheckboxWrapped extends Checkbox {

    public CheckboxWrapped(String text) {
        super(text, $x(format("//span[text()='%s'][1]/preceding-sibling::" +
                "span[contains(@class, 'ant-checkbox')][1]", text)));
    }

    @Override
    public void isSelected() {
        getElement().shouldHave(attributeMatching("class", ".*checked"));
    }

    @Override
    public void isNotSelected() {
        getElement().shouldNotHave(attributeMatching("class", ".*checked"));
    }
}