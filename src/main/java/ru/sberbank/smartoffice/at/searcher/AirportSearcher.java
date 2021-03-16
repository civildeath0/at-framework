package ru.sberbank.smartoffice.at.searcher;

import ru.sberbank.smartoffice.at.entities.objects.Airport;
import ru.sberbank.smartoffice.at.searcher.results.SearchResult;

import java.util.List;
import java.util.Map;

public interface AirportSearcher {

    SearchResult<Airport.Created> searchAirport(Map<String, String> queryParams);

    List<Airport.Created> searchAirport(String search);
}
