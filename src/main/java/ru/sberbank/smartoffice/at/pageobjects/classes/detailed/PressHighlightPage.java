package ru.sberbank.smartoffice.at.pageobjects.classes.detailed;

import lombok.EqualsAndHashCode;
import lombok.Value;
import ru.sberbank.smartoffice.at.annotations.Name;
import ru.sberbank.smartoffice.at.pageelements.buttons.AnimationButton;
import ru.sberbank.smartoffice.at.pageelements.fields.*;
import ru.sberbank.smartoffice.at.pageobjects.core.button.Button;
import ru.sberbank.smartoffice.at.pageobjects.core.page.Page;

@Name("Детали PressHighlight") @Value
@EqualsAndHashCode(callSuper = true)
public class PressHighlightPage extends Page {

    SelectableField rubric;
    SearchField company;

    PlaceholderField title;
    PlaceholderField subtitle;

    PlaceholderField source;
    PlaceholderField sourceLink;

    TextEditorField textEditor;

    CalendarField publishDate;
    TimeField publishTime;

    Button create;
    Button backToList;

    public PressHighlightPage() {
        rubric = new SelectableField("Рубрика", "Выберите рубрику");
        company = new SearchField("Компания", "Выберите компанию");

        title = new PlaceholderField("Заголовок");
        subtitle = new PlaceholderField("Подзаголовок");

        textEditor = new TextEditorField("Текст новости");

        source = new PlaceholderField("Источник");
        sourceLink = new PlaceholderField("Ссылка на источник");

        publishDate = new CalendarField("Дата публикации", 1);
        publishTime = new TimeField("Время публикации", 1);

        create = new AnimationButton("Создать");
        backToList = new Button("Назад к списку");
    }
}
