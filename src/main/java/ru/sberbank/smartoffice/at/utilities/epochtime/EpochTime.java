package ru.sberbank.smartoffice.at.utilities.epochtime;


import ru.sberbank.smartoffice.at.utilities.epochtime.enums.TimeIndicator;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.util.regex.Matcher;

import static java.lang.Integer.parseInt;
import static java.lang.String.format;
import static java.time.temporal.ChronoField.HOUR_OF_DAY;
import static java.time.temporal.ChronoField.MINUTE_OF_HOUR;
import static java.util.regex.Pattern.compile;

/**
 * <h1><a href=https://ru.wikipedia.org/wiki/Unix-%D0%B2%D1%80%D0%B5%D0%BC%D1%8F>UNIX-время</a> утилита</h1>
 * <p>
 * Примеры вызова:
 * <blockquote><pre>
 *     long epochTime = EpochTime.day("сегодня").at("21:00");
 * </pre></blockquote>
 * Данный вызов вернет
 * <a href=https://ru.wikipedia.org/wiki/Unix-%D0%B2%D1%80%D0%B5%D0%BC%D1%8F>UNIX-время</a>
 * соответствующее сегодняшнему дню в 21:00.
 * <p>
 * Доступные варианты слов-указателей
 * времени перечислены в {@link TimeIndicator}.
 */
public final class EpochTime {

    private ZonedDateTime dateTime;

    /**
     * Конструктор класса {@link EpochTime}.
     *
     * @param timeWord слово-указатель времени
     *                 вида {@link TimeIndicator}
     */
    private EpochTime(TimeIndicator timeWord) {
        dateTime = timeWord.getLocalDateTime();
    }

    /**
     * Ковернтирует слово-указатель времени вида {@link TimeIndicator}
     * к текущей дате и возвращает экземпляр класса {@link EpochTime}.
     *
     * @param timeWord слово-указатель времени вида {@link TimeIndicator}
     *
     * @return экземпляр класса {@link EpochTime} с конвертированным
     * словом-указателем времени к дате.
     */
    public static EpochTime day(TimeIndicator timeWord) {
        return new EpochTime(timeWord);
    }

    /**
     * Конвертирует слово-указатель времени ("вчера, сегодня и т.д.")
     * к текущей дате и возвращает экземпляр класса {@link EpochTime}.
     * <p>
     * <h3>>>>>> Регистр не имеет значения <<<<<</h3>
     * <p>
     * Доступные варианты слова-указателя времени
     * перечислены в {@link TimeIndicator}.
     *
     * @param timeWord слово-указатель времени
     *
     * @return экземпляр класса {@link EpochTime} с конвертированным
     * словом-указателем времени к дате.
     */
    public static EpochTime day(String timeWord) {
        return new EpochTime(TimeIndicator.get(timeWord));
    }

    /**
     * Возвращает
     * <a href=https://ru.wikipedia.org/wiki/Unix-%D0%B2%D1%80%D0%B5%D0%BC%D1%8F>UNIX-время</a>
     * соответствующее дню, переданному в метод {@link #day(String)}, и времени
     * переданному в текущий метод в формате HH:mm.
     *
     * @param time время в формате HH:mm
     *
     * @return <a href=https://ru.wikipedia.org/wiki/Unix-%D0%B2%D1%80%D0%B5%D0%BC%D1%8F>UNIX-время</a>
     */
    public long at(String time) {
        Matcher matcher = compile("^([0-9]|0[0-9]|1[0-9]|2[0-3]):([0-5][0-9])$").matcher(time);
        if (!matcher.find()) {
            throw new IllegalArgumentException(format("Время \"%s\" не существует", time));
        }
        dateTime = dateTime.with(HOUR_OF_DAY, parseInt(matcher.group(1)));
        dateTime = dateTime.with(MINUTE_OF_HOUR, parseInt(matcher.group(2)));
        return dateTime.toEpochSecond();
    }

    public long atStart() {
        return dateTime.toLocalDate().atStartOfDay().toEpochSecond(ZoneOffset.UTC);
    }

    public long atEnd() {
        return LocalDateTime.of(dateTime.toLocalDate(), LocalTime.MAX).toEpochSecond(ZoneOffset.UTC);
    }
}
