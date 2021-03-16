package ru.sberbank.smartoffice.at.pageobjects.classes;

import lombok.EqualsAndHashCode;
import lombok.Value;
import ru.sberbank.smartoffice.at.annotations.Name;
import ru.sberbank.smartoffice.at.pageelements.buttons.FilterButton;
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
 * <a href=https://ift2-app2.smartoffice.sberbank.ru:15443/admin/users>"Администрирование пользователей"</a>
 * SmartOffice
 * </h1>
 */
@Name("Администрирование")
@Value
@EqualsAndHashCode(callSuper = true)
public class AdminUsersPage extends Page {

    Button add;
    Button reload;

    FilterButton filter;

    SearchField search;

    SelectableField status;
    SelectableField role;

    FilterButton clear;
    FilterButton cancel;
    FilterButton apply;

    Table users;

    public AdminUsersPage() {

        users = Table.builder()
                .name("Пользователи")
                .table($("table"))
                .column(Column.class, "Логин")
                .column(Column.class, "ФИО")
                .column(Column.class, "Роль")
                .column(Column.class, "Состояние")
                .column(Column.class, "Должность")
                .build();

        add = new Button("Добавить пользователя");
        reload = new Button("Обновить", $x("//img[@alt='refresh']"));

        filter = new FilterButton("Фильтр", $x("//img[@alt='filter']"));

        search = new SearchField("Поиск", $x("//input[@placeholder='Поиск...']"));

        status = new SelectableField("Состояние", "Все", 1);
        role = new SelectableField("Роль", "Все", 2);

        clear = new FilterButton("Сбросить");
        cancel = new FilterButton("Отменить");
        apply = new FilterButton("Применить");
    }
}
