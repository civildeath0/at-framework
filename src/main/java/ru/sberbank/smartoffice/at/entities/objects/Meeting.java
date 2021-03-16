package ru.sberbank.smartoffice.at.entities.objects;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import com.fasterxml.jackson.databind.node.ObjectNode;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Value;
import ru.sberbank.smartoffice.at.entities.CreatedEntity;
import ru.sberbank.smartoffice.at.entities.CreatingEntity;
import ru.sberbank.smartoffice.at.entities.converter.Converter;
import ru.sberbank.smartoffice.at.entities.engine.Engine;
import ru.sberbank.smartoffice.at.entities.nested.*;
import ru.sberbank.smartoffice.at.entities.searcher.Searcher;
import ru.sberbank.smartoffice.at.visitors.creator.Creator;
import ru.sberbank.smartoffice.at.visitors.changer.Changer;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import static java.lang.Integer.parseInt;
import static lombok.AccessLevel.PRIVATE;

@AllArgsConstructor(access = PRIVATE)
public final class Meeting extends Engine {

    @Value @Builder @AllArgsConstructor(access = PRIVATE)
    @JsonDeserialize(builder = Creating.CreatingBuilder.class)
    public static class Creating implements CreatingEntity {

        @Builder.Default
        List<ObjectNode> users = new ArrayList<>();
        List<ObjectNode> participants;
        @Builder.Default
        List<ObjectNode> documents = new ArrayList<>();

        Location location;

        String conferenceId;
        String photoDocumentId;
        String description;
        String shortDescription;
        @Builder.Default
        String agenda = "";
        String title;

        EventType eventType;

        long dateStart;
        long dateEnd;
        int gmt;

        @Builder.Default
        boolean isChecked = true;
        @Builder.Default
        boolean isExchangeSyncNeeded = false;

        @JsonProperty(value = "isChecked")
        public boolean isChecked() {
            return isChecked;
        }

        @JsonProperty(value = "isExchangeSyncNeeded")
        public boolean isExchangeSyncNeeded() {
            return isExchangeSyncNeeded;
        }

        @Override
        public void accept(Creator creator) {
            creator.manage(this);
        }

        @JsonPOJOBuilder(withPrefix = "")
        @SuppressWarnings({"FieldMayBeFinal", "MismatchedQueryAndUpdateOfCollection"})
        public static final class CreatingBuilder {

            private Location location = new Location();
            private List<ObjectNode> participants = new LinkedList<>();

            @JsonAlias("Заголовок")
            public CreatingBuilder title(String title) {
                this.title = title;
                return this;
            }

            @JsonAlias("Дата и время начала")
            public CreatingBuilder dateStart(String time) {
                Converter converter = Meeting.getConverter();
                this.dateStart = converter != null ?
                        converter.convertDateTime(time) : parseInt(time);
                return this;
            }

            @JsonAlias("Дата и время окончания")
            public CreatingBuilder dateEnd(String time) {
                Converter converter = Meeting.getConverter();
                this.dateEnd = converter != null ?
                        converter.convertDateTime(time) : parseInt(time);
                return this;
            }

            @JsonAlias("Место проведения")
            public CreatingBuilder address(String address) {
                this.location.setAddress(address);
                return this;
            }

            @JsonAlias("Комментарий к месту проведения")
            public CreatingBuilder locationComment(String locationComment) {
                this.location.setLocationComment(locationComment);
                return this;
            }

            @JsonAlias("Короткое описание")
            public CreatingBuilder shortDescription(String shortDescription) {
                this.shortDescription = shortDescription;
                return this;
            }

            @JsonAlias("Описание")
            public CreatingBuilder description(String description) {
                this.description = description;
                return this;
            }

            @JsonAlias("Часовой пояс")
            public CreatingBuilder gmt(String gmt) {
                Converter converter = Meeting.getConverter();
                this.gmt = converter != null ?
                        converter.convertGmt(gmt) : parseInt(gmt);
                return this;
            }

            @JsonAlias("Тип")
            public CreatingBuilder type(String type) {
                this.eventType = EventType.getEventType(type);
                return this;
            }

            @JsonAlias("Участник")
            public CreatingBuilder participant(String searchCriteria) {
                Searcher searcher = Meeting.getSearcher();
                if (searcher != null) {
                    this.participants.add(searcher.searchParticipant(searchCriteria));
                }
                return this;
            }
        }
    }

    @Value @Builder @AllArgsConstructor(access = PRIVATE)
    @JsonDeserialize(builder = Created.CreatedBuilder.class)
    public static class Created implements CreatedEntity {

        long createdAt;
        long updatedAt;
        long dateStart;
        long dateEnd;

        int gmt;

        String id;
        String agenda;
        String description;
        String conferenceTitle;
        String shortDescription;
        String participantStatus;
        String photoDocumentId;
        String meetingStatus;
        String conferenceId;
        String organizerId;
        String title;

        EventType eventType;

        boolean isEditable;
        boolean isExchangeSyncNeeded;

        Location location;

        List<User> users;
        List<Note> notes;
        List<Participant> participants;
        List<Assignment> assignments;
        List<Document> documents;

        User updateAuthor;
        User author;

        @Override
        public void accept(Changer changer) {
            changer.manage(this);
        }

        @JsonPOJOBuilder(withPrefix = "")
        @JsonIgnoreProperties(ignoreUnknown = true)
        public static class CreatedBuilder {
        }
    }
}
