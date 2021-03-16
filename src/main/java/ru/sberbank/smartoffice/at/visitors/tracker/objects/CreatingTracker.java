package ru.sberbank.smartoffice.at.visitors.tracker.objects;

import com.browserup.harreader.model.HarEntry;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.jetbrains.annotations.NotNull;
import ru.sberbank.smartoffice.at.entities.CreatingEntity;
import ru.sberbank.smartoffice.at.entities.objects.*;
import ru.sberbank.smartoffice.at.pageobjects.classes.detailed.*;
import ru.sberbank.smartoffice.at.visitors.tracker.abstraction.ATracker;

import static lombok.AccessLevel.PRIVATE;

@NoArgsConstructor
@FieldDefaults(level = PRIVATE, makeFinal = true)
public final class CreatingTracker extends ATracker<CreatingEntity> {

    @NotNull
    @Override
    protected Class<? extends CreatingEntity> getClass(FlightPage page) {
        return Flight.Creating.class;
    }

    @NotNull
    @Override
    protected Class<? extends CreatingEntity> getClass(MeetingPage page) {
        return Meeting.Creating.class;
    }

    @NotNull
    @Override
    protected Class<? extends CreatingEntity> getClass(AirportPage page) {
        return Airport.Creating.class;
    }

    @NotNull
    @Override
    protected Class<? extends CreatingEntity> getClass(CompanyPage page) {
        return Company.Creating.class;
    }

    @NotNull
    @Override
    protected Class<? extends CreatingEntity> getClass(AssignmentPage page) {
        return Assignment.Creating.class;
    }

    @NotNull
    @Override
    protected Class<? extends CreatingEntity> getClass(ContactPage page) {
        return Contact.Creating.class;
    }

    @NotNull
    @Override
    protected String getContent(HarEntry harEntry) {
        return harEntry.getRequest().getPostData().getText();
    }
}
