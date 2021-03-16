package ru.sberbank.smartoffice.at.pageelements.fields;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.Keys;
import org.openqa.selenium.interactions.Actions;
import ru.sberbank.smartoffice.at.pageobjects.core.field.Field;

import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;

public class TextEditorField extends Field {

    /**
     * Конструктор класса {@link Field}.
     *
     * @param name    имя поля
     * @param element {@link SelenideElement}, указывающий на текстовое поле
     */
    public TextEditorField(String name, SelenideElement element) {
        super(name, element);
    }

    public TextEditorField(String name) {
        super(name, $x("//div[contains(@class, 'rdw-editor-main')]//div[@role]"));
    }


    @Override
    public void shouldHaveError(String text) {
    }

    @Override
    public void shouldNotHaveError() {
    }

    @Override
    public void clear() {
        Actions actionObj = new Actions(getWebDriver());
        actionObj
                .moveToElement(getElement().$x(".//span"))
                .click()
                .keyDown(Keys.COMMAND)
                .pause(50)
                .sendKeys(Keys.chord("A"))
                .pause(50)
                .keyUp(Keys.COMMAND)
                .sendKeys(Keys.chord(Keys.BACK_SPACE))
                .perform();
    }
}
