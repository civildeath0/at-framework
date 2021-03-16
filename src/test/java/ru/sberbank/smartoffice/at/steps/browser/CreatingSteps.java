package ru.sberbank.smartoffice.at.steps.browser;

import com.fasterxml.jackson.databind.JsonNode;
import io.cucumber.java.ru.И;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import ru.sberbank.smartoffice.at.pageobjects.Trackable;
import ru.sberbank.smartoffice.at.pageobjects.core.page.Page;
import ru.sberbank.smartoffice.at.validators.Draftsman;
import ru.sberbank.smartoffice.at.visitors.tracker.objects.CreatedNodeTracker;
import ru.sberbank.smartoffice.at.visitors.tracker.objects.CreatedTracker;
import ru.sberbank.smartoffice.at.visitors.tracker.objects.CreatingNodeTracker;
import ru.sberbank.smartoffice.at.world.World;

import static lombok.AccessLevel.PRIVATE;

@Slf4j @RequiredArgsConstructor
@FieldDefaults(level = PRIVATE, makeFinal = true)
public final class CreatingSteps {

    World world;

    @И("сущность успешно создалась")
    public void entityCreatedSuccessfully() {
        world.addCreatedEntity(new CreatedTracker().perform(getPage()));
    }

    @И("запрос на создание сущности соответствует json схеме")
    @И("запрос на создание сущности соответствует json схеме {jsonSchemaFile}")
    public void checkRequestForSchemaCorresponding(JsonNode jsonSchema) {
        new Draftsman(new CreatingNodeTracker()).checkTrackablePage(getPage(), jsonSchema);
    }

    @И("ответ после создания сущности соответствует json схеме")
    @И("ответ после создания сущности соответствует json схеме {jsonSchemaFile}")
    public void checkResponseForSchemaCorresponding(JsonNode jsonSchema) {
        new Draftsman(new CreatedNodeTracker()).checkTrackablePage(getPage(), jsonSchema);
    }

    private Trackable getPage() {
        Page currentPage = world.getCurrentPage();
        if (!(currentPage instanceof Trackable)) {
            throw new Error("Текущая страница не поддерживает интерфейс Trackable");
        }
        return (Trackable) currentPage;
    }
}
