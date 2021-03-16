package ru.sberbank.smartoffice.at.pageobjects.core.button;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import lombok.Getter;
import lombok.experimental.FieldDefaults;
import ru.sberbank.smartoffice.at.pageobjects.core.element.Element;

import static com.codeborne.selenide.Condition.enabled;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$x;
import static java.lang.String.format;
import static lombok.AccessLevel.PRIVATE;

/**
 * <h1>Репрезентация кнопки страницы</h1>
 */
@Getter
@FieldDefaults(level = PRIVATE, makeFinal = true)
public class Button extends Element {

    /**
     * Конструктор класса {@link Button}.
     *
     * @param name    имя кнопки
     * @param element {@link SelenideElement}, указывающий на кнопку
     */
    public Button(String name, SelenideElement element) {
        super(name, element);
    }

    /**
     * Конструктор класса {@link Button}.
     *
     * @param name    имя кнопки
     * @param element {@link SelenideElement}, указывающий на кнопку
     */
    public Button(String name, String buttonText) {
        super(name, $x(format("//button[.='%s']", buttonText)));
    }

    public Button(String name, String buttonText, Integer index) {
        super(name, $x(format("(//button[.='%s'])[%d]", buttonText, index)));
    }

    /**
     * Конструктор класса {@link Button}.
     * <p>
     * После инициалзации, метод {@link #getElement()}, будет
     * возвращать {@link SelenideElement}, указывающий на
     * "//button[.='%s']", где %s - <code>buttonText</code>.
     *
     * @param buttonText имя кнопки, совпадающиее с
     *                   ее текстом на странице
     */
    public Button(String buttonText) {
        this(buttonText, buttonText);
    }

    /**
     * Совершает нажатие по кнопке.
     * <p>
     * Возможно переопределение поведения в наследуемых классах.
     */
    public void click() {
        try {
            getElement().shouldBe(visible, enabled).click();
        } catch (AssertionError error) {

        }
    }
}
