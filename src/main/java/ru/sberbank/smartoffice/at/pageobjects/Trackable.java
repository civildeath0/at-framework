package ru.sberbank.smartoffice.at.pageobjects;

import ru.sberbank.smartoffice.at.visitors.tracker.Tracker;

public interface Trackable {

    void accept(Tracker tracker);
}
