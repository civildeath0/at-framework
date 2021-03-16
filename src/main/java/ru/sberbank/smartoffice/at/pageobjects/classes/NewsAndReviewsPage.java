package ru.sberbank.smartoffice.at.pageobjects.classes;

import lombok.EqualsAndHashCode;
import lombok.Value;
import ru.sberbank.smartoffice.at.annotations.Name;
import ru.sberbank.smartoffice.at.pageelements.buttons.FilterButton;
import ru.sberbank.smartoffice.at.pageelements.columns.DateColumn;
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
 * <a href=https://ift2-app2.smartoffice.sberbank.ru:15443/news/presshighlights>"Новости и обзоры"</a>
 * SmartOffice
 * </h1>
 */
@Name("Новости и обзоры") @Value
@EqualsAndHashCode(callSuper = true)
public class NewsAndReviewsPage extends Page {

    Button add;
    Button filter;
    Button reload;
    Button leftArrow;
    Button rightArrow;

    SearchField search;

    SelectableField status;
    SelectableField company;
    SelectableField notes;

    CalendarField dateFrom;
    CalendarField dateTo;

    Button clear;
    Button cancel;
    Button apply;

    Table news;

    public NewsAndReviewsPage() {

        news = Table.builder()
                .name("Новости")
                .table($("table"))
                .column(DateColumn.class, "Дата")
                .column(Column.class, "Время публикации")
                .column(Column.class, "Заголовок")
                .column(Column.class, "Компания")
                .column(Column.class, "Рубрика")
                .column(Column.class, "Источник")
                .column(Column.class, "Автор")
                .column(Column.class, "Статус")
                .column()
                .build();

        filter = new FilterButton("Фильтр", $x("//img[@alt='filter']"));
        reload = new Button("Обновить", $x("//img[@alt='refresh']"));
        leftArrow = new Button("Влево", $x("//img[@alt='left']"));
        rightArrow = new Button("Влево", $x("//img[@alt='right']"));

        search = new SearchField("Поиск", $x("//input[@placeholder='Поиск...']"));

        dateFrom = new CalendarField("От", 1);
        dateTo = new CalendarField("До", 2);

        status = new SelectableField("Статус", "Все", 1);
        company = new SelectableField("Компания", "Все", 2);
        notes = new SelectableField("Наличие заметок", "Все", 3);

        clear = new Button("Сбросить");
        cancel = new Button("Отменить");
        apply = new FilterButton("Применить");
        add = new Button("Добавить");
    }
}
