package ru.sberbank.smartoffice.at.pageobjects.classes.detailed;

import lombok.EqualsAndHashCode;
import lombok.Value;
import ru.sberbank.smartoffice.at.annotations.Name;
import ru.sberbank.smartoffice.at.pageelements.buttons.AnimationButton;
import ru.sberbank.smartoffice.at.pageelements.fields.PlaceholderField;
import ru.sberbank.smartoffice.at.pageelements.fields.SelectableField;
import ru.sberbank.smartoffice.at.pageelements.fields.TextAreaField;
import ru.sberbank.smartoffice.at.pageobjects.Trackable;
import ru.sberbank.smartoffice.at.pageobjects.core.button.Button;
import ru.sberbank.smartoffice.at.pageobjects.core.page.Page;
import ru.sberbank.smartoffice.at.visitors.tracker.Tracker;

/**
 * <h1>
 * Репрезентация страницы
 * <a href=https://ift2-app2.smartoffice.sberbank.ru:15443/contacts/create>"Контакт"</a>
 * SmartOffice
 * </h1>
 */
@Name("Детали контакта")
@EqualsAndHashCode(callSuper = true)
@Value
public class ContactPage extends Page implements Trackable {

    PlaceholderField lastName;
    PlaceholderField firstName;
    PlaceholderField secondName;

    SelectableField sex;

    AnimationButton apply;
    AnimationButton create;

    Button backToList;

    public ContactPage() {

        lastName = new PlaceholderField("Фамилия", "Фамилия на русском");
        firstName = new PlaceholderField("Имя","Имя на русском");
        secondName = new PlaceholderField("Отчество", "Отчество на русском");
        sex = new SelectableField("Пол","Выберите пол");
        backToList = new Button("Назад к списку");
        apply = new AnimationButton("Применить");
        create = new AnimationButton("Создать контакт");
    }

    @Override
    public void accept(Tracker tracker) {
        tracker.track(this);
    }
}
