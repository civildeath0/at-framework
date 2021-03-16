package ru.sberbank.smartoffice.at.steps.api;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.ru.И;
import lombok.experimental.FieldDefaults;
import ru.sberbank.smartoffice.at.entities.CreatedEntity;
import ru.sberbank.smartoffice.at.entities.Factory;
import ru.sberbank.smartoffice.at.entities.converter.Converter;
import ru.sberbank.smartoffice.at.entities.converter.object.GherkinConverter;
import ru.sberbank.smartoffice.at.entities.objects.MaterialCategory;
import ru.sberbank.smartoffice.at.entities.objects.Meeting;
import ru.sberbank.smartoffice.at.entities.objects.News;
import ru.sberbank.smartoffice.at.entities.searcher.object.RelatedRequests;
import ru.sberbank.smartoffice.at.users.SmartUser;
import ru.sberbank.smartoffice.at.utilities.Authorizer;
import ru.sberbank.smartoffice.at.visitors.creator.objects.EntityPoster;
import ru.sberbank.smartoffice.at.visitors.changer.objects.EntityStatusUpdater;
import ru.sberbank.smartoffice.at.world.World;

import java.util.Map;
import java.util.UUID;

import static lombok.AccessLevel.PRIVATE;
import static ru.sberbank.smartoffice.at.utilities.Columnist.fromTable;

@FieldDefaults(level = PRIVATE, makeFinal = true)
public final class Creating {

    World world;
    RelatedRequests searcher = new RelatedRequests();

    public Creating(World world) {
        setEntitiesConverters();
        setEntitiesSearcher();
        this.world = world;
    }

    @И("помощник {assistant} создал сущность {entity}")
    public void createEntity(SmartUser author, Factory factory, DataTable dataTable) {
        searcher.setAuthor(author);
        Authorizer.loginUser(author);
        Map<String, String> filledFields = fromTable(dataTable)
                .mapColumns("[Название поля]", "[Значение]");
        world.addCreatedEntity(EntityPoster
                .postEntity(author, factory.fillEntity(filledFields)));
    }

    @И("обновил на статус {status}")
    public void updateEntity(String status) {
        EntityStatusUpdater.updateEntityStatus(world.getLastCreatedEntity(), status);
    }

    @И("помощник {assistant} создал документ {string} с сущностью {entity}")
    public void createDocument(SmartUser author, String fileName, Factory factory, DataTable dataTable) {
        String randomUUID = UUID.randomUUID().toString();
        searcher.setAuthor(author);
        Authorizer.loginUser(author);

        Map<String, String> filledFields = fromTable(dataTable)
                .mapColumns("[Название поля]", "[Значение]");
        filledFields.put("id", randomUUID);
        world.addCreatedEntity(EntityPoster
                .postEntity(author, factory.fillEntity(filledFields)));
        EntityPoster.createDocument(author, randomUUID, fileName);
    }

    private void setEntitiesConverters() {
        Converter converter = new GherkinConverter();

        MaterialCategory.setConverter(converter);
        Meeting.setConverter(converter);
        News.setConverter(converter);
    }

    private void setEntitiesSearcher() {
        Meeting.setSearcher(searcher);
        News.setSearcher(searcher);
    }
}
