package ru.sberbank.smartoffice.at.pageelements.fields;


import com.codeborne.selenide.SelenideElement;
import ru.sberbank.smartoffice.at.pageobjects.core.field.Field;

import static com.codeborne.selenide.Selenide.$x;
import static java.lang.String.format;

/**
 * <h1>Репрезентация поля с возможностью изменения размера</h1>
 */

public class TextAreaField extends Field {

    public TextAreaField(String name, SelenideElement element) {
        super(name, element);
    }

    public TextAreaField(String name) {
        super(name, $x(format("//textarea[@placeholder='%s']", name)));
    }

    @Override
    public void selectValue(String value) {
        throw new UnsupportedOperationException(format("Невозможно выбрать значение \"%s\" в текстовом поле", value));
    }

    @Override
    public void shouldHaveError(String text) {
        throw new AssertionError("У поля типа \"TextArea\" не может быть предупреждения!");
    }

    @Override
    public void shouldNotHaveError() {
        throw new AssertionError("У поля типа \"TextArea\" не может быть предупреждения!");
    }
}
