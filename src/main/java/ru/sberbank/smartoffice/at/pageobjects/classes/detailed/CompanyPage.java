package ru.sberbank.smartoffice.at.pageobjects.classes.detailed;

import lombok.EqualsAndHashCode;
import lombok.Value;
import ru.sberbank.smartoffice.at.annotations.Name;
import ru.sberbank.smartoffice.at.pageelements.buttons.AnimationButton;
import ru.sberbank.smartoffice.at.pageelements.fields.PlaceholderField;
import ru.sberbank.smartoffice.at.pageobjects.Trackable;
import ru.sberbank.smartoffice.at.pageobjects.core.button.Button;
import ru.sberbank.smartoffice.at.pageobjects.core.page.Page;
import ru.sberbank.smartoffice.at.visitors.tracker.Tracker;

/**
 * <h1>
 * Репрезентация страницы
 * <a href=https://ift2-app2.smartoffice.sberbank.ru:15443/references/companies/create>"Компания"</a>
 * SmartOffice
 * </h1>
 */
@Name("Детали компании")
@EqualsAndHashCode(callSuper = true)
@Value
public class CompanyPage extends Page implements Trackable {

    PlaceholderField name;
    PlaceholderField link;

    AnimationButton save;

    Button accept;
    Button decline;
    Button backToList;
    Button deleteLogo;
    Button delete;

    public CompanyPage() {
        name = new PlaceholderField("Название", "Введите название компании");
        link = new PlaceholderField("Ссылка");
        save = new AnimationButton("Сохранить");
        accept = new Button("Применить");
        decline = new Button("Отменить");
        backToList = new Button("Назад к списку");
        deleteLogo = new Button("Удалить логотип", "Удалить");
        delete = new Button("Удалить");
    }

    @Override
    public void accept(Tracker tracker) {
        tracker.track(this);
    }
}
