package ru.sberbank.smartoffice.at.entities;

import java.util.Map;

public interface Factory {

    CreatingEntity fillEntity(Map<String, String> filledFields);
}
