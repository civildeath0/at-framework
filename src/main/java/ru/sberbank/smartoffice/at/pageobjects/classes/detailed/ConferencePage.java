package ru.sberbank.smartoffice.at.pageobjects.classes.detailed;

import lombok.EqualsAndHashCode;
import lombok.Value;
import ru.sberbank.smartoffice.at.annotations.Name;
import ru.sberbank.smartoffice.at.pageelements.buttons.AnimationButton;
import ru.sberbank.smartoffice.at.pageelements.checkboxes.CheckboxWrapped;
import ru.sberbank.smartoffice.at.pageelements.fields.*;
import ru.sberbank.smartoffice.at.pageobjects.core.button.Button;
import ru.sberbank.smartoffice.at.pageobjects.core.page.Page;

/**
 * <h1>
 * Репрезентация страницы
 * <a href=https://ift2-app2.smartoffice.sberbank.ru:15443/events/conferences/create>"Мероприятие"</a>
 * SmartOffice
 * </h1>
 */
@Name("Детали мероприятия")
@Value @EqualsAndHashCode(callSuper = true)
public class ConferencePage extends Page {

    CalendarField startDate;
    CalendarField endDate;

    TimeField startTime;
    TimeField endTime;

    PlaceholderField name;
    PlaceholderField address;

    TextAreaField locationComment;

    SelectableField timeZone;
    SelectableField materialCategory;

    PreselectedField privacy;

    AnimationButton save;
    AnimationButton publish;

    Button backToList;
    Button add;
    Button materialUndo;
    Button materialUpload;
    Button materialChange;
    Button materialReset;
    Button materialChoose;
    Button imageAccept;

    CheckboxWrapped interestingPlaces;

    TextEditorField descriptionArea;

    public ConferencePage() {
        startDate = new CalendarField("Дата начала", 1);
        endDate = new CalendarField("Дата окончания", 2);
        startTime = new TimeField("Время начала", 1);
        endTime = new TimeField("Время окончания", 2);
        timeZone = new SelectableField("Часовой пояс", "Выберите часовой пояс");
        name = new PlaceholderField("Название");
        address = new PlaceholderField("Место проведения");
        locationComment = new TextAreaField("Комментарий к месту проведения");
        save = new AnimationButton("Сохранить");
        publish = new AnimationButton("Опубликовать");
        imageAccept = new Button("Применить");
        backToList = new Button("Назад к списку");
        add = new Button("Добавить");
        materialChoose = new Button("Выбрать");
        materialUndo = new Button("Отменить");
        materialUpload = new Button("Загрузить");
        materialChange = new Button("Заменить");
        materialReset = new Button("Сбросить");
        privacy = new PreselectedField("Приватность");
        materialCategory = new SelectableField("Категория", "Выберите категорию");
        interestingPlaces = new CheckboxWrapped("Интересные места");
        descriptionArea = new TextEditorField("Описание");
    }
}
