package ru.sberbank.smartoffice.at.pageobjects.core.checkbox;

import com.codeborne.selenide.SelenideElement;
import lombok.Getter;
import lombok.experimental.FieldDefaults;
import ru.sberbank.smartoffice.at.pageobjects.core.element.Element;

import static com.codeborne.selenide.Condition.selected;
import static com.codeborne.selenide.Selenide.$x;
import static java.lang.String.format;
import static lombok.AccessLevel.PRIVATE;

/**
 * <h1>Репрезентация чекбокса</h1>
 */
@Getter
@FieldDefaults(level = PRIVATE, makeFinal = true)
public class Checkbox  extends Element {

    /**
     * Конструктор класса {@link Checkbox}.
     *
     * @param name    имя чекбокса
     * @param element {@link SelenideElement}, указывающий на чекбокс
     */
    public Checkbox(String name, SelenideElement element) {
        super(name, element);
    }

    public Checkbox(String text) {
        this(text, $x(format("//span[@class='checkboxLabel'][text()='%s']/preceding-sibling::span[1]", text)));
    }

    public void isSelected() {
        getElement().$x("./preceding-sibling::input[1]").shouldBe(selected);
    }

    public void isNotSelected() {
        getElement().$x("./preceding-sibling::input[1]").shouldNotBe(selected);
    }

    public final void click() {
        getElement().click();
    }

    public final void clickText() {
        getElement().$x("./following-sibling::span[1]").click();
    }

}