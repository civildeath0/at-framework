package ru.sberbank.smartoffice.at.utilities;

import io.restassured.response.Response;
import ru.sberbank.smartoffice.at.entities.CreatedEntity;
import ru.sberbank.smartoffice.at.users.SmartUser;
import ru.sberbank.smartoffice.at.users.SmartUsers;
import ru.sberbank.smartoffice.at.utilities.errors.FailedAuthentication;
import ru.sberbank.smartoffice.at.utilities.errors.UserNotFound;

import java.util.function.Predicate;

import static io.restassured.http.ContentType.JSON;

public abstract class Authorizer {

    public static void loginUser(SmartUser user) {
        Response response = Specificator.
                getBaseSpecification()
                .accept(JSON)
                .contentType(JSON)
                .body(String.format("{\"username\": \"%s\", \"password\": \"%s\"}",
                        user.getLogin(), user.getPassword()))
                .post("api/web/auth");
        if (response.getStatusCode() != 200) {
            throw new FailedAuthentication(response);
        }
        user.setCookie(response.getDetailedCookie("smart-office-auth"));
    }

    public static SmartUser getAuthor(CreatedEntity createdEntity) {
        String authorId = createdEntity.getAuthor().getId();

        Predicate<SmartUser> byId = user -> user.getId().equals(authorId);
        return SmartUsers.userStream().filter(byId).findFirst().orElseThrow(() -> {
            throw new UserNotFound(authorId);
        });
    }
}
