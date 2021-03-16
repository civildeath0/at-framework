package ru.sberbank.smartoffice.at.pageobjects.classes;

import lombok.Getter;
import lombok.experimental.FieldDefaults;
import ru.sberbank.smartoffice.at.annotations.Name;
import ru.sberbank.smartoffice.at.pageobjects.core.button.Button;
import ru.sberbank.smartoffice.at.pageobjects.core.page.Page;
import ru.sberbank.smartoffice.at.pageobjects.core.table.Column;
import ru.sberbank.smartoffice.at.pageobjects.core.table.Table;

import static com.codeborne.selenide.Selenide.$;
import static lombok.AccessLevel.PRIVATE;

/**
 * <h1>
 * Репрезентация страницы
 * <a href=https://ift2-app2.smartoffice.sberbank.ru:15443/references/companies>"Справочник компаний"</a>
 * SmartOffice
 * </h1>
 */
@Name("Справочник компаний") @Getter
@FieldDefaults(level = PRIVATE, makeFinal = true)
public final class CompaniesPage extends Page {

    Button addCompany;
    Button backToList;

    Table companiesTable;

    public CompaniesPage() {
        addCompany = new Button("Добавить компанию");
        backToList = new Button("Назад к списку");
        companiesTable = Table
                .builder()
                .name("Компании")
                .table($("table"))
                .column(Column.class, "Наименование")
                .column(Column.class, "Адрес сайта")
                .column(Column.class, "Автор(ФИО)")
                .column(Column.class, "Дата и время последнего изменения")
                .build();
    }
}
