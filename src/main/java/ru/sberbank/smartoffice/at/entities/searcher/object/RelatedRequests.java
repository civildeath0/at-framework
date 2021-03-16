package ru.sberbank.smartoffice.at.entities.searcher.object;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import io.qameta.allure.restassured.AllureRestAssured;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;
import lombok.experimental.NonFinal;
import ru.sberbank.smartoffice.at.entities.searcher.Searcher;
import ru.sberbank.smartoffice.at.users.SmartUser;
import ru.sberbank.smartoffice.at.utilities.Specificator;

import static io.restassured.http.ContentType.TEXT;
import static lombok.AccessLevel.PRIVATE;

@FieldDefaults(level = PRIVATE)
public class RelatedRequests implements Searcher {

    @Getter @Setter SmartUser author;
    @NonFinal int order = 0;

    @Override
    public ObjectNode searchParticipant(String searchCriteria) {
        ObjectNode objectNode = new ObjectMapper().createObjectNode();
        objectNode.put("order", order++);
        String id = Specificator
                .getBaseSpecification()
                .accept(TEXT)
                .queryParam("search", searchCriteria)
                .cookie(author.getCookie())
                .get("/api/web/biographies")
                .then()
                .assertThat()
                .statusCode(200)
                .extract()
                .response()
                .getBody()
                .as(ArrayNode.class)
                .get(0)
                .get("id")
                .asText();
        return objectNode.put("biographyId", id);
    }

    @Override
    public String searchTypeId(String documentTypeName) {
        return baseSearch(documentTypeName, "news/types");
    }

    @Override
    public String searchCompanyId(String companyName) {
        return baseSearch(companyName, "references/companies");
    }

    @Override
    public String searchCategoryId(String category) {
        return baseSearch(category, "documents/categories");
    }

    @Override
    public String searchAirportId(String airport) {
        return baseSearch(airport, "events/flights/airports");
    }

    private String baseSearch(String search, String path) {
        return Specificator
                .getBaseSpecification()
                .accept(TEXT)
                .queryParam("Search", search)
                .queryParam("SortBy", "Name")
                .queryParam("SortDesc", true)
                .queryParam("LimitFrom", "1")
                .queryParam("LimitSize", "1")
                .cookie(author.getCookie())
                .basePath("/api/web/")
                .get(path)
                .then()
                .assertThat()
                .statusCode(200)
                .extract()
                .response()
                .getBody()
                .as(ObjectNode.class)
                .get("items")
                .get(0)
                .get("id")
                .asText();
    }
}
