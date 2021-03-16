package ru.sberbank.smartoffice.at.searcher.errors;

import com.browserup.harreader.model.HttpMethod;
import io.restassured.response.Response;

import static java.lang.String.format;

public class FailedRequest extends IllegalAccessError {

    public FailedRequest(HttpMethod method, String path, Response response) {
        super(format("\"%s\" запрос на адрес \"%s\" завершился со статусом %d",
                method.toString(), path, response.getStatusCode()));
    }
}
