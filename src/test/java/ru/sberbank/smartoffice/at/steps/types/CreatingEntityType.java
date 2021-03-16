package ru.sberbank.smartoffice.at.steps.types;

import io.cucumber.java.ParameterType;
import ru.sberbank.smartoffice.at.entities.factory.CreatingFactory;

public final class CreatingEntityType {

    @ParameterType("\"(.*)\"")
    public CreatingFactory entity(String entityName) {
        return CreatingFactory.find(entityName);
    }
}
