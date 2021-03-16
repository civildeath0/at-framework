package ru.sberbank.smartoffice.at.pageelements.fields;

import com.codeborne.selenide.SelenideElement;
import ru.sberbank.smartoffice.at.errors.TestError;
import ru.sberbank.smartoffice.at.pageobjects.core.field.Field;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.$x;
import static java.lang.String.format;
import static ru.sberbank.smartoffice.at.errors.TestErrorMessage.INVALID_TEXT;

/**
 * <h1>Репрезентация поля с текстовым заполнителем</h1>
 */
public class PlaceholderField extends Field {

    public PlaceholderField(String name, SelenideElement element) {
        super(name, element);
    }

    public PlaceholderField(String name, String placeholder) {
        super(name, $x(format("//input[@placeholder='%s']", placeholder)));
    }

    public PlaceholderField(String name) {
        this(name, name);
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
    public void shouldHaveError(String text) {
        try {
            getElement().$x(format("./following-sibling::div[contains" +
                    "(@class, 'form-module__error') and (text()='%s')]", text)).should(exist);
        } catch (AssertionError error) {
            throw new TestError(format("У поля \"%s\" нет предупреждения с текстом \"%s\"", getName(), text), error);
        }
    }

    @Override
    public void shouldNotHaveError() {
        SelenideElement warning = getElement()
                .$x("./following-sibling::div[contains(@class, 'form-module__error')]");
        try {
            warning.should(not(exist));
        } catch (AssertionError error) {
            throw new TestError(format("У поля \"%s\" есть предупреждение: %s",
                    getName(), warning.getText()), error);
        }
    }

    @Override
    public void selectValue(String value) {
        throw new UnsupportedOperationException(format("Невозможно выбрать значение \"%s\" в текстовом поле", value));
    }
}
