package ru.sberbank.smartoffice.at.visitors.tracker.errors;

public class ConvertingException extends IllegalArgumentException {

    public ConvertingException(String json, Class<?> clazz, Throwable throwable) {
        super(String.format("Не удалось конвертировать json к классу \"%s\"\n%s",
                clazz.getName(), json), throwable);
    }
}
