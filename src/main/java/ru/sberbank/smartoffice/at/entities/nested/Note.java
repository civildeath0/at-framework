package ru.sberbank.smartoffice.at.entities.nested;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import lombok.Builder;
import lombok.Value;

import java.util.List;

@Value
@Builder
@JsonDeserialize(builder = Note.NoteBuilder.class)
public class Note {

    String text;
    String id;

    List<String> audioDocumentIds;

    @JsonPOJOBuilder(withPrefix = "")
    public static class NoteBuilder {

    }
}
