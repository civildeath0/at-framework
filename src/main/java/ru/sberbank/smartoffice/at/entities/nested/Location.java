package ru.sberbank.smartoffice.at.entities.nested;

import lombok.Data;

@Data
public class Location {

    String locationComment = "";
    String address = "";

    long longitude = 0;
    long latitude = 0;
}
