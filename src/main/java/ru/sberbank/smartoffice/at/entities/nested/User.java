package ru.sberbank.smartoffice.at.entities.nested;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import lombok.Builder;
import lombok.Value;

@Value
@Builder
@JsonDeserialize(builder = User.UserBuilder.class)
public class User {
    String firstName;
    String lastName;
    String middleName;
    String position;
    String id;

    @JsonPOJOBuilder(withPrefix = "")
    public static class UserBuilder {
    }
}
