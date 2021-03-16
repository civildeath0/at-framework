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
 * <a href=https://ift2-app2.smartoffice.sberbank.ru:15443/references/airports>"Справочник аэропортов"</a>
 * SmartOffice
 * </h1>
 */
@Value
@Name("Справочник аэропортов")
@EqualsAndHashCode(callSuper = true)
public class AirportsPage extends Page {

    Button addAirport;

    Table table;

    public AirportsPage() {
        table = Table.builder()
                .name("Аэропорты")
                .table($("table").$("tbody"))
                .column(Column.class,"Наименование")
                .column(Column.class,"Код")
                .column(Column.class,"Город")
                .column(Column.class, "Страна")
                .build();
        addAirport = new Button("Добавить аэропорт");
    }
}
