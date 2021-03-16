package ru.sberbank.smartoffice.at.pageobjects.classes;

import lombok.EqualsAndHashCode;
import lombok.Value;
import ru.sberbank.smartoffice.at.annotations.Name;
import ru.sberbank.smartoffice.at.pageobjects.core.button.Button;
import ru.sberbank.smartoffice.at.pageobjects.core.page.Page;
import ru.sberbank.smartoffice.at.pageobjects.core.table.Column;
import ru.sberbank.smartoffice.at.pageobjects.core.table.Table;

import static com.codeborne.selenide.Selenide.$;

/**
 * <h1>
 * Репрезентация страницы
 * <a href=https://ift2-app2.smartoffice.sberbank.ru:15443/references/documents>"Категории материалов"</a>
 * SmartOffice
 * </h1>
 */
@Name("Категории материалов") @Value
@EqualsAndHashCode(callSuper = true)
public class MaterialCategories extends Page {

    Button addCategory;

    Table categories;

    public MaterialCategories() {
        addCategory = new Button("Добавить категорию");
        this.categories = Table.builder()
                .name("Категории")
                .table($("table").$("tbody"))
                .column(Column.class, "Дата и время последнего изменения")
                .column(Column.class, "Наименование")
                .column(Column.class, "Автор")
                .build();
    }
}
