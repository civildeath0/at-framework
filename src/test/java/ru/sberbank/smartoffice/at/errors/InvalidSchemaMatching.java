package ru.sberbank.smartoffice.at.errors;

public class InvalidSchemaMatching extends AssertionError {

    public InvalidSchemaMatching() {
        super("Объект не соответствует json схеме");
    }
}
