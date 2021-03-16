package ru.sberbank.smartoffice.at.pageobjects.classes.detailed;

import lombok.EqualsAndHashCode;
import lombok.Value;
import ru.sberbank.smartoffice.at.annotations.Name;
import ru.sberbank.smartoffice.at.pageelements.buttons.AnimationButton;
import ru.sberbank.smartoffice.at.pageelements.fields.*;
import ru.sberbank.smartoffice.at.pageobjects.Trackable;
import ru.sberbank.smartoffice.at.pageobjects.core.button.Button;
import ru.sberbank.smartoffice.at.pageobjects.core.page.Page;
import ru.sberbank.smartoffice.at.visitors.tracker.Tracker;

import static com.codeborne.selenide.Selenide.$x;

/**
 * <h1>
 * Репрезентация страницы
 * <a href=https://ift2-app2.smartoffice.sberbank.ru:15443/assignments/create>"Задача"</a>
 * SmartOffice
 * </h1>
 */
@Name("Детали задачи")
@EqualsAndHashCode(callSuper = true)
@Value
public class AssignmentPage extends Page implements Trackable {

    PlaceholderField title;
    PlaceholderField text;

    SelectableField priority;
    SelectableField materialCategory;

    PreselectedField binding;

    PreselectedField privacy;

    Button addFromList;
    Button addNewComment;
    Button addFile;
    Button postComment;
    Button backToList;
    Button materialUndo;
    Button materialUpload;
    Button materialChange;
    Button materialReset;
    Button materialChoose;
    Button delete;
    Button reopen;
    Button sendMail;
    Button setDone;

    SearchField executorFromList;

    AnimationButton save;

    CalendarField date;

    public AssignmentPage() {
        backToList = new Button("Назад к списку");
        addFile = new Button("Добавить");
        materialChoose = new Button("Выбрать");
        materialUndo = new Button("Отменить");
        materialUpload = new Button("Загрузить");
        materialChange = new Button("Заменить");
        materialReset = new Button("Сбросить");
        addFromList = new Button("Добавить из списка");
        addNewComment = new Button("Добавить новый комментарий");
        postComment = new Button("Отправить комментарий");
        delete = new Button("Удалить");
        reopen = new Button("Переоткрыть");
        sendMail = new Button("Отправить по почте");
        setDone = new Button("Отметить выполненной");
        executorFromList = new SearchField("Исполнитель из списка",
                $x("(//div[contains(@class, 'selected-value')])[3]/.."));
        save = new AnimationButton("Сохранить");
        date = new CalendarField("Дата исполнения", 1);
        title = new PlaceholderField("Заголовок");
        text = new PlaceholderField("Текст поручения");
        privacy = new PreselectedField("Приватность");
        materialCategory = new SelectableField("Категория", "Выберите категорию");
        priority = new SelectableField("Приоритет", "Приоритет");
        binding = new PreselectedField("Без привязки");
    }

    @Override
    public void accept(Tracker tracker) {
        tracker.track(this);
    }
}
