package ru.sberbank.smartoffice.at.pageelements.fields;

import com.codeborne.selenide.SelenideElement;
import ru.sberbank.smartoffice.at.errors.TestError;
import ru.sberbank.smartoffice.at.pageobjects.core.field.Field;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.$x;
import static java.lang.String.format;
import static ru.sberbank.smartoffice.at.errors.TestErrorMessage.INVALID_TEXT;

/**
 * <h1>Репрезентация поля со временем</h1>
 */
public class TimeField extends Field {

    public TimeField(String name, Integer index) {
        this(name, $x(format("(//input[@placeholder='ЧЧ:ММ'])[%d]", index)));
    }

    public TimeField(String name, SelenideElement element) {
        super(name, element);
    }

    @Override
    public void shouldHaveText(String text) {
        try {
            getElement().should(visible, attribute("value", text));
        } catch (AssertionError error) {
            throw new TestError(INVALID_TEXT, error, getName());
        }
    }

    @Override
    public void sendRandomKeys() {
        throw new UnsupportedOperationException("Невозможно ввести случайный текст в поле времени");
    }

    @Override
    public void selectValue(String value) {
        getElement().click();
        selectFromDropDownList(value);
    }

    @Override
    public void shouldHaveError(String text) {
        try {
            getElement().$x(format("./ancestor::div[5]/following-sibling::div[contains" +
                    "(@class, 'form-module__error') and (text()='%s')]", text)).should(exist);
        } catch (AssertionError error) {
            throw new TestError(format("У поля \"%s\" нет предупреждения с текстом \"%s\"", getName(), text), error);
        }
    }

    @Override
    public void shouldNotHaveError() {
        try {
            getElement().$x("./ancestor::div[5]/following-sibling::div[contains" +
                    "(@class, 'form-module__error')]").shouldNot(exist);
        } catch (AssertionError error) {
            throw new TestError(format("У поля \"%s\" есть предупреждение!", getName()), error);
        }
    }
}
