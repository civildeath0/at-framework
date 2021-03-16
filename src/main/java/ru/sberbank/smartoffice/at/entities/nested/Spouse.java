package ru.sberbank.smartoffice.at.entities.nested;

import lombok.Data;

@Data
public class Spouse {
    String id = "";
    String firstName = "";
    String lastName = "";
    String middleName = "";
    String firstNameNative = "";
    String lastNameNative = "";
    String firstNameEng = "";
    String lastNameEng = "";
    String birthDay = "";
    String birthMonth = "";
    String birthYear = "";

    Appeal appeal;
}
