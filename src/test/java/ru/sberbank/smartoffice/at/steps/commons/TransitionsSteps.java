package ru.sberbank.smartoffice.at.steps.commons;

import io.cucumber.java.ru.И;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import ru.sberbank.smartoffice.at.errors.TestError;
import ru.sberbank.smartoffice.at.pageobjects.core.page.Pages;
import ru.sberbank.smartoffice.at.world.World;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$x;
import static java.lang.String.format;
import static lombok.AccessLevel.PRIVATE;
import static ru.sberbank.smartoffice.at.errors.TestErrorMessage.INVALID_STATUS;

@AllArgsConstructor
@FieldDefaults(level = PRIVATE, makeFinal = true)
public final class TransitionsSteps {

    World world;

    @И("оказался на странице {string}")
    @И("нахожусь на странице {string}")
    @И("я оказался на странице {string}")
    public void setCurrentPage(String pageName) {
        world.setCurrentPage(Pages.getPage(pageName));
    }

    @И("сущность имеет статус {string}")
    @И("сущность перешла в статус {string}")
    public void entityStatus(String status) {
        try {
            $x(format("//span[text()='%s']", status)).shouldBe(visible);
        } catch (AssertionError error) {
            throw new TestError(INVALID_STATUS, error);
        }
    }
}
