package ru.sberbank.smartoffice.at.visitors.tracker;

import ru.sberbank.smartoffice.at.pageobjects.classes.detailed.*;

public interface Tracker {

    void track(AirportPage airportPage);

    void track(CompanyPage companyPage);

    void track(FlightPage flightPage);

    void track(MeetingPage meetingPage);

    void track(AssignmentPage assignmentPage);

    void track(ContactPage contactPage);
}
