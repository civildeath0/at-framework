package ru.sberbank.smartoffice.at.pageelements.columns;

import com.codeborne.selenide.SelenideElement;
import lombok.experimental.FieldDefaults;
import ru.sberbank.smartoffice.at.pageobjects.core.table.Column;
import ru.sberbank.smartoffice.at.utilities.epochtime.EpochTime;

import java.util.Calendar;
import java.util.Date;
import java.util.regex.Matcher;

import static java.lang.String.format;
import static java.util.Calendar.*;
import static java.util.regex.Pattern.compile;
import static lombok.AccessLevel.PRIVATE;

/**
 * <h1>Репрезентация столбца с датой и временем</h1>
 */
@FieldDefaults(level = PRIVATE, makeFinal = true)
public class FullTimeColumn extends Column {

    Calendar calendar;

    /**
     * Конструктор класса {@link FullTimeColumn}.
     *
     * @param index     номер столбца
     * @param table     {@link SelenideElement}, указывающий на таблицу
     * @param title     название столбца
     */
    public FullTimeColumn(Integer index, SelenideElement table, String title) {
        super(index, table, title);
        calendar = Calendar.getInstance();
    }

    @Override
    public String getFilteredValue(String text) {
        Matcher matcher = compile("^([А-я]*) " +
                "((?:[0-9]|0[0-9]|1[0-9]|2[0-3]):(?:[0-5][0-9])) " + "((GMT([-+]\\d{1,2})))$").matcher(text);
        if (!matcher.find()) {
            throw new IllegalArgumentException(String.format("Неверный формат даты: %s", text));
        }

        long epochTime = EpochTime.day(matcher.group(1)).at(matcher.group(2)) * 1_000;
        calendar.setTime(new Date(epochTime));

        return format("contains(., '%d.%d.%d %s') and contains(., '%s')",
                calendar.get(DAY_OF_MONTH),
                calendar.get(MONTH) + 1,
                calendar.get(YEAR),
                matcher.group(2),
                matcher.group(3));
    }
}
