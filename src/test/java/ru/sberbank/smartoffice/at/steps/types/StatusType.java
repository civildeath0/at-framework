package ru.sberbank.smartoffice.at.steps.types;

import io.cucumber.java.ParameterType;

import static java.lang.String.format;

public final class StatusType {

    @ParameterType("\"(.*)\"")
    public String status(String status) {
        switch (status.toLowerCase()) {
            case "отправлено по почте":
                return "EmailSent";
            case "отменено":
                return "Cancelled";
            case "завершено":
                return "Completed";
            case "опубликовано":
                return "Directed";
            case "черновик":
                return "Draft";
            default:
                throw new IllegalArgumentException(format("Некорректный выбранный статус: %s", status));
        }
    }
}
