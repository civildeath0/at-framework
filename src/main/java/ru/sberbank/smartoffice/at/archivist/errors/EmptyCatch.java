package ru.sberbank.smartoffice.at.archivist.errors;

import com.browserup.harreader.model.HttpMethod;

import static java.lang.String.format;

public class EmptyCatch extends AssertionError {

    public EmptyCatch(HttpMethod method, String path) {
        super(format("Не удалось найти \"%s\" запрос, отправленный на адрес \"%s\"",
                method.toString(), path));
    }

    public EmptyCatch(HttpMethod method, String path, int status) {
        super(format("Не удалось найти \"%s\" запрос, отправленный на адрес \"%s\", со статусом %d",
                method.toString(), path, status));
    }
}
