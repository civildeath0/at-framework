package ru.sberbank.smartoffice.at.steps.commons;

import com.codeborne.selenide.SelenideElement;
import io.cucumber.java.Transpose;
import io.cucumber.java.it.Ma;
import io.cucumber.java.ru.И;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import ru.sberbank.smartoffice.at.errors.Collector;
import ru.sberbank.smartoffice.at.pageobjects.core.table.Table;
import ru.sberbank.smartoffice.at.world.World;

import java.util.List;
import java.util.Map;
import java.util.stream.IntStream;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.$x;
import static java.lang.String.format;
import static lombok.AccessLevel.PRIVATE;

@AllArgsConstructor
@FieldDefaults(level = PRIVATE, makeFinal = true)
public final class TablesSteps {

    World world;

    @И("в таблице {string} отсутствует строка:")
    public void rowAbsent(String tableName, @Transpose Map<String, String> filledColumns) {
        getRow(tableName, filledColumns).shouldBe(not(exist));
    }

    @И("в таблице {string} присутствует строка")
    public void rowInTable(String tableName, @Transpose Map<String, String> filledColumns) {
        getRow(tableName, filledColumns).shouldBe(visible);
    }

    @И("в таблице {string} присутствуют записи")
    public void rowsInTable(String tableName, @Transpose List<Map<String, String>> rows) {
        Collector collector = new Collector();
        rows.forEach(filledColumns -> {
            try {
                rowInTable(tableName, filledColumns);
            } catch (AssertionError error) {
                collector.addError(error);
            }
        });
        collector.verify();
    }

    @И("нажимаю на строку в таблице {string}")
    @И("я нажимаю на строку в таблице {string}")
    public void clickRow(String tableName, @Transpose Map<String, String> filledColumns) {
        getRow(tableName, filledColumns).click();
    }

    @И("нажимаю на редактирование у строки из таблицы {string}")
    @И("я нажимаю на редактирование у строки из таблицы {string}")
    public void clickOnChangeButtonInRow(String tableName, @Transpose Map<String, String> textByColumns) {
        getRow(tableName, textByColumns).$x("(.//td[(contains(@class, 'delete'))])[1]").click();
    }

    @И("нажимаю на крестик у строки из таблицы {string}")
    @И("я нажимаю на крестик у строки из таблицы {string}")
    public void clickOnRowCross(String tableName, @Transpose Map<String, String> textByColumns) {
        getRow(tableName, textByColumns).$x(".//td[(contains(@class, 'delete'))]").click();
    }

    @И("выбираю отображение {string}")
    @И("я выбираю отображение {string}")
    @И("выбираю отображение всех строк за {string}")
    @И("я выбираю отображение всех строк за {string}")
    public void displayRowsForPeriod(String period) {
        $x(format("//p[contains(@class, 'header-module')][text()='%s']", period)).click();
    }

    private SelenideElement getRow(String tableName, @Transpose Map<String, String> filledColumns) {
        return world
                .getCurrentPage()
                .getElement(tableName)
                .as(Table.class)
                .getRow(filledColumns);
    }
}
