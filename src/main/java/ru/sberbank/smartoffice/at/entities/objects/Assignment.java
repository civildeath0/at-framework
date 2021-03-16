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
import ru.sberbank.smartoffice.at.entities.nested.User;
import ru.sberbank.smartoffice.at.visitors.creator.Creator;
import ru.sberbank.smartoffice.at.visitors.changer.Changer;

import javax.xml.stream.events.Comment;
import java.util.ArrayList;
import java.util.List;

import static java.lang.Boolean.parseBoolean;
import static lombok.AccessLevel.PRIVATE;
import static lombok.AccessLevel.PROTECTED;

public final class Assignment extends Engine {

    @Value @Builder @AllArgsConstructor(access = PRIVATE)
    @JsonDeserialize(builder = Assignment.Creating.CreatingBuilder.class)
    public static class Creating implements CreatingEntity {

        long factFinishDate;
        long expiresAt;
        long confirmDate;

        boolean isPersonal;
        boolean isExchangeSyncNeeded;

        String entityType;
        String entityId;
        String priority;
        String status;
        String text;
        String title;

        @Builder.Default
        List<ObjectNode> performers = new ArrayList<>();

        @Override
        public void accept(Creator creator) {
            creator.manage(this);
        }

        @JsonProperty(value = "isPersonal")
        public boolean isPersonal() {
            return isPersonal;
        }

        @JsonPOJOBuilder(withPrefix = "")
        public static final class CreatingBuilder {

            @JsonAlias("Название задачи")
            public CreatingBuilder title(String title) {
                this.title = title;
                return this;
            }

            @JsonAlias("Текст задачи")
            public CreatingBuilder text(String text) {
                this.text = text;
                return this;
            }

            @JsonAlias("Статус задачи")
            public CreatingBuilder status(String status) {
                Converter converter = Assignment.getConverter();
                this.status = converter.convertStatus(status);
                return this;
            }

            @JsonAlias("Приоритет задачи")
            public CreatingBuilder priority(String priority) {
                Converter converter = Assignment.getConverter();
                this.priority = converter.convertPriority(priority);
                return this;
            }

            @JsonAlias("Личная задача")
            public CreatingBuilder personal(String choose) {
                Converter converter = Assignment.getConverter();
                this.isPersonal = converter != null ?
                        converter.convertBoolean(choose) : parseBoolean(choose);
                return this;
            }
        }
    }

    @Value @Builder @AllArgsConstructor(access = PROTECTED)
    @JsonDeserialize(builder = Assignment.Created.CreatedBuilder.class)
    public static class Created implements CreatedEntity {

        String id;

        User author;
        User updateAuthor;

        long createdAt;
        long updatedAt;
        long factFinishDate;
        long confirmDate;
        long expiresAt;

        boolean isExpired;
        boolean isPersonal;

        String entityType;
        String entityId;
        String entityTitle;
        String number;
        String priority;
        String status;
        String text;
        String title;

        List<String> audioDocumentIds;
        List<Comment> comments;
        ObjectNode emailSentProcessing;

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
