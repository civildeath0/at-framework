package ru.sberbank.smartoffice.at.entities.nested;

import com.fasterxml.jackson.annotation.JsonProperty;

public enum EventStatus {
    @JsonProperty("Draft")
    DRAFT,
    @JsonProperty("Directed")
    DIRECTED,
    @JsonProperty("Canceled")
    CANCELED,
    @JsonProperty("Completed")
    COMPLETED
}
