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
import ru.sberbank.smartoffice.at.entities.nested.Note;
import ru.sberbank.smartoffice.at.entities.nested.User;
import ru.sberbank.smartoffice.at.entities.searcher.Searcher;
import ru.sberbank.smartoffice.at.visitors.changer.Changer;
import ru.sberbank.smartoffice.at.visitors.creator.Creator;

import java.util.ArrayList;
import java.util.List;

import static java.lang.Boolean.parseBoolean;
import static lombok.AccessLevel.PRIVATE;

@AllArgsConstructor(access = PRIVATE)
public final class Material extends Engine {

    @Value
    @Builder
    @AllArgsConstructor(access = PRIVATE)
    @JsonDeserialize(builder = Creating.CreatingBuilder.class)
    public static class Creating implements CreatingEntity {

        String id;
        String title;
        String categoryId;
        @Builder.Default
        String description = "";

        @Builder.Default
        List<ObjectNode> eventId = new ArrayList<>();

        boolean isPrivate;
        boolean isGeneral;
        boolean isInterestingPlace;
        @Builder.Default
        boolean isExchangeSyncNeeded = true;

        @JsonProperty(value = "isPrivate")
        public boolean isPrivate() {
            return isPrivate;
        }

        @JsonProperty(value = "isGeneral")
        public boolean isGeneral() {
            return isGeneral;
        }

        @JsonProperty(value = "isInterestingPlace")
        public boolean isInterestingPlace() {
            return isInterestingPlace;
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
        public static final class CreatingBuilder {

            @JsonAlias("Заголовок")
            public CreatingBuilder title(String title) {
                this.title = title;
                return this;
            }

            @JsonAlias("Категория")
            public CreatingBuilder category(String category) {
                Searcher searcher = Meeting.getSearcher();
                this.categoryId = searcher.searchCategoryId(category);
                return this;
            }

            @JsonAlias("Личный")
            public CreatingBuilder isPrivate(String choose) {
                Converter converter = Material.getConverter();
                this.isPrivate = converter != null ?
                        converter.convertBoolean(choose) : parseBoolean(choose);
                return this;
            }

            @JsonAlias("Общий")
            public CreatingBuilder isGeneral(String choose) {
                Converter converter = Material.getConverter();
                this.isGeneral = converter != null ?
                        converter.convertBoolean(choose) : parseBoolean(choose);
                return this;
            }

            @JsonAlias("Интересное место")
            public CreatingBuilder isInterestingPlace(String choose) {
                Converter converter = Material.getConverter();
                this.isInterestingPlace = converter != null ?
                        converter.convertBoolean(choose) : parseBoolean(choose);
                return this;
            }
        }
    }

    @Value
    @Builder
    @AllArgsConstructor(access = PRIVATE)
    @JsonDeserialize(builder = Created.CreatedBuilder.class)
    public static class Created implements CreatedEntity {

        String id;
        long publishedAt;
        long createdAt;
        long updatedAt;

        String title;
        String categoryId;
        String description;

        boolean showAllChiefs;
        boolean isDayTopic;

        List<User> users;
        List<Note> notes;
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
