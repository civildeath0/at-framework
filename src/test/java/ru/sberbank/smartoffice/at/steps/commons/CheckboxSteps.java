package ru.sberbank.smartoffice.at.steps.commons;

import io.cucumber.java.DocStringType;
import io.cucumber.java.ru.И;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import ru.sberbank.smartoffice.at.pageobjects.core.checkbox.Checkbox;
import ru.sberbank.smartoffice.at.world.World;

import java.io.ByteArrayInputStream;

import static com.codeborne.selenide.Selenide.$x;
import static io.qameta.allure.Allure.addAttachment;
import static lombok.AccessLevel.PRIVATE;

@AllArgsConstructor
@FieldDefaults(level = PRIVATE, makeFinal = true)
public final class CheckboxSteps {

    World world;

    @DocStringType
    private String checkboxText(String str) {
        addAttachment("CheckBoxName", "text/html",
                new ByteArrayInputStream(str.getBytes()), "png");
        return str;
    }

//    @И("нажимаю чекбокс")
//    public void checkTheCheckbox(String name) {
//        $x("//input[@type='checkbox']").click();
//    }

    @И("нажимаю чекбокс")
    @И("нажимаю чекбокс {string}")
    public void checkTheCheckbox(String checkboxName) {
        getCheckbox(checkboxName).click();
    }

    @И("нажимаю на текст чекбокса {string}")
    public void checkTheCheckboxText(String checkboxName) {
        getCheckbox(checkboxName).clickText();
    }

    @И("чекбокс {string} выбран")
    public void checkboxSelected(String checkboxName) {
        getCheckbox(checkboxName).isSelected();
    }

    @И("чекбокс {string} не выбран")
    public void checkboxNotSelected(String checkboxName) {
        getCheckbox(checkboxName).isNotSelected();
    }

    private Checkbox getCheckbox(String text) {
        return world.getCurrentPage().getElement(text).as(Checkbox.class);
    }
}
