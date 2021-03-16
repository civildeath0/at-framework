package ru.sberbank.smartoffice.at.validators;

import com.fasterxml.jackson.databind.JsonNode;
import com.github.fge.jsonschema.core.report.ProcessingReport;
import com.github.fge.jsonschema.main.JsonSchemaFactory;
import com.github.fge.jsonschema.main.JsonValidator;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.experimental.FieldDefaults;
import ru.sberbank.smartoffice.at.errors.InvalidSchemaMatching;
import ru.sberbank.smartoffice.at.pageobjects.Trackable;
import ru.sberbank.smartoffice.at.visitors.tracker.abstraction.ATracker;

import static lombok.AccessLevel.PRIVATE;
import static ru.sberbank.smartoffice.at.utilities.Stationery.attachText;

@RequiredArgsConstructor
@FieldDefaults(level = PRIVATE, makeFinal = true)
public final class Draftsman {

    ATracker<JsonNode> tracker;

    @SneakyThrows
    public void checkTrackablePage(Trackable trackable, JsonNode jsonSchema) {
        JsonValidator validator = JsonSchemaFactory.byDefault().getValidator();
        JsonNode entity = tracker.perform(trackable);
        ProcessingReport report = validator.validate(jsonSchema, entity, true);
        if (!report.isSuccess()) {
            attachText("Json", entity.toPrettyString());
            attachText("Report", report.toString());
            throw new InvalidSchemaMatching();
        }
    }
}
