package ru.sberbank.smartoffice.at.steps.commons;

import com.codeborne.selenide.SelenideElement;
import io.cucumber.java.ru.Затем;

import static com.codeborne.selenide.Selenide.$;
import static java.lang.String.format;

public final class SideMenuSteps {

    private final SelenideElement sideMenu;

    public SideMenuSteps() {
        sideMenu = $("div[class^=leftMenuBar-module__container]");
    }

    @Затем("нажал на раздел {string}")
    @Затем("выбрал подраздел {string}")
    public void chooseSection(String sectionName) {
        sideMenu.$x(format(".//li[.='%s']", sectionName)).click();
    }
}
