package ru.sberbank.smartoffice.at.pageelements.fields;

import com.codeborne.selenide.SelenideElement;
import ru.sberbank.smartoffice.at.errors.TestError;
import ru.sberbank.smartoffice.at.pageobjects.core.field.Field;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$x;
import static com.codeborne.selenide.Selenide.actions;
import static java.lang.String.format;
import static ru.sberbank.smartoffice.at.errors.TestErrorMessage.INVALID_TEXT;

/**
 * <h1>Репрезентация поискового поля</h1>
 * <p>
 * Такое поле имеет крестик для стирания введенных символов. К тому же
 * после совершения поиска под полем появляется список
 * значений, удовлетворяющих условиям поиска.
 */
public class SearchField extends Field {

    public SearchField(String name, String placeholder) {
        this(name, $x(format("//div[contains(@class, 'placeholder')][text()='%s']", placeholder)));
    }

    public SearchField(String name, SelenideElement element) {
        super(name, element);
    }

    @Override
    public void shouldHaveText(String text) {
        try {
            getElement()
                    .$x(".//following-sibling::div[@title]")
                    .shouldBe(visible, text(text));
        } catch (AssertionError error) {
            throw new TestError(INVALID_TEXT, error, getName());
        }
    }

    @Override
    public void sendKeys(String text) {
        actions().moveToElement(getElement()).click()
                .pause(50).sendKeys(text).perform();
    }

    @Override
    public void sendRandomKeys() {
        throw new UnsupportedOperationException("Невозможно ввести случайный текст в поле поиска");
    }

    @Override
    public void selectValue(String value) {
        sendKeys(value);
        selectFromDropDownList(value);
    }

    @Override
    public void shouldHaveError(String text) {

    }

    @Override
    public void shouldNotHaveError() {

    }
}
