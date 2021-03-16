package ru.sberbank.smartoffice.at.entities.nested;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import lombok.Builder;
import lombok.Value;

@Value
@Builder
@JsonDeserialize(builder = Document.DocumentBuilder.class)
public class Document {

    String id;
    String title;
    String fileName;
    String categoryId;

    boolean isPrivate;

    @JsonPOJOBuilder(withPrefix = "")
    public static class DocumentBuilder {
    }
}
