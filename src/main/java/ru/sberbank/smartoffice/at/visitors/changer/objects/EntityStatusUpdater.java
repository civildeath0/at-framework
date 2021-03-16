package ru.sberbank.smartoffice.at.visitors.changer.objects;

import io.qameta.allure.restassured.AllureRestAssured;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import ru.sberbank.smartoffice.at.entities.CreatedEntity;
import ru.sberbank.smartoffice.at.entities.objects.*;
import ru.sberbank.smartoffice.at.users.SmartUser;
import ru.sberbank.smartoffice.at.utilities.Authorizer;
import ru.sberbank.smartoffice.at.utilities.Specificator;
import ru.sberbank.smartoffice.at.visitors.changer.Changer;
import ru.sberbank.smartoffice.at.visitors.changer.errors.UnapdatableStatus;

import static io.restassured.http.ContentType.JSON;
import static io.restassured.http.ContentType.TEXT;
import static java.lang.String.format;
import static lombok.AccessLevel.PRIVATE;

@RequiredArgsConstructor(access = PRIVATE)
@FieldDefaults(level = PRIVATE, makeFinal = true)
public final class EntityStatusUpdater implements Changer {

    String status;

    public static void updateEntityStatus(CreatedEntity entity, String status) {
        entity.accept(new EntityStatusUpdater(status));
    }

    @Override
    public void manage(Airport.Created airport) {
        throw new UnapdatableStatus(airport.getClass());
    }

    @Override
    public void manage(Assignment.Created assignment) {
        updateStatus(assignment, "/assignments/status/{assignmentId}", "assignmentStatus");
    }

    @Override
    public void manage(Company.Created company) {
        throw new UnapdatableStatus(company.getClass());
    }

    @Override
    public void manage(Conference.Created conference) {
        updateStatus(conference, "/events/conferences/{id}/status", "status");
    }

    @Override
    public void manage(Flight.Created flight) {
        updateStatus(flight, "/api/web/events/flights/{id}/status", "status");
    }

    @Override
    public void manage(MaterialCategory.Created materialCategory) {
        throw new UnapdatableStatus(materialCategory.getClass());
    }

    @Override
    public void manage(Meeting.Created meeting) {
        updateStatus(meeting, "/api/web/events/meetings/{id}/status", "status");
    }

    @Override
    public void manage(News.Created news) {
        updateStatus(news, "/api/web/news/status/{id}", "status");
    }

    @Override
    public void manage(Contact.Created contact) {
        updateStatus(contact, "/api/web/contacts/status/{id}", "status");
    }

    @Override
    public void manage(Material.Created material) {
        updateStatus(material, "/api/web/document/{id}", "status");
    }

    private void updateStatus(CreatedEntity createdEntity, String path, String key) {
        SmartUser author = Authorizer.getAuthor(createdEntity);
        Authorizer.loginUser(author);
        Specificator
                .getBaseSpecification()
                .filter(new AllureRestAssured())
                .basePath("/api/web")
                .accept(TEXT)
                .contentType(JSON)
                .cookie(author.getCookie())
                .body(format("{\"%s\": \"%s\"}", key, status))
                .put(path, createdEntity.getId())
                .then()
                .assertThat()
                .statusCode(200);
    }
}
