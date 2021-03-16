package ru.sberbank.smartoffice.at.entities.searcher;

import com.fasterxml.jackson.databind.node.ObjectNode;

public interface Searcher {

    ObjectNode searchParticipant(String searchCriteria);

    String searchTypeId(String documentTypeName);

    String searchCompanyId(String companyName);

    String searchCategoryId(String category);

    String searchAirportId(String airport);
}
