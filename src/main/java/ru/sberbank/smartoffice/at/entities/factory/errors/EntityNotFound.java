package ru.sberbank.smartoffice.at.entities.factory.errors;

import static java.lang.String.format;

public class EntityNotFound extends NullPointerException {

    public EntityNotFound(String entityName) {
        super(format("Сущность \"%s\" не найдена среди известных", entityName));
    }
}
