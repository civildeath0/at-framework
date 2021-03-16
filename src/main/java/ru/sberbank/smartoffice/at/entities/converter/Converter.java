package ru.sberbank.smartoffice.at.entities.converter;

public interface Converter {

    long convertDateTime(String dateTime);

    boolean convertBoolean(String boolText);

    int convertGmt(String gmt);

    String convertPriority(String priority);

    String convertStatus(String status);
}
