package ru.sberbank.smartoffice.at.visitors.changer.errors;

import static java.lang.String.format;

@SuppressWarnings({"unused", "SpellCheckingInspection"})
public class UnapdatableStatus extends UnsupportedOperationException {

    public UnapdatableStatus(Class<?> clazz) {
        super(format("Сущность \"%s\" не поддерживает обновление статуса", clazz.getName()));
    }
}
