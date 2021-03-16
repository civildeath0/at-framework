package ru.sberbank.smartoffice.at.pageobjects.classes.detailed;

import lombok.EqualsAndHashCode;
import lombok.Value;
import ru.sberbank.smartoffice.at.annotations.Name;
import ru.sberbank.smartoffice.at.pageelements.buttons.AnimationButton;
import ru.sberbank.smartoffice.at.pageelements.fields.*;
import ru.sberbank.smartoffice.at.pageobjects.Trackable;
import ru.sberbank.smartoffice.at.visitors.tracker.Tracker;
import ru.sberbank.smartoffice.at.pageobjects.core.button.Button;
import ru.sberbank.smartoffice.at.pageobjects.core.checkbox.Checkbox;
import ru.sberbank.smartoffice.at.pageobjects.core.page.Page;
import ru.sberbank.smartoffice.at.pageobjects.core.table.Column;
import ru.sberbank.smartoffice.at.pageobjects.core.table.Table;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$x;

/**
 * <h1>
 * Репрезентация страницы
 * <a href=https://ift2-app2.smartoffice.sberbank.ru:15443/events/meetings/flights/create>"Перелет"</a>
 * SmartOffice
 * </h1>
 */
@Name("Детали перелета")
@EqualsAndHashCode(callSuper = true)
@Value
public class FlightPage extends Page implements Trackable {

    CalendarField departureDate;
    CalendarField arrivalDate;

    TimeField departureTime;
    TimeField arrivalTime;

    PlaceholderField name;
    PlaceholderField flightNumber;
    PlaceholderField firstName;
    PlaceholderField lastName;
    PlaceholderField fathersName;
    PlaceholderField company;
    PlaceholderField position;

    SelectableField departureTimeZone;
    SelectableField arrivalTimeZone;

    SearchField departurePlace;
    SearchField arrivalPlace;

    Button addFromList;
    Button createNew;
    Button addContact;
    Button addMaterial;
    Button add;
    Button backToList;
    Button publish;

    AnimationButton delete;
    AnimationButton save;

    Table participantsTable;

    Checkbox contactCreating;

    public FlightPage() {
        departureDate = new CalendarField("Дата вылета", $x("(//input[@mask='11.11.1111'])[1]"));
        arrivalDate = new CalendarField("Дата прибытия", $x("(//input[@mask='11.11.1111'])[2]"));
        departureTime = new TimeField("Время вылета", $x("(//input[@placeholder='ЧЧ:ММ'])[1]"));
        arrivalTime = new TimeField("Время прибытия", $x("(//input[@placeholder='ЧЧ:ММ'])[2]"));
        departureTimeZone = new SelectableField("Часовой пояс вылета", "Выберите часовой пояс", 1);
        arrivalTimeZone = new SelectableField("Часовой пояс прибытия", "Выберите часовой пояс", 2);
        name = new PlaceholderField("Название");
        flightNumber = new PlaceholderField("Рейс");
        departurePlace = new SearchField("Откуда", $x("(//div[@role='combobox']/div[1])[3]"));
        arrivalPlace = new SearchField("Куда", $x("(//div[@role='combobox']/div[1])[1]"));
        firstName = new PlaceholderField("Имя");
        lastName = new PlaceholderField("Фамилия");
        fathersName = new PlaceholderField("Отчество");
        company = new PlaceholderField("Компания");
        position = new PlaceholderField("Должность");
        addFromList = new Button("Добавить из списка");
        createNew = new Button("Создать нового");
        addContact = new Button("Добавить контакт", "Добавить", 1);
        addMaterial = new Button("Добавить материал" , "Добавить", 2);
        add = new Button("Добавить");
        backToList = new Button("Назад к списку");
        save = new AnimationButton("Сохранить");
        publish = new Button("Опубликовать");
        delete = new AnimationButton("Удалить");
        contactCreating = new Checkbox("Добавить в список контактов");
        participantsTable = Table
                .builder()
                .name("Участники")
                .table($("table"))
                .column(Column.class, "Фамилия Имя Отчество")
                .column(Column.class, "Организация и должность")
                .column(Column.class, "Статус")
                .build();
    }

    @Override
    public void accept(Tracker tracker) {
        tracker.track(this);
    }
}
