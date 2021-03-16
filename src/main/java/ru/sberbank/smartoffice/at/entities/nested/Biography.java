package ru.sberbank.smartoffice.at.entities.nested;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.node.ObjectNode;
import lombok.Builder;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class Biography {

    String id = "";
    Spouse spouse = new Spouse();
    String firstName = "";
    String lastName = "";
    String middleName = "";
    String shortName = "";
    String firstNameNative = "";
    String lastNameNative = "";
    String firstNameEng = "";
    String lastNameEng = "";
    long birthDay = 0;
    long birthMonth = 0;
    long birthYear = 0;
    String birthPlace = "";
    String photoDocumentId = "";
    String maritalStatus = "";
    String relationToStructure = "";
    String relationToStructureForeign = "";
    String previousEmployment = "";
    String additionalInfo = "";
    String gender = "";
    String carModel = "";
    String carNumber = "";
    String workplaceAddress = "";
    String workplaceAddressComment = "";
    long createdAt = 0;
    long updatedAt = 0;

    boolean hasChildren = false;
    boolean isForeign = false;
    @JsonProperty(value = "isForeign")
    public boolean isForeign() {
        return isForeign;
    }
    boolean showRussianName = false;

    Appeal appeal;

    List<ObjectNode> workplaces = new ArrayList<>();
    List<ObjectNode> educations = new ArrayList<>();
}
