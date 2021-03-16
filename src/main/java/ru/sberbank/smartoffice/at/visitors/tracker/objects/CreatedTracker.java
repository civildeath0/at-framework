package ru.sberbank.smartoffice.at.visitors.tracker.objects;

import com.browserup.harreader.model.HarEntry;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.jetbrains.annotations.NotNull;
import ru.sberbank.smartoffice.at.entities.CreatedEntity;
import ru.sberbank.smartoffice.at.entities.objects.*;
import ru.sberbank.smartoffice.at.pageobjects.classes.detailed.*;
import ru.sberbank.smartoffice.at.visitors.tracker.abstraction.ATracker;

import static lombok.AccessLevel.PRIVATE;

@NoArgsConstructor
@FieldDefaults(level = PRIVATE, makeFinal = true)
public final class CreatedTracker extends ATracker<CreatedEntity> {

    @NotNull @Override
    protected Class<? extends CreatedEntity> getClass(FlightPage page) {
        return Flight.Created.class;
    }

    @NotNull @Override
    protected Class<? extends CreatedEntity> getClass(MeetingPage page) {
        return Meeting.Created.class;
    }

    @NotNull @Override
    protected Class<? extends CreatedEntity> getClass(AirportPage page) {
        return Airport.Created.class;
    }

    @NotNull @Override
    protected Class<? extends CreatedEntity> getClass(CompanyPage page) {
        return Company.Created.class;
    }

    @NotNull @Override
    protected Class<? extends CreatedEntity> getClass(AssignmentPage page) {
        return Assignment.Created.class;
    }

    @NotNull @Override
    protected Class<? extends CreatedEntity> getClass(ContactPage page) { return Contact.Created.class; }

    @NotNull @Override
    protected String getContent(HarEntry harEntry) {
        return harEntry.getResponse().getContent().getText();
    }

}
