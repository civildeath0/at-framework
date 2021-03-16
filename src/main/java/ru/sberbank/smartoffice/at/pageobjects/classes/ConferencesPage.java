package ru.sberbank.smartoffice.at.pageobjects.classes;

import lombok.EqualsAndHashCode;
import lombok.Value;
import ru.sberbank.smartoffice.at.annotations.Name;
import ru.sberbank.smartoffice.at.pageelements.buttons.FilterButton;
import ru.sberbank.smartoffice.at.pageelements.columns.FullTimeColumn;
import ru.sberbank.smartoffice.at.pageelements.fields.CalendarField;
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
 * <a href=https://ift2-app2.smartoffice.sberbank.ru:15443/events/conferences>"Мероприятия"</a>
 * SmartOffice
 * </h1>
 */
@Value
@Name("Мероприятия")
@EqualsAndHashCode(callSuper = true)
public class ConferencesPage extends Page {

    Button addConference;
    Button reload;
    Button leftArrow;
    Button rightArrow;

    FilterButton filter;

    SearchField search;

    CalendarField dateMeetingFrom;
    CalendarField dateMeetingTo;
    CalendarField dateLastChangeFrom;
    CalendarField dateLastChangeTo;

    SelectableField status;
    SelectableField type;
    SelectableField notes;

    FilterButton clear;
    FilterButton cancel;
    FilterButton apply;


    Table conferences;

    public ConferencesPage() {

        conferences = Table.builder()
                .name("Мероприятия")
                .table($("table"))
                .column(FullTimeColumn.class, "Начало")
                .column(FullTimeColumn.class, "Окончание")
                .column(Column.class, "Название")
                .column(Column.class, "Место проведения")
                .column()
                .column(Column.class, "Состояние встречи")
                .column()
                .build();

        addConference = new Button("Добавить мероприятие");
        filter = new FilterButton("Фильтр", $x("//img[@alt='filter']"));
        reload = new Button("Обновить", $x("//img[@alt='refresh']"));
        leftArrow = new Button("Влево", $x("//img[@alt='left']"));
        rightArrow = new Button("Влево", $x("//img[@alt='right']"));

        search = new SearchField("Поиск", $x("//input[@placeholder='Поиск...']"));

        dateMeetingFrom = new CalendarField("Дата начала проведения события от", 1);
        dateMeetingTo = new CalendarField("Дата начала проведения события до", 2);
        dateLastChangeFrom = new CalendarField("Дата последнего изменения от", 3);
        dateLastChangeTo = new CalendarField("Дата последнего изменения до", 4);

        status = new SelectableField("Статус событий", "Все", 1);
        type = new SelectableField("Тип событий", "Все", 2);
        notes = new SelectableField("Наличие заметок", "Все", 3);

        clear = new FilterButton("Сбросить");
        cancel = new FilterButton("Отменить");
        apply = new FilterButton("Применить");
    }
}
