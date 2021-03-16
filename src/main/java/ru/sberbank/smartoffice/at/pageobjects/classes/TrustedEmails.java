package ru.sberbank.smartoffice.at.pageobjects.classes;

import lombok.EqualsAndHashCode;
import lombok.Value;
import ru.sberbank.smartoffice.at.annotations.Name;
import ru.sberbank.smartoffice.at.pageobjects.core.button.Button;
import ru.sberbank.smartoffice.at.pageobjects.core.page.Page;
import ru.sberbank.smartoffice.at.pageobjects.core.table.Column;
import ru.sberbank.smartoffice.at.pageobjects.core.table.Table;

import static com.codeborne.selenide.Selenide.$;

/**
 * <h1>
 * Репрезентация страницы
 * <a href=https://ift2-app2.smartoffice.sberbank.ru:15443/settings/trustedsender>"Доверенные email"</a>
 * SmartOffice
 * </h1>
 */
@Name("Доверенные email") @Value
@EqualsAndHashCode(callSuper = true)
public class TrustedEmails extends Page {

    Button addEmail;

    Table trustedEmails;

    public TrustedEmails() {
        addEmail = new Button("Добавить email");
        trustedEmails = Table
                .builder()
                .name("Доверенные email")
                .table($("table"))
                .column(Column.class, "Адрес email")
                .build();
    }
}
