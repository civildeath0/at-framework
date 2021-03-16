package ru.sberbank.smartoffice.at.entities.errors;

import static java.lang.String.format;

public class InvalidHarContent extends Error {

    public InvalidHarContent(String harContent, Throwable throwable) {
        super(format("Не удалось привести \"%s\" к JsonNode", harContent), throwable);
    }
}
