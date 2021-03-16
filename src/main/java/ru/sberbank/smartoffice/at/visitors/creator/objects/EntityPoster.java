package ru.sberbank.smartoffice.at.visitors.creator.objects;

import io.qameta.allure.restassured.AllureRestAssured;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.experimental.NonFinal;
import ru.sberbank.smartoffice.at.entities.CreatedEntity;
import ru.sberbank.smartoffice.at.entities.CreatingEntity;
import ru.sberbank.smartoffice.at.entities.objects.*;
import ru.sberbank.smartoffice.at.users.SmartUser;
import ru.sberbank.smartoffice.at.utilities.Specificator;
import ru.sberbank.smartoffice.at.visitors.creator.Creator;

import java.io.File;

import static io.restassured.http.ContentType.JSON;
import static io.restassured.http.ContentType.TEXT;
import static java.lang.String.format;
import static lombok.AccessLevel.PRIVATE;

@RequiredArgsConstructor(access = PRIVATE)
@FieldDefaults(level = PRIVATE, makeFinal = true)
public final class EntityPoster implements Creator {

    SmartUser author;

    @NonFinal
    CreatedEntity entity;

    public static CreatedEntity postEntity(SmartUser author, CreatingEntity entity) {
        return new EntityPoster(author).manage(entity);
    }

    public static void createDocument(SmartUser author, String uuid, String filePath) {
        File file = new File(format("src/test/resources/files/valid/%s", filePath));

        Specificator
                .getBaseSpecification()
                .filter(new AllureRestAssured())
                .cookie(author.getCookie())
                .accept(TEXT)
                .multiPart(file)
                .post("/api/web/documents/{documentId}/upload", uuid)
                .then()
                .assertThat()
                .statusCode(200);
    }

    public CreatedEntity manage(CreatingEntity entity) {
        entity.accept(this);
        return this.entity;
    }

    @Override
    public void manage(Airport.Creating airport) {
        entity = createEntity(airport,
                "/api/web/events/flights/airports", Airport.Created.class);
    }

    @Override
    public void manage(Assignment.Creating assignment) {
        entity = createEntity(assignment,
                "api/web/assignments", Assignment.Created.class);
    }

    @Override
    public void manage(Company.Creating company) {
        entity = createEntity(company,
                "/api/web/references/companies", Company.Created.class);
    }

    @Override
    public void manage(Conference.Creating conference) {
        entity = createEntity(conference,
                "api/web/events/conferences", Conference.Created.class);
    }

    @Override
    public void manage(Flight.Creating flight) {
        entity = createEntity(flight,
                "/api/web/events/flights", Flight.Created.class);
    }

    @Override
    public void manage(MaterialCategory.Creating materialCategory) {
        entity = createEntity(materialCategory,
                "api/web/documents/categories", MaterialCategory.Created.class);
    }

    @Override
    public void manage(Meeting.Creating meeting) {
        entity = createEntity(meeting,
                "api/web/events/meetings", Meeting.Created.class);
    }

    @Override
    public void manage(News.Creating news) {
        entity = createEntity(news, "api/web/news", News.Created.class);
    }

    @Override
    public void manage(Contact.Creating contact) {
        entity = createEntity(contact, "api/web/contacts", Contact.Created.class);
    }

    @Override
    public void manage(Material.Creating material) {
        entity = createEntity(material, "api/web/documents", Material.Created.class);
    }

    private CreatedEntity createEntity(Object entity, String path, Class<? extends CreatedEntity> classCast) {
        return Specificator
                .getBaseSpecification()
                .filter(new AllureRestAssured())
                .cookie(author.getCookie())
                .accept(TEXT)
                .contentType(JSON)
                .body(entity)
                .post(path)
                .then()
                .assertThat()
                .statusCode(201)
                .extract()
                .response()
                .getBody()
                .as(classCast);
    }
}
