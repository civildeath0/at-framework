package ru.sberbank.smartoffice.at.searcher.object;

import com.browserup.harreader.model.HttpMethod;
import com.fasterxml.jackson.core.TreeNode;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import io.restassured.response.Response;
import ru.sberbank.smartoffice.at.entities.objects.Airport;
import ru.sberbank.smartoffice.at.searcher.AirportSearcher;
import ru.sberbank.smartoffice.at.searcher.errors.FailedRequest;
import ru.sberbank.smartoffice.at.searcher.results.SearchResult;
import ru.sberbank.smartoffice.at.users.SmartUser;
import ru.sberbank.smartoffice.at.utilities.QueryURLShaper;
import ru.sberbank.smartoffice.at.utilities.Specificator;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static io.restassured.http.ContentType.TEXT;

@SuppressWarnings("SameParameterValue")
public class EntitySearcher implements AirportSearcher {

    SmartUser author;
    ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public SearchResult<Airport.Created> searchAirport(Map<String, String> queryParams) {
        ObjectNode result = getResult("/api/web/events/flights/airports", queryParams);
        return SearchResult.<Airport.Created>builder()
                .limitFrom(result.get("limitFrom").asInt())
                .limitSize(result.get("limitSize").asInt())
                .items(getItemsList(result.get("items"), Airport.Created.class))
                .build();
    }

    @Override
    public List<Airport.Created> searchAirport(String search) {
        ObjectNode result = getResult("/api/web/events/flights/airports", new HashMap<>() {{
            put("Search", search);
        }});
        return getItemsList(result, Airport.Created.class);
    }

    private ObjectNode getResult(String path, Map<String, String> queryParams) {
        Response response = Specificator
                .getBaseSpecification()
                .accept(TEXT)
                .cookie(author.getCookie())
                .queryParams(queryParams)
                .get(path);
        if (response.getStatusCode() != 200) {
            throw new FailedRequest(HttpMethod.GET, QueryURLShaper.formQueryURL(path, queryParams), response);
        }
        return response.getBody().as(ObjectNode.class);
    }

    private <T> List<T> getItemsList(TreeNode node, Class<T> resultClass) {
        JavaType type = objectMapper.getTypeFactory()
                .constructCollectionType(List.class, resultClass);
        return objectMapper.convertValue(node, type);
    }
}
