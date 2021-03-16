package ru.sberbank.smartoffice.at.pageobjects.core.field;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import com.thedeanda.lorem.LoremIpsum;
import lombok.Getter;
import lombok.experimental.FieldDefaults;
import ru.sberbank.smartoffice.at.errors.TestError;
import ru.sberbank.smartoffice.at.pageobjects.core.element.Element;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.$x;
import static java.lang.String.format;
import static lombok.AccessLevel.PRIVATE;
import static ru.sberbank.smartoffice.at.errors.TestErrorMessage.INVALID_TEXT;

/**
 * <h1>Абстракция, описывающая поле страницы</h1>
 */
@Getter
@FieldDefaults(level = PRIVATE, makeFinal = true)
public abstract class Field extends Element {

    /**
     * Конструктор класса {@link Field}.
     *
     * @param name    имя поля
     * @param element {@link SelenideElement}, указывающий на текстовое поле
     */
    protected Field(String name, SelenideElement element) {
        super(name, element);
    }

    /**
     * Вспомогательный метод, используемый в дочерних классах
     * для выбора значения из выпадающего списка.
     *
     * @param value выбираемое значение
     */
    protected final void selectFromDropDownList(String value) {
        $x(format("//li[contains(text(), '%s')]", value)).click();
    }

    /**
     * Вводит текст в поле.
     *
     * @param text вводимый текст
     */
    public void sendKeys(String text) {
        SelenideElement element = getElement().shouldBe(visible);
        Selenide.actions()
                .moveToElement(element)
                .click()
                .pause(50)
                .sendKeys(text)
                .pause(50)
                .perform();
    }

    /**
     * Вводит случайный текст в поле.
     */
    public void sendRandomKeys() {
        sendKeys(LoremIpsum.getInstance().getWords(7, 12));
    }

    /**
     * Очищает текст в поле.
     */
    public void clear() {
        getElement().shouldBe(visible).clear();
    }

    /**
     * Проверяет элемент на отображание в нем ожидаемого текста.
     *
     * @param text ожидаемый текст
     */
    public void shouldHaveText(String text) {
        try {
            getElement().shouldBe(visible).shouldHave(exactText(text));
        } catch (AssertionError error) {
            throw new TestError(INVALID_TEXT, error, getName());
        }
    }

    /**
     * Выбирает значение для поля из возможных вариантов.
     *
     * @param value выбираемое значение
     */
    public void selectValue(String value) {
        getElement().click();
        selectFromDropDownList(value);
    }

    /**
     * Проверяет поле на наличие предупреждающего текста внизу.
     *
     * @param text содержание предупреждающего текста
     */
    public abstract void shouldHaveError(String text);

    public abstract void shouldNotHaveError();


    public void shouldHaveValue(String value) {
        getElement().click();
        $x(format("//li[contains(text(), '%s')]", value)).shouldBe(visible);
    }

    public void shouldNotHaveValue(String value) {
        getElement().click();
        $x(format("//li[contains(text(), '%s')]", value)).shouldNot(exist);
    }
}
