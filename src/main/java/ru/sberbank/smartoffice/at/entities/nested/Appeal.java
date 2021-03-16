package ru.sberbank.smartoffice.at.entities.nested;

import lombok.Data;

@Data
public class Appeal {
    String officialAppeal = "";
    String privateAppeal = "";
    String officialAppealNative = "";
    String privateAppealNative = "";
    String officialAppealEng = "";
    String privateAppealEng = "";
}
