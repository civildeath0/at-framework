package ru.sberbank.smartoffice.at.pageobjects.classes.detailed;

import lombok.EqualsAndHashCode;
import lombok.Value;
import ru.sberbank.smartoffice.at.annotations.Name;
import ru.sberbank.smartoffice.at.pageelements.fields.PlaceholderField;
import ru.sberbank.smartoffice.at.pageobjects.core.button.Button;
import ru.sberbank.smartoffice.at.pageobjects.core.page.Page;

@Name("Новый доверенный адрес")
@EqualsAndHashCode(callSuper = true)
@Value
public class TrustedSenderPage extends Page {
    PlaceholderField email;

    Button save;

    public TrustedSenderPage() {
        email = new PlaceholderField("Электронная почта", "Введите почту");
        save = new Button("Сохранить");
    }
}
