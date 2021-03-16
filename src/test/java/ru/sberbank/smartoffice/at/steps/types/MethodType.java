package ru.sberbank.smartoffice.at.steps.types;

import com.browserup.harreader.model.HttpMethod;
import io.cucumber.java.ParameterType;

public final class MethodType {

    @ParameterType("GET|POST|PUT|DELETE")
    public HttpMethod method(String method) {
        return HttpMethod.valueOf(method);
    }
}
