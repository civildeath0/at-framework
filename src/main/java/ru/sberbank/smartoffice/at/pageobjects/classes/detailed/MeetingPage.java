package ru.sberbank.smartoffice.at.pageobjects.classes.detailed;

import lombok.EqualsAndHashCode;
import lombok.Value;
import ru.sberbank.smartoffice.at.annotations.Name;
import ru.sberbank.smartoffice.at.pageelements.buttons.AnimationButton;
import ru.sberbank.smartoffice.at.pageelements.fields.CalendarField;
import ru.sberbank.smartoffice.at.pageelements.fields.PlaceholderField;
import ru.sberbank.smartoffice.at.pageelements.fields.SelectableField;
import ru.sberbank.smartoffice.at.pageelements.fields.TimeField;
import ru.sberbank.smartoffice.at.pageobjects.Trackable;
import ru.sberbank.smartoffice.at.visitors.tracker.Tracker;
import ru.sberbank.smartoffice.at.pageelements.fields.*;
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
 * <a href=https://ift2-app2.smartoffice.sberbank.ru:15443/events/meetings/create>"Встреча"</a>
 * SmartOffice
 * </h1>
 */
@Name("Детали встречи")
@EqualsAndHashCode(callSuper = true)
@Value
public class MeetingPage extends Page implements Trackable {

    CalendarField startDate;
    CalendarField endDate;

    TimeField startTime;
    TimeField endTime;

    PlaceholderField description;
    PlaceholderField descriptionComment;
    PlaceholderField address;
    PlaceholderField locationComment;
    PlaceholderField lastName;
    PlaceholderField firstName;
    PlaceholderField fathersName;
    PlaceholderField company;
    PlaceholderField position;

    SelectableField timeZone;
    SelectableField type;

    AnimationButton save;

    Button addFromList;
    Button createNew;
    Button addContact;
    Button addMaterial;
    Button backToList;
    Button delete;

    AnimationButton publish;
    AnimationButton unpublish;

    Table participantsTable;

    Checkbox addContactList;

    TextEditorField descriptionArea;

    public MeetingPage() {
        startDate = new CalendarField("Дата начала", $x("(//input[@mask='11.11.1111'])[1]"));
        endDate = new CalendarField("Дата окончания", $x("(//input[@mask='11.11.1111'])[2]"));
        startTime = new TimeField("Время начала", $x("(//input[@placeholder='ЧЧ:ММ'])[1]"));
        endTime = new TimeField("Время окончания", $x("(//input[@placeholder='ЧЧ:ММ'])[2]"));
        description = new PlaceholderField("Название");
        descriptionComment = new PlaceholderField("Комментарий к названию");
        address = new PlaceholderField("Место проведения");
        locationComment = new PlaceholderField("Комментарий к месту проведения");
        timeZone = new SelectableField("Часовой пояс", "Выберите часовой пояс");
        type = new SelectableField("Тип", "Выберите тип встречи");
        lastName = new PlaceholderField("Фамилия");
        firstName = new PlaceholderField("Имя");
        fathersName = new PlaceholderField("Отчество");
        company = new PlaceholderField("Компания");
        position = new PlaceholderField("Должность");
        save = new AnimationButton("Сохранить");
        addFromList = new Button("Добавить из списка");
        createNew = new Button("Создать нового");
        addContact = new Button("Добавить контакт", "Добавить", 1);
        addMaterial = new Button("Добавить материал" , "Добавить", 2);
        backToList = new Button("Назад к списку");
        delete = new Button("Удалить");
        publish = new AnimationButton("Опубликовать");
        unpublish = new AnimationButton("Снять с публикации");
        addContactList = new Checkbox("Добавить в список контактов");
        descriptionArea = new TextEditorField("Описание");
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
