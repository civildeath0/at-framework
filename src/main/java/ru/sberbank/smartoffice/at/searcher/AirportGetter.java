package ru.sberbank.smartoffice.at.searcher;

import ru.sberbank.smartoffice.at.entities.objects.Airport;

public interface AirportGetter {

    Airport.Created getAirport(String id);
}
