package ru.sberbank.smartoffice.at.steps.commons;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.Transpose;
import io.cucumber.java.ru.И;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import ru.sberbank.smartoffice.at.errors.Collector;
import ru.sberbank.smartoffice.at.errors.TestError;
import ru.sberbank.smartoffice.at.pageelements.fields.SelectableField;
import ru.sberbank.smartoffice.at.pageobjects.core.element.Element;
import ru.sberbank.smartoffice.at.pageobjects.core.errors.FieldsExpectedValuesError;
import ru.sberbank.smartoffice.at.pageobjects.core.field.Field;
import ru.sberbank.smartoffice.at.world.World;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static com.codeborne.selenide.CollectionCondition.size;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.sleep;
import static java.lang.String.format;
import static java.util.stream.Collectors.toSet;
import static lombok.AccessLevel.PRIVATE;
import static ru.sberbank.smartoffice.at.errors.TestErrorMessage.INVALID_TEXT;
import static ru.sberbank.smartoffice.at.utilities.Columnist.fromTable;

@AllArgsConstructor
@FieldDefaults(level = PRIVATE, makeFinal = true)
public final class FieldsSteps {

    World world;

    @И("жду {int} секунд")
    public void waitSeconds(int seconds) {
        sleep(seconds * 1000);
    }

    @И("ввожу текст в поле {string}")
    @И("я ввожу текст в поле {string}")
    public void sendLongTextIntoField(String fieldName, String longText) {
        sendKeysIntoField(longText, fieldName);
    }

    @И("ввожу текст {string} в поле {string}")
    @И("я ввожу текст {string} в поле {string}")
    public void sendKeysIntoField(String text, String fieldName) {
        getField(fieldName).sendKeys(text);
    }

    @И("ввожу текст в поля:")
    @И("я ввожу текст в поля:")
    public void sendKeysIntoFields(DataTable dataTable) {
        fromTable(dataTable).mapColumns("[Название поля]", "[Текст]")
                .forEach((fieldName, text) -> sendKeysIntoField(text, fieldName));
    }

    @И("в поле {string} записан текст")
    @И("в поле {string} записан текст {string}")
    public void fieldContainsValue(String fieldName, String text) {
        getField(fieldName).shouldHaveText(text);
    }

    @И("в полях записан текст")
    @И("в полях записан соответствующий текст")
    public void fieldsContainsValues(DataTable dataTable) {
        Collector collector = new Collector();
        fromTable(dataTable).mapColumns("[Название поля]", "[Текст]")
                .forEach((fieldName, text) -> {
                    try {
                        fieldContainsValue(fieldName, text);
                    } catch (AssertionError error) {
                        collector.addError(error);
                    }
                });
        collector.verify();
    }

    @И("очищаю текст в поле {string}")
    @И("я очищаю текст в поле {string}")
    public void clearTextField(String fieldName) {
        getField(fieldName).clear();
    }

    @И("очищаю текст в полях")
    @И("я очищаю текст в полях")
    public void clearTextFields(List<String> fieldName) {
        fieldName.forEach(this::clearTextField);
    }

    @И("заменяю текст в полях")
    @И("я заменяю текст в полях")
    public void changeFieldsValue(DataTable dataTable) {
        fromTable(dataTable).mapColumns("[Название поля]", "[Текст]")
                .forEach((fieldName, text) -> clearTextField(fieldName));
        sendKeysIntoFields(dataTable);
    }

    @И("ввожу случайный текст в поле {string}")
    @И("я ввожу случайный текст в поле {string}")
    public void sendRandomKeysIntoField(String fieldName) {
        getField(fieldName).sendRandomKeys();
    }

    @И("ввожу случайный текст в поля:")
    @И("я ввожу случайный текст в поля:")
    public void enterRandomTextIntoFields(List<String> fieldNames) {
        fieldNames.forEach(this::sendRandomKeysIntoField);
    }

    @И("выбираю значение {string} в поле {string}")
    @И("я выбираю значение {string} в поле {string}")
    public void selectValueInTheField(String value, String fieldName) {
        getField(fieldName).selectValue(value);
    }

    @И("выбираю значение в следующих полях:")
    @И("я выбираю значение в следующих полях:")
    public void selectValueInTheFields(DataTable dataTable) {
        fromTable(dataTable).mapColumns("[Название поля]", "[Значение]")
                .forEach((fieldName, value) -> selectValueInTheField(value, fieldName));
    }

    @И("у поля {string} есть/появилось предупреждение")
    @И("у поля {string} есть предупреждение c текстом {string}")
    public void warningAppearedForField(String fieldName, String warningText) {
        getField(fieldName).shouldHaveError(warningText);
    }

    @И("проверяю, что у полей есть соответствующие предупреждения:")
    public void warningAppearedForFields(DataTable dataTable) {
        fromTable(dataTable).mapColumns("[Название поля]", "[Текст предупреждения]")
                .forEach(this::warningAppearedForField);
    }

    @И("проверяю, что есть предупреждение с текстом {string} у полей:")
    public void warningAppearedForFields(String warningText, List<String> fieldNames) {
        fieldNames.forEach(fieldName -> warningAppearedForField(fieldName, warningText));
    }

    @И("проверяю, что у поля {string} отсутствует предупреждение")
    public void warningNotExistForField(String fieldName) {
        getField(fieldName).shouldNotHaveError();
    }

    @И("проверяю, что у следующих полей отсутствуют предупреждения:")
    public void warningNotExistForFields(List<String> fieldNames) {
        fieldNames.forEach(this::warningNotExistForField);
    }

    @И("проверяю, что в поле {string} можно выбрать значение {string}")
    public void fieldHaveValue(String fieldName, String value) {
        try {
            getField(fieldName).shouldHaveValue(value);
        } catch (AssertionError error) {
            throw new TestError(format("В поле \"%s\" недоступно для выбора значение \"%s\"!", fieldName, value), error);
        }
    }

    @И("проверяю, что в поле {string} нельзя выбрать значение {string}")
    public void fieldDontHaveValue(String fieldName, String value) {
        try {
            getField(fieldName).shouldNotHaveValue(value);
        } catch (AssertionError error) {
            throw new TestError(format("В поле \"%s\" доступно для выбора значение \"%s\"!", fieldName, value), error);
        }
    }

    @И("проверяю, что в поле {string} можно выбрать значения:")
    public void fieldHaveValues(String fieldName, @Transpose List<String> values) {
        values.forEach(value -> fieldHaveValue(fieldName, value));
    }

    @И("проверяю, что в поле {string} нельзя выбрать значения:")
    public void fieldDontHaveValues(String fieldName, @Transpose List<String> values) {
        values.forEach(value -> fieldDontHaveValue(fieldName, value));
    }

    @И("проверяю, что в поле {string} доступны для выбора только значения:")
    public void fieldHaveOnlyValues(String fieldName, List<String> values) {
        ElementsCollection options = getFieldOptions(fieldName);
        try {
            options.shouldHave(size(values.size()));
        } catch (AssertionError error) {
            throw new TestError
                    (format("Количество вариантов значений поля \"%s\" не совпадает с ожидаемым!", fieldName), error);
        }
        compareOptions(options, values);
    }

    @И("на странице появилась кнопка {string}")
    public void buttonAppeared(String elementName) {
        getElement(elementName).getElement().shouldBe(visible);
    }

    private void compareOptions(ElementsCollection actualOptions, List<String> expectedOptions) {
        Set<String> actualSet = actualOptions.stream().map(SelenideElement::getText).collect(toSet());
        Set<String> expectedSet = new HashSet<>(expectedOptions);
        if (!actualSet.equals(expectedSet)) {
            throw new FieldsExpectedValuesError
                    ("Актуальный список значений поля не совпадает с ожидаемым!", actualSet, expectedSet);
        }
    }

    private ElementsCollection getFieldOptions(String fieldName) {
        SelenideElement field = getSelectableField(fieldName).getElement();
        field.parent().click();
        return field.$$x(".//following::li");
    }

    private SelectableField getSelectableField(String fieldName) {
        return getElement(fieldName).as(SelectableField.class);
    }

    private Element getElement(String elementName) {
        return world.getCurrentPage().getElement(elementName);
    }

    private Field getField(String fieldName) {
        return world.getCurrentPage().getElement(fieldName).as(Field.class);
    }
}
