package ru.sberbank.smartoffice.at.steps.commons;

import io.cucumber.java.ru.И;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import ru.sberbank.smartoffice.at.errors.TestError;
import ru.sberbank.smartoffice.at.pageobjects.core.button.Button;
import ru.sberbank.smartoffice.at.world.World;

import java.util.List;

import static com.codeborne.selenide.Condition.*;
import static java.lang.String.format;
import static lombok.AccessLevel.PRIVATE;

@RequiredArgsConstructor
@FieldDefaults(level = PRIVATE, makeFinal = true)
public final class ButtonsSteps {

    World world;

    @И("нажимаю кнопку {string}")
    @И("я нажимаю кнопку {string}")
    public void pressTheButtonWithText(String buttonText) {
        getButton(buttonText).click();
    }

    @И("кнопка {string} активна")
    public void buttonEnabled(String buttonText) {
        try {
            getButton(buttonText).getElement().shouldBe(visible, enabled);
        } catch (AssertionError error) {
            throw new TestError(format("Кнопка \"%s\" должна быть активна!", buttonText), error);
        }
    }

    @И("кнопки активны")
    public void buttonsEnabled(List<String> buttonsList) {
        buttonsList.forEach(this::buttonEnabled);
    }

    @И("кнопка {string} неактивна")
    public void buttonDisabled(String buttonText) {
        try {
            getButton(buttonText).getElement().shouldBe(visible, disabled);
        } catch (AssertionError error) {
            throw new TestError(format("Кнопка \"%s\" должна быть неактивна!", buttonText), error);
        }
    }

    @И("кнопки неактивны")
    @И("следующие кнопки неактивны")
    public void buttonsDisabled(List<String> buttonsList) {
        buttonsList.forEach(this::buttonDisabled);
    }

    private Button getButton(String buttonText) {
        return world.getCurrentPage().getElement(buttonText).as(Button.class);
    }
}
