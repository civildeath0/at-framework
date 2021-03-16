package ru.sberbank.smartoffice.at.steps.types;

import io.cucumber.java.DataTableType;
import org.apache.hc.core5.http.NameValuePair;
import org.apache.hc.core5.http.message.BasicNameValuePair;
import ru.sberbank.smartoffice.at.converters.QueryConverter;
import ru.sberbank.smartoffice.at.converters.factories.QueryConverterFactory;

import java.util.Map;

public final class QueryType {

    @DataTableType
    public NameValuePair pair(Map<String, String> param) {
        return getPairedValue(param.get("[Query параметр]"),
                param.get("[Значение]"));
    }

    private NameValuePair getPairedValue(String queryParam, String value) {
        QueryConverter factory;
        try {
            factory = QueryConverterFactory.valueOf(queryParam);
        } catch (IllegalArgumentException ex) {
            return new BasicNameValuePair(queryParam, value == null ? "" : value);
        }
        return new BasicNameValuePair(queryParam, factory.convertValue(value));
    }
}
