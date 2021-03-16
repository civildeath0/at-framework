package ru.sberbank.smartoffice.at.pageelements.columns;

import com.codeborne.selenide.SelenideElement;
import lombok.experimental.FieldDefaults;
import ru.sberbank.smartoffice.at.pageobjects.core.table.Column;
import ru.sberbank.smartoffice.at.utilities.epochtime.EpochTime;

import java.text.DateFormatSymbols;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.regex.Matcher;

import static java.lang.String.format;
import static java.util.Calendar.DAY_OF_MONTH;
import static java.util.Calendar.MONTH;
import static java.util.regex.Pattern.compile;
import static lombok.AccessLevel.PRIVATE;

/**
 * <h1>Репрезентация столбца с датой</h1>
 */
@FieldDefaults(level = PRIVATE, makeFinal = true)
public class DateColumn extends Column {

    Calendar calendar;

    /**
     * Конструктор класса {@link DateColumn}.
     *
     * @param index     номер столбца
     * @param table     {@link SelenideElement}, указывающий на таблицу
     * @param title     название столбца
     */
    public DateColumn(Integer index, SelenideElement table, String title) {
        super(index, table, title);
        calendar = Calendar.getInstance();
    }

    @Override
    public String getFilteredValue(String text) {
        Matcher matcher = compile("^((?:поза)?вчера|сегодня|(?:после)?завтра)$").matcher(text);
        if (!matcher.find()) {
            throw new IllegalArgumentException(String.format("Неверный формат даты: %s", text));
        }

        long epochTime = EpochTime.day(matcher.group(1)).at("00:00") * 1_000;
        calendar.setTime(new Date(epochTime));

        return format("'%d %s'",
                calendar.get(DAY_OF_MONTH),
                new DateFormatSymbols(new Locale("ru")).getMonths()[calendar.get(MONTH)]);
    }
}