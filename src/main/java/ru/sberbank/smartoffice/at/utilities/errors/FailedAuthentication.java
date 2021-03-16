package ru.sberbank.smartoffice.at.utilities.errors;

import io.restassured.response.Response;

import static java.lang.String.format;

public class FailedAuthentication extends Error {

    public FailedAuthentication(Response response) {
        super(format("Аутентификация завершилась неудачно со статусом %d",
                response.getStatusCode()));
    }
}
