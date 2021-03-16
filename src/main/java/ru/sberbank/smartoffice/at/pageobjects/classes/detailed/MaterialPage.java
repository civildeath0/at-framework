package ru.sberbank.smartoffice.at.pageobjects.classes.detailed;

import lombok.EqualsAndHashCode;
import lombok.Value;
import org.checkerframework.checker.units.qual.C;
import ru.sberbank.smartoffice.at.annotations.Name;
import ru.sberbank.smartoffice.at.pageelements.buttons.AnimationButton;
import ru.sberbank.smartoffice.at.pageelements.buttons.WrappedRadioButton;
import ru.sberbank.smartoffice.at.pageelements.fields.*;
import ru.sberbank.smartoffice.at.pageobjects.core.button.Button;
import ru.sberbank.smartoffice.at.pageobjects.core.checkbox.Checkbox;
import ru.sberbank.smartoffice.at.pageobjects.core.page.Page;
import static com.codeborne.selenide.Selenide.$x;

/**
 * <h1>
 * Репрезентация страницы
 * <a href=https://ift2-app2.smartoffice.sberbank.ru:15443/materials/create>"Материал"</a>
 * SmartOffice
 * </h1>
 */
@Name("Детали материала")
@EqualsAndHashCode(callSuper = true)
@Value
public class MaterialPage extends Page {

    PlaceholderField title;

    SelectableField personal;
    SelectableField category;

    WrappedRadioButton event;
    WrappedRadioButton meeting;
    WrappedRadioButton flight;

    SearchField eventSearch;

    Checkbox widget;

    TextAreaField shortName;

    AnimationButton save;

    Button widgetContinue;
    Button widgetCancel;
    Button backToList;
    Button delete;

    public MaterialPage() {
        title = new PlaceholderField("Название","Введите название материала");
        personal = new SelectableField("Приватность", "Выберите видимость");
        category = new SelectableField("Категория", "Выберите категорию");
        event = new WrappedRadioButton("К мероприятию", "Интересные места", "Мероприятия");
        meeting = new WrappedRadioButton("Ко встрече", "Интересные места", "Встречи");
        flight = new WrappedRadioButton("К перелету", "Интересные места", "Перелеты");
        eventSearch = new SearchField("Поиск события", $x("(//div[contains(@class, 'ant-select-selection__rendered')])[3]"));
        widget = new Checkbox("Вывести материал на виджет",$x(""));
        widgetContinue = new AnimationButton("Продолжить");
        widgetCancel = new AnimationButton("Отмена");
        shortName = new TextAreaField("Короткое название");
        save = new AnimationButton("Сохранить");
        backToList = new Button("Назад к списку");
        delete = new Button("Удалить");
    }
}
