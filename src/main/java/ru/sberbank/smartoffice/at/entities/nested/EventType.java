package ru.sberbank.smartoffice.at.entities.nested;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NonNull;
import org.jetbrains.annotations.Contract;

import java.util.HashMap;
import java.util.Map;

import static java.lang.String.format;

public enum EventType {

    @JsonProperty("MeetingPrivate")
    PRIVATE("Личная"),
    @JsonProperty("MeetingExternal")
    EXTERNAL("Внешняя"),
    @JsonProperty("MeetingInternal")
    INTERNAL("Внутренняя");

    private static final Map<String, EventType> lookup;

    static {
        lookup = new HashMap<>();
        for (EventType eventType : EventType.values()) {
            lookup.put(eventType.getEventType(), eventType);
        }
    }

    @Getter
    private final String eventType;

    @Contract(pure = true)
    EventType(@NonNull String eventType) {
        this.eventType = eventType;
    }

    public static EventType getEventType(String eventTypeString) {
        EventType eventType;
        if ((eventType = lookup.get(eventTypeString)) == null) {
            throw new IllegalArgumentException(format("Несуществующий тип встречи: %s", eventTypeString));
        }
        return eventType;
    }
}
