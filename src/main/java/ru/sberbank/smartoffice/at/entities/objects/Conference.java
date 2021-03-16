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
import ru.sberbank.smartoffice.at.entities.nested.Document;
import ru.sberbank.smartoffice.at.entities.nested.Location;
import ru.sberbank.smartoffice.at.entities.nested.Note;
import ru.sberbank.smartoffice.at.entities.nested.User;
import ru.sberbank.smartoffice.at.visitors.creator.Creator;
import ru.sberbank.smartoffice.at.visitors.changer.Changer;

import java.util.ArrayList;
import java.util.List;

import static java.lang.Integer.parseInt;
import static lombok.AccessLevel.PRIVATE;

@AllArgsConstructor(access = PRIVATE)
public final class Conference extends Engine {

    @Value @Builder @AllArgsConstructor(access = PRIVATE)
    @JsonDeserialize(builder = Creating.CreatingBuilder.class)
    public static class Creating implements CreatingEntity {

        @Builder.Default
        List<ObjectNode> documents = new ArrayList<>();

        Location location;

        String photoDocumentId;
        String shortDescription;
        String description;
        String title;
        String status;

        long dateStart;
        long dateEnd;
        int gmt;

        boolean isChecked = true;

        @JsonProperty(value = "isChecked")
        public boolean isChecked() {
            return isChecked;
        }

        @Override
        public void accept(Creator creator) {
            creator.manage(this);
        }

        @JsonPOJOBuilder(withPrefix = "")
        @SuppressWarnings({"FieldMayBeFinal", "MismatchedQueryAndUpdateOfCollection"})
        public static final class CreatingBuilder {
            private Location location = new Location();

            @JsonAlias("Название")
            public Creating.CreatingBuilder title(String title) {
                this.title = title;
                return this;
            }

            @JsonAlias("Описание")
            public Creating.CreatingBuilder description(String description) {
                this.description = description;
                return this;
            }

            @JsonAlias("Статус мероприятия")
            public Creating.CreatingBuilder status(String status) {
                Converter converter = Conference.getConverter();
                this.status = converter.convertStatus(status);
                return this;
            }

            @JsonAlias("Дата и время начала")
            public Creating.CreatingBuilder dateStart(String time) {
                Converter converter = Conference.getConverter();
                this.dateStart = converter != null ?
                        converter.convertDateTime(time) : parseInt(time);
                return this;
            }

            @JsonAlias("Дата и время окончания")
            public Creating.CreatingBuilder dateEnd(String time) {
                Converter converter = Conference.getConverter();
                this.dateEnd = converter != null ?
                        converter.convertDateTime(time) : parseInt(time);
                return this;
            }

            @JsonAlias("Место проведения")
            public Creating.CreatingBuilder address(String address) {
                this.location.setAddress(address);
                return this;
            }

            @JsonAlias("Комментарий к месту проведения")
            public Creating.CreatingBuilder locationComment(String locationComment) {
                this.location.setLocationComment(locationComment);
                return this;
            }

            @JsonAlias("Часовой пояс")
            public Creating.CreatingBuilder gmt(String gmt) {
                Converter converter = Conference.getConverter();
                this.gmt = converter != null ?
                        converter.convertGmt(gmt) : parseInt(gmt);
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
        String description;
        String shortDescription;
        String photoDocumentId;
        String organizeId;
        String title;
        String status;
        String eventType;

        boolean isEditable;

        Location location;

        List<Note> notes;
        List<Assignment> assignments;
        List<Document> documents;
        List<Meeting> meetings;

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
