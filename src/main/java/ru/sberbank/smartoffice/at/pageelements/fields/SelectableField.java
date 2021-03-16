package ru.sberbank.smartoffice.at.pageelements.fields;

import ru.sberbank.smartoffice.at.errors.TestError;
import ru.sberbank.smartoffice.at.pageobjects.core.field.Field;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.$x;
import static java.lang.String.format;
import static ru.sberbank.smartoffice.at.errors.TestErrorMessage.INVALID_TEXT;

/**
 * <h1>Репрезентация поля выбора значения</h1>
 * <p>
 * В такое поле нельзя вводить текст, можно только воспользоваться
 * доступными значениями, появляющимися в выпадающем списке
 * после совершения нажатия по нему.
 */
public class SelectableField extends Field {

    public SelectableField(String name, String placeholder) {
        super(name, $x(format("//div[contains(@class, 'placeholder')][text()='%s']", placeholder)));
    }

    public SelectableField(String name, String placeholder, int index) {
        super(name, $x(format("(//div[contains(@class, 'placeholder')][text()='%s'])[%d]", placeholder, index)));
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
        throw new UnsupportedOperationException(format("Невозможно ввести текст \"%s\" в поле выбора значения", text));
    }

    @Override
    public void sendRandomKeys() {
        throw new UnsupportedOperationException("Невозможно ввести случайный текст в поле выбора значения");
    }

    @Override
    public void selectValue(String value) {
        getElement().$x("./following-sibling::div").click();
        getElement().$x(format("./ancestor::div[4]/descendant::li[.='%s']", value)).click();
    }

    @Override
    public void shouldHaveValue(String value) {
        getElement().$x("./following-sibling::div").click();
        getElement().$x(format("./ancestor::div[4]/descendant::li[.='%s']", value)).shouldBe(visible);
    }

    @Override
    public void shouldNotHaveValue(String value) {
        getElement().$x("./following-sibling::div").click();
        getElement().$x(format("./ancestor::div[4]/descendant::li[.='%s']", value)).shouldNot(exist);
    }

    @Override
    public void shouldHaveError(String text) {
        try {
            getElement().$x(format("./ancestor::div[3]/following-sibling::div[contains" +
                    "(@class, 'form-module__error') and (text()='%s')]", text)).should(exist);
        } catch (AssertionError error) {
            throw new TestError(format("У поля \"%s\" нет предупреждения с текстом \"%s\"", getName(), text), error);
        }
    }

    @Override
    public void shouldNotHaveError() {
        try {
            getElement().$x("./ancestor::div[3]/following-sibling::div[contains" +
                    "(@class, 'form-module__error')]").shouldNot(exist);
        } catch (AssertionError error) {
            throw new TestError(format("У поля \"%s\" есть предупреждение!", getName()), error);
        }
    }
}
