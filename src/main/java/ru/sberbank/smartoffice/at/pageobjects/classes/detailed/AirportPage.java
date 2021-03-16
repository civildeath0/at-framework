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
 * <a href=https://ift2-app2.smartoffice.sberbank.ru:15443/references/airports/create>"Аэропорт"</a>
 * SmartOffice
 * </h1>
 */
@Name("Детали аэропорта")
@EqualsAndHashCode(callSuper = true)
@Value
public class AirportPage extends Page implements Trackable {

    PlaceholderField name;
    PlaceholderField code;
    PlaceholderField city;
    PlaceholderField country;
    PlaceholderField englishName;
    PlaceholderField englishCity;
    PlaceholderField englishCountry;

    AnimationButton save;

    Button backToList;
    Button delete;


    public AirportPage() {
        name = new PlaceholderField("Название", "Введите название аэропорта");
        code = new PlaceholderField("Код", "Введите код аэропорта");
        city = new PlaceholderField("Город", "Введите город");
        country = new PlaceholderField("Страна", "Введите страну");
        englishName = new PlaceholderField("Название по английски");
        englishCity = new PlaceholderField("Город по английски");
        englishCountry = new PlaceholderField("Страна по английски");
        save = new AnimationButton("Сохранить");
        backToList = new Button("Назад к списку");
        delete = new Button("Удалить");
    }

    @Override
    public void accept(Tracker tracker) {
        tracker.track(this);
    }
}
