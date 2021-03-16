package ru.sberbank.smartoffice.at.pageobjects.classes.detailed;

import lombok.EqualsAndHashCode;
import lombok.Value;
import ru.sberbank.smartoffice.at.annotations.Name;
import ru.sberbank.smartoffice.at.pageelements.buttons.AnimationButton;
import ru.sberbank.smartoffice.at.pageelements.checkboxes.CheckboxWrapped;
import ru.sberbank.smartoffice.at.pageelements.fields.PlaceholderField;
import ru.sberbank.smartoffice.at.pageobjects.core.button.Button;
import ru.sberbank.smartoffice.at.pageobjects.core.page.Page;

import static com.codeborne.selenide.Selenide.$x;

/**
 * <h1>
 * Репрезентация страницы
 * <a href=https://ift2-app2.smartoffice.sberbank.ru:15443/references/documents/create>"Категория материала"</a>
 * SmartOffice
 * </h1>
 */
@Name("Детали категории")
@EqualsAndHashCode(callSuper = true)
@Value
public class MaterialCategory extends Page {

    AnimationButton save;

    Button delete;
    Button backToList;

    PlaceholderField name;
    PlaceholderField shortName;

    CheckboxWrapped showInMaterials;

    public MaterialCategory() {
        save = new AnimationButton("Сохранить");
        name = new PlaceholderField("Название", "Введите название категории");
        shortName = new PlaceholderField("Короткое название", $x("(//input)[3]"));
        delete = new Button("Удалить");
        backToList = new Button("Назад к списку");
        showInMaterials = new CheckboxWrapped("Показывать на виджете \"Материалы\"");
    }
}
