package ru.sberbank.smartoffice.at.pageobjects.core.table;

import com.codeborne.selenide.SelenideElement;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NonNull;
import lombok.experimental.FieldDefaults;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static java.lang.String.format;
import static lombok.AccessLevel.PRIVATE;

/**
 * <h1>Репрезентация столбца таблицы</h1>
 */
@Getter @Builder @AllArgsConstructor
@FieldDefaults(level = PRIVATE, makeFinal = true)
public class Column {

    @NonNull Integer index;
    @NonNull SelenideElement table;
    @NonNull String title;

    /**
     * Проверяет отображение столбца на web-странице.
     */
    public void shouldVisible() {
        table.$("thead").$x(format(".//th[%d]", index))
                .should(visible, text(title));
    }

    /**
     * Конвертирует, передаваемый в сценарии текст, к xpath.
     *
     * @param text конвертируемый текст
     *
     * @return конвертированный текст, в виде части xpath, используемого
     * при поиске строки в таблице
     */
    public String getFilteredValue(String text) {
        return format(".='%s'", text);
    }
}
