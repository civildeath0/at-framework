package ru.sberbank.smartoffice.at.utilities.epochtime.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

import java.time.ZonedDateTime;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Map;

import static java.lang.String.format;
import static java.time.ZoneOffset.UTC;
import static java.time.ZonedDateTime.now;
import static java.time.temporal.ChronoUnit.*;
import static java.util.Calendar.DAY_OF_MONTH;
import static lombok.AccessLevel.PRIVATE;

/**
 * <h1>Перечисление слов-указателей времени</h1>
 * <p>
 * Доступные варианты в строковом представлении:
 * <ul>
 *     <li>Позавчера</li>
 *     <li>Вчера</li>
 *     <li>Сегодня</li>
 *     <li>Завтра</li>
 *     <li>Послезавтра</li>
 * </ul>
 */
@RequiredArgsConstructor(access = PRIVATE)
@FieldDefaults(level = PRIVATE, makeFinal = true)
public enum TimeIndicator {

    YEAR_AGO("год назад") {
        @Override
        public ZonedDateTime getLocalDateTime() {
            return now().minus(1, YEARS);
        }
    },
    MONTH_AGO("месяц назад") {
        @Override
        public ZonedDateTime getLocalDateTime() {
            return now().minus(1, MONTHS);
        }
    },
    MONTH_START("начало месяца") {
        @Override
        public ZonedDateTime getLocalDateTime() {
            ZonedDateTime zonedDateTime = now();
            return ZonedDateTime.of(zonedDateTime.getYear(), zonedDateTime.getMonthValue(),
                    1, 0, 0, 0, 0, UTC);
        }
    },
    WEEK_AGO("неделю назад") {
        @Override
        public ZonedDateTime getLocalDateTime() {
            return now().minus(1, WEEKS);
        }
    },
    BEFORE_YESTERDAY("позавчера") {
        @Override
        public ZonedDateTime getLocalDateTime() {
            return now().minus(2, DAYS);
        }
    },
    YESTERDAY("вчера") {
        @Override
        public ZonedDateTime getLocalDateTime() {
            return now().minus(1, DAYS);
        }
    },
    TODAY("сегодня") {
        @Override
        public ZonedDateTime getLocalDateTime() {
            return now();
        }
    },
    TOMORROW("завтра") {
        @Override
        public ZonedDateTime getLocalDateTime() {
            return now().plus(1, DAYS);
        }
    },
    AFTER_TOMORROW("послезавтра") {
        @Override
        public ZonedDateTime getLocalDateTime() {
            return now().plus(2, DAYS);
        }
    },
    WEEK_LATER("через неделю") {
        @Override
        public ZonedDateTime getLocalDateTime() {
            return now().plus(1, WEEKS);
        }
    },
    MONTH_END("конец месяца") {
        @Override
        public ZonedDateTime getLocalDateTime() {
            ZonedDateTime zonedDateTime = now();
            Calendar calendar = new GregorianCalendar();
            return ZonedDateTime.of(zonedDateTime.getYear(), zonedDateTime.getMonthValue(),
                    calendar.getActualMaximum(DAY_OF_MONTH), 0, 0, 0, 0, UTC);
        }
    },
    MONTH_LATER("через месяц") {
        @Override
        public ZonedDateTime getLocalDateTime() {
            return now().plus(1, MONTHS);
        }
    },
    YEAR_LATER("через год") {
        @Override
        public ZonedDateTime getLocalDateTime() {
            return now().plus(1, YEARS);
        }
    };

    static Map<String, TimeIndicator> lookup;

    static {
        lookup = new HashMap<>() {{
            for (TimeIndicator timeWord : TimeIndicator.values()) {
                put(timeWord.getTimeWord(), timeWord);
            }
        }};
    }

    @Getter(PRIVATE) String timeWord;

    /**
     * Конвертирует строковое представление
     * слова-указателя времени к {@link TimeIndicator}.
     *
     * @param timeWordString слово-указатель
     *                       времени в строковом виде
     *
     * @return слово-указатель времени
     * в виде {@link TimeIndicator}
     */
    public static TimeIndicator get(String timeWordString) {
        TimeIndicator timeWord;
        if ((timeWord = lookup.get(timeWordString.toLowerCase())) == null) {
            throw new IllegalArgumentException(format("Слово-указатель времени \"%s\" не существует!",
                    timeWordString));
        }
        return timeWord;
    }

    /**
     * Возвращает экземпляр {@link ZonedDateTime}, день которого
     * смещен на количество, соответствующее слову-указателю времени.
     * <p>
     * Подразумевается, что если сегодня 04.07.1998
     * то "вчера" соответствует 03.07.1998.
     *
     * @return экземпляр {@link ZonedDateTime} день которого
     * смещен на количество, соответствующее слову-указателю времени.
     */
    public abstract ZonedDateTime getLocalDateTime();
}
