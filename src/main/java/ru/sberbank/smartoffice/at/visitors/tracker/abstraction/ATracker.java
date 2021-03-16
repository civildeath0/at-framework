package ru.sberbank.smartoffice.at.visitors.tracker.abstraction;

import com.browserup.harreader.model.HarEntry;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import lombok.experimental.FieldDefaults;
import lombok.experimental.NonFinal;
import org.jetbrains.annotations.NotNull;
import ru.sberbank.smartoffice.at.archivist.Archivist;
import ru.sberbank.smartoffice.at.archivist.object.Angler;
import ru.sberbank.smartoffice.at.entities.CreatedEntity;
import ru.sberbank.smartoffice.at.entities.objects.Contact;
import ru.sberbank.smartoffice.at.pageobjects.Trackable;
import ru.sberbank.smartoffice.at.pageobjects.classes.detailed.*;
import ru.sberbank.smartoffice.at.utilities.Delayer;
import ru.sberbank.smartoffice.at.visitors.tracker.Tracker;
import ru.sberbank.smartoffice.at.visitors.tracker.errors.ConvertingException;

import javax.annotation.Nonnull;

import static com.browserup.harreader.model.HttpMethod.POST;
import static lombok.AccessLevel.PRIVATE;

@FieldDefaults(level = PRIVATE, makeFinal = true)
public abstract class ATracker<T> implements Tracker {

    Archivist searcher = new Angler();
    ObjectMapper objectMapper = new ObjectMapper();

    @NonFinal T entity;

    public T perform(Trackable trackable) {
        trackable.accept(this);
        return entity;
    }

    @Override
    public void track(CompanyPage companyPage) {
        entity = capture("references/companies", getClass(companyPage));
    }

    @Override
    public void track(FlightPage flightPage) {
        entity = capture("events/flights/", getClass(flightPage));
    }

    @Override
    public void track(MeetingPage meetingPage) {
        entity = capture("events/meetings/", getClass(meetingPage));
    }

    @Override
    public void track(AssignmentPage assignmentPage) {
        entity = capture("assignments/", getClass(assignmentPage));
    }

    @Override
    public void track(ContactPage contactPage) {
        entity = capture("contacts", getClass(contactPage));
    }

    @Override
    public void track(AirportPage airportPage) {
        entity = capture("events/flights/airports", getClass(airportPage));
    }

    private T capture(String path, Class<? extends T> aClass) {
        HarEntry har = Delayer.waitValue(() -> searcher.searchEntry(POST, path, 201));
        JsonNode node = getNode(har);
        try {
            return objectMapper.convertValue(node, aClass);
        } catch (IllegalArgumentException ex) {
            throw new ConvertingException(node.toPrettyString(), aClass, ex);
        }
    }

    @SneakyThrows
    private JsonNode getNode(HarEntry har) {
        return objectMapper.readTree(getContent(har));
    }

    @Nonnull
    protected abstract Class<? extends T> getClass(final FlightPage page);

    @Nonnull
    protected abstract Class<? extends T> getClass(final MeetingPage page);

    @Nonnull
    protected abstract Class<? extends T> getClass(final AirportPage page);

    @Nonnull
    protected abstract Class<? extends T> getClass(final CompanyPage page);

    @NotNull
    protected abstract Class<? extends T> getClass(AssignmentPage page);

    @NotNull
    protected abstract Class<? extends T> getClass(ContactPage page);

    @Nonnull
    protected abstract String getContent(final HarEntry harEntry);
}
