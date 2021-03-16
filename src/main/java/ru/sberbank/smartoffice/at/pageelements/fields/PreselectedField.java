package ru.sberbank.smartoffice.at.pageelements.fields;

import ru.sberbank.smartoffice.at.pageobjects.core.field.Field;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.$x;
import static java.lang.String.format;

public class PreselectedField extends Field {

    public PreselectedField(String name) {
        super(name, $x(format("//span[text()='%s']/..//div[@class='ant-select-selection__rendered']", name)));
    }

    @Override public void shouldHaveText(String text) {
        getElement().$x("./div[@title]").shouldBe(visible, text(text));
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
        getElement().$x("./div").click();
        getElement().$x(format("./ancestor::div[4]/descendant::li[.='%s']", value)).click();
    }

    @Override
    public void shouldHaveValue(String value) {
        getElement().$x("./div").click();
        getElement().$x(format("./ancestor::div[4]/descendant::li[.='%s']", value)).shouldBe(visible);
    }

    @Override
    public void shouldNotHaveValue(String value) {
        getElement().$x("./div").click();
        getElement().$x(format("./ancestor::div[4]/descendant::li[.='%s']", value)).shouldNot(exist);
    }

    @Override
    public void shouldHaveError(String text) {

    }

    @Override
    public void shouldNotHaveError() {

    }
}
