package ru.sberbank.smartoffice.at.visitors.changer.objects;

import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.specification.RequestSpecification;
import lombok.experimental.FieldDefaults;
import ru.sberbank.smartoffice.at.entities.CreatedEntity;
import ru.sberbank.smartoffice.at.entities.objects.*;
import ru.sberbank.smartoffice.at.users.SmartUser;
import ru.sberbank.smartoffice.at.utilities.Authorizer;
import ru.sberbank.smartoffice.at.utilities.Specificator;
import ru.sberbank.smartoffice.at.visitors.changer.Changer;

import static io.restassured.http.ContentType.JSON;
import static lombok.AccessLevel.PRIVATE;

@FieldDefaults(level = PRIVATE, makeFinal = true)
public final class EntityDeleter implements Changer {

    public void manage(CreatedEntity entity) {
        entity.accept(this);
    }

    @Override
    public void manage(Airport.Created airport) {
        deleteEntity(airport, "/events/flights/airports/{id}");
    }

    @Override
    public void manage(Assignment.Created assignment) {
        deleteEntity(assignment, "/assignments/{newsId}");
    }

    @Override
    public void manage(Company.Created company) {
        deleteEntity(company, "/references/companies/{id}");
    }

    @Override
    public void manage(Conference.Created conference) {
        deleteEntity(conference, "/events/conferences/{id}");
    }

    @Override
    public void manage(Flight.Created flight) {
        deleteEntity(flight, "/events/flights/{id}");
    }

    @Override
    public void manage(MaterialCategory.Created materialCategory) {
        deleteEntity(materialCategory, "/documents/categories/{id}");
    }

    @Override
    public void manage(Meeting.Created meeting) {
        deleteEntity(meeting, "/events/meetings/{id}");
    }

    @Override
    public void manage(News.Created news) {
        deleteEntity(news, "/news/{newsId}");
    }

    @Override
    public void manage(Contact.Created contact) {
        deleteEntity(contact, "/contacts/{id}");
    }

    @Override
    public void manage(Material.Created material) {
        deleteEntity(material, "/documents/{id}");
    }

    private void deleteEntity(CreatedEntity createdEntity, String path) {
        specification(createdEntity).delete(path, createdEntity.getId());
    }

    private RequestSpecification specification(CreatedEntity createdEntity) {
        SmartUser author = Authorizer.getAuthor(createdEntity);
        Authorizer.loginUser(author);
        return Specificator
                .getBaseSpecification()
                .filter(new AllureRestAssured())
                .basePath("/api/web")
                .accept(JSON)
                .cookie(author.getCookie());
    }

}
