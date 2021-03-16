package ru.sberbank.smartoffice.at.entities.converter.object;

import ru.sberbank.smartoffice.at.entities.converter.Converter;
import ru.sberbank.smartoffice.at.utilities.epochtime.EpochTime;

import java.util.regex.Matcher;

import static java.util.regex.Pattern.compile;

public class GherkinConverter implements Converter {

    @Override
    public long convertDateTime(String dateTime) {
        Matcher matcher = compile("^([А-я]+) (\\d{1,2}:\\d{2})$")
                .matcher(dateTime);
        if (matcher.find()) {
            return EpochTime.day(matcher.group(1))
                    .at(matcher.group(2));
        }
        throw new IllegalStateException("Неверный формат даты и времени");
    }

    @Override
    public boolean convertBoolean(String boolText) {
        switch (boolText.toUpperCase()) {
            case "ДА":
                return true;
            case "НЕТ":
                return false;
            default:
                throw new IllegalArgumentException("");
        }
    }

    @Override
    public int convertGmt(String gmt) {
        Matcher matcher = compile("^GMT([+-]\\d{1,2})$")
                .matcher(gmt);
        if (matcher.find()) {
            return Integer.parseInt(matcher.group(1)) * 3_600;
        }
        throw new IllegalStateException("Неверный формат часового пояса");
    }

    @Override
    public String convertPriority(String priority) {
        switch (priority.toLowerCase()) {
            case "низкий":
                return "Low";
            case "средний":
                return "Medium";
            case "высокий":
                return "High";
            case "критический":
                return "Critical";
            default:
                throw new IllegalArgumentException("");
        }
    }

    @Override
    public String convertStatus(String status) {
        switch (status.toLowerCase()) {
            case "в работе":
                return "Working";
            case "черновик":
                return "Draft";
            case "отправлено по почте":
                return "EmailSent";
            case "завершено":
                return "Completed";
            case "отменено":
                return "Cancelled";
            case "направлено участникам":
                return "Directed";
            default:
                throw new IllegalArgumentException("");
        }
    }
}
