package ru.sberbank.smartoffice.at.pageobjects.classes;

import lombok.Getter;
import lombok.experimental.FieldDefaults;
import ru.sberbank.smartoffice.at.annotations.Name;
import ru.sberbank.smartoffice.at.pageelements.buttons.FilterButton;
import ru.sberbank.smartoffice.at.pageelements.fields.CalendarField;
import ru.sberbank.smartoffice.at.pageelements.fields.SearchField;
import ru.sberbank.smartoffice.at.pageelements.fields.SelectableField;
import ru.sberbank.smartoffice.at.pageobjects.core.button.Button;
import ru.sberbank.smartoffice.at.pageobjects.core.page.Page;
import ru.sberbank.smartoffice.at.pageobjects.core.table.Column;
import ru.sberbank.smartoffice.at.pageobjects.core.table.Table;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$x;
import static lombok.AccessLevel.PRIVATE;

/**
 * <h1>
 * Репрезентация страницы
 * <a href=https://ift2-app2.smartoffice.sberbank.ru:15443/contacts>"Контакты"</a>
 * SmartOffice
 * </h1>
 */
@Name("Контакты") @Getter
@FieldDefaults(level = PRIVATE, makeFinal = true)
public final class ContactsPage extends Page {

    Button addContact;
    Button reload;

    FilterButton filter;

    SearchField search;

    CalendarField dateMeetingFrom;
    CalendarField dateMeetingTo;
    CalendarField dateLastChangeFrom;
    CalendarField dateLastChangeTo;

    SelectableField status;
    SelectableField notes;
    SelectableField birthday;
    SelectableField dayFrom;
    SelectableField monthFrom;
    SelectableField yearFrom;
    SelectableField dayTo;
    SelectableField monthTo;
    SelectableField yearTo;

    FilterButton clear;
    FilterButton cancel;
    FilterButton apply;

    Table contacts;

    public ContactsPage() {

        contacts = Table.builder()
                .name("Контакты")
                .table($("table"))
                .column(Column.class, "ФИО")
                .column(Column.class, "Дата рождения")
                .column(Column.class, "Организация")
                .column(Column.class, "Должность")
                .column(Column.class, "Структура")
                .column()
                .column(Column.class, "Статус")
                .build();

        addContact = new Button("Добавить контакт");
        reload = new Button("Обновить", $x("//img[@alt='refresh']"));

        filter = new FilterButton("Фильтр", $x("//img[@alt='filter']"));

        search = new SearchField("Поиск", $x("//input[@placeholder='Поиск...']"));

        dateMeetingFrom = new CalendarField("Дата начала проведения события от", 1);
        dateMeetingTo = new CalendarField("Дата начала проведения события до", 2);
        dateLastChangeFrom = new CalendarField("Дата последнего изменения от", 3);
        dateLastChangeTo = new CalendarField("Дата последнего изменения до", 4);

        status = new SelectableField("Статус", "Все", 1);
        notes = new SelectableField("Наличие заметок", "Все", 2);
        birthday = new SelectableField("Поздравлять с днем рождения", "Все", 3);
        dayFrom = new SelectableField("Дата рождения (день) от", "День",1);
        monthFrom = new SelectableField("Дата рождения (месяц) от", "Месяц", 1);
        yearFrom = new SelectableField("Дата рождения (год) от", "Год", 1);
        dayTo = new SelectableField("Дата рождения (день) до", "День",2);
        monthTo = new SelectableField("Дата рождения (месяц) до", "Месяц", 2);
        yearTo = new SelectableField("Дата рождения (год) до", "Год", 2);

        clear = new FilterButton("Сбросить");
        cancel = new FilterButton("Отменить");
        apply = new FilterButton("Применить");
    }
}
