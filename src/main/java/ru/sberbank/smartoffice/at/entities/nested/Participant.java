package ru.sberbank.smartoffice.at.entities.nested;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import lombok.Builder;
import lombok.Value;

@Value
@Builder
@JsonDeserialize(builder = Participant.ParticipantBuilder.class)
public class Participant {

    String participantStatus;
    String companyPosition;
    String biographyId;
    String companyName;
    String middleName;
    String contactId;
    String firstName;
    String lastName;
    String userId;

    int order;

    @JsonPOJOBuilder(withPrefix = "")
    public static class ParticipantBuilder {
    }
}
