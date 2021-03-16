package ru.sberbank.smartoffice.at.pageobjects.classes;

import lombok.EqualsAndHashCode;
import lombok.Value;
import ru.sberbank.smartoffice.at.annotations.Name;
import ru.sberbank.smartoffice.at.pageelements.buttons.FilterButton;
import ru.sberbank.smartoffice.at.pageelements.columns.DateColumn;
import ru.sberbank.smartoffice.at.pageelements.fields.SearchField;
import ru.sberbank.smartoffice.at.pageelements.fields.SelectableField;
import ru.sberbank.smartoffice.at.pageobjects.core.button.Button;
import ru.sberbank.smartoffice.at.pageobjects.core.page.Page;
import ru.sberbank.smartoffice.at.pageobjects.core.table.Column;
import ru.sberbank.smartoffice.at.pageobjects.core.table.Table;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$x;

/**
 * <h1>
 * Репрезентация страницы
 * <a href=https://ift2-app2.smartoffice.sberbank.ru:15443/assignments>"Задачи"</a>
 * SmartOffice
 * </h1>
 */
@Name("Задачи") @Value
@EqualsAndHashCode(callSuper = true)
public class AssignmentsPage extends Page {

    Button add;
    Button reload;

    FilterButton filter;

    SearchField search;

    SelectableField priority;
    SelectableField outdated;
    SelectableField status;

    FilterButton clear;
    FilterButton cancel;
    FilterButton apply;

    Table tasks;

    public AssignmentsPage() {

        tasks = Table.builder()
                .name("Задачи")
                .table($("table"))
                .column(Column.class, "Наименование")
                .column(DateColumn.class, "Дата исполнения")
                .column(Column.class, "Приоритет")
                .column(Column.class, "Статус")
                .column(Column.class, "Привязка")
                .build();

        add = new Button("Добавить задачу");
        reload = new Button("Обновить", $x("//img[@alt='refresh']"));

        filter = new FilterButton("Фильтр", $x("//img[@alt='filter']"));

        search = new SearchField("Поиск", $x("//input[@placeholder='Поиск...']"));

        priority = new SelectableField("Приоритет", "Все", 1);
        outdated = new SelectableField("Просроченные", "Все", 2);
        status = new SelectableField("Статус", "Все", 3);

        clear = new FilterButton("Сбросить");
        cancel = new FilterButton("Отменить");
        apply = new FilterButton("Применить");
    }
}
