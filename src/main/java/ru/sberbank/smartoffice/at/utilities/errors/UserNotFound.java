package ru.sberbank.smartoffice.at.utilities.errors;

import java.util.NoSuchElementException;

import static java.lang.String.format;

public class UserNotFound extends NoSuchElementException {

    public UserNotFound(String authorId) {
        super(format("Автор с id \"%s\" не найден среди известных пользователей", authorId));
    }
}
