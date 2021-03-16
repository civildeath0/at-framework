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
 * <a href=https://ift2-app2.smartoffice.sberbank.ru:15443/materials>"Материалы"</a>
 * SmartOffice
 * </h1>
 */
@Name("Материалы") @Value
@EqualsAndHashCode(callSuper = true)
public class MaterialsPage extends Page {

    Button addMaterial;
    Button reload;
    Button leftArrow;
    Button rightArrow;

    FilterButton filter;

    SearchField search;

    SelectableField category;
    SelectableField type;
    SelectableField notes;

    CalendarField dateMeetingFrom;
    CalendarField dateMeetingTo;
    CalendarField dateLastChangeFrom;
    CalendarField dateLastChangeTo;

    FilterButton clear;
    FilterButton cancel;
    FilterButton apply;

    Table materials;

    public MaterialsPage() {

        materials = Table.builder()
                .name("Материалы")
                .table($("table"))
                .column(Column.class, "Название")
                .column(Column.class, "Категория")
                .column(Column.class, "Тип")
                .column(Column.class, "Встреча")
                .column(DateColumn.class, "Дата начала события")
                .column()
                .column(Column.class, "Изменено")
                .build();

        addMaterial = new Button("Добавить материал");
        filter = new FilterButton("Фильтр", $x("//img[@alt='filter']"));
        reload = new Button("Обновить", $x("//img[@alt='refresh']"));
        leftArrow = new Button("Влево", $x("//img[@alt='left']"));
        rightArrow = new Button("Влево", $x("//img[@alt='right']"));

        search = new SearchField("Поиск", $x("//input[@placeholder='Поиск...']"));

        category = new SelectableField("Категория", "Все", 1);
        type = new SelectableField("Тип сущности", "Все", 2);
        notes = new SelectableField("Наличие заметок", "Все", 3);

        dateMeetingFrom = new CalendarField("Дата начала проведения события от", 1);
        dateMeetingTo = new CalendarField("Дата начала проведения события до", 2);
        dateLastChangeFrom = new CalendarField("Дата последнего изменения от", 3);
        dateLastChangeTo = new CalendarField("Дата последнего изменения до", 4);

        clear = new FilterButton("Сбросить");
        cancel = new FilterButton("Отменить");
        apply = new FilterButton("Применить");
    }
}
