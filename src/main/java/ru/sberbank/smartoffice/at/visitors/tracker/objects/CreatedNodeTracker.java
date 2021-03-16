package ru.sberbank.smartoffice.at.visitors.tracker.objects;

import com.browserup.harreader.model.HarEntry;
import com.fasterxml.jackson.databind.JsonNode;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.jetbrains.annotations.NotNull;
import ru.sberbank.smartoffice.at.pageobjects.classes.detailed.*;
import ru.sberbank.smartoffice.at.visitors.tracker.abstraction.ATracker;

import static lombok.AccessLevel.PRIVATE;

@NoArgsConstructor
@FieldDefaults(level = PRIVATE, makeFinal = true)
public final class CreatedNodeTracker extends ATracker<JsonNode> {

    @NotNull @Override
    protected Class<? extends JsonNode> getClass(FlightPage page) {
        return JsonNode.class;
    }

    @NotNull @Override
    protected Class<? extends JsonNode> getClass(MeetingPage page) {
        return JsonNode.class;
    }

    @NotNull @Override
    protected Class<? extends JsonNode> getClass(AirportPage page) {
        return JsonNode.class;
    }

    @NotNull @Override
    protected Class<? extends JsonNode> getClass(CompanyPage page) {
        return JsonNode.class;
    }

    @NotNull @Override
    protected Class<? extends JsonNode> getClass(AssignmentPage page) { return JsonNode.class; }

    @NotNull @Override
    protected Class<? extends JsonNode> getClass(ContactPage page) { return JsonNode.class; }

    @NotNull @Override
    protected String getContent(HarEntry harEntry) {
        return harEntry.getResponse().getContent().getText();
    }
}
