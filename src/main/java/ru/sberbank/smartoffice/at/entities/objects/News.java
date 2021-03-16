package ru.sberbank.smartoffice.at.entities.objects;

import com.fasterxml.jackson.annotation.JsonAlias;
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
import ru.sberbank.smartoffice.at.visitors.creator.Creator;
import ru.sberbank.smartoffice.at.visitors.changer.Changer;

import java.util.ArrayList;
import java.util.List;

import static java.lang.Boolean.parseBoolean;
import static java.lang.Integer.parseInt;
import static lombok.AccessLevel.PRIVATE;
import static lombok.AccessLevel.PROTECTED;

@AllArgsConstructor(access = PRIVATE)
public final class News extends Engine {


    @Value @Builder @AllArgsConstructor(access = PRIVATE)
    @JsonDeserialize(builder = News.Creating.CreatingBuilder.class)
    public static class Creating implements CreatingEntity {

        long publishedAt;

        String title;
        String subtitle;

        String typeId;
        String companyId;

        @Builder.Default
        String sourceLink = "";
        @Builder.Default
        String sourceName = "";
        @Builder.Default
        String photoDocumentId = "";
        String message;

        boolean showAllChiefs;
        boolean isDayTopic;
        @Builder.Default
        List<ObjectNode> users = new ArrayList<>();
        @Builder.Default
        List<ObjectNode> documents = new ArrayList<>();

        @JsonProperty(value = "isDayTopic")
        public boolean isDayTopic() {
            return isDayTopic;
        }

        @Override
        public void accept(Creator creator) {
            creator.manage(this);
        }

        @JsonPOJOBuilder(withPrefix = "")
        @SuppressWarnings({"FieldMayBeFinal", "MismatchedQueryAndUpdateOfCollection"})
        public static final class CreatingBuilder {

            @JsonAlias("Дата и время публикации")
            public CreatingBuilder publishedAt(String time) {
                Converter converter = News.getConverter();
                this.publishedAt = converter != null ?
                        converter.convertDateTime(time) : parseInt(time);
                return this;
            }

            @JsonAlias("Заголовок")
            public CreatingBuilder title(String title) {
                this.title = title;
                return this;
            }

            @JsonAlias("Подзаголовок")
            public CreatingBuilder subtitle(String subtitle) {
                this.subtitle = subtitle;
                return this;
            }

            @JsonAlias("Рубрика")
            public CreatingBuilder typeId(String documentTypeName) {
                Searcher searcher = News.getSearcher();
                if (searcher != null) {
                    this.typeId = searcher.searchTypeId(documentTypeName);
                }
                return this;
            }

            @JsonAlias("Компания")
            public CreatingBuilder companyId(String companyName) {
                Searcher searcher = News.getSearcher();
                if (searcher != null) {
                    this.companyId = searcher.searchCompanyId(companyName);
                }
                return this;
            }

            @JsonAlias("Текст новости")
            public CreatingBuilder text(String message) {
                this.message = message;
                return this;
            }

            @JsonAlias("Показать всем")
            public CreatingBuilder showAllChiefs(String show) {
                Converter converter = News.getConverter();
                this.showAllChiefs = converter != null ?
                        converter.convertBoolean(show) : parseBoolean(show);
                return this;
            }

            @JsonAlias("Новость дня")
            public CreatingBuilder isDayTopic(String choose) {
                Converter converter = News.getConverter();
                this.isDayTopic = converter != null ?
                        converter.convertBoolean(choose) : parseBoolean(choose);
                return this;
            }
        }
    }

    @Value @Builder @AllArgsConstructor(access = PROTECTED)
    @JsonDeserialize(builder = News.Created.CreatedBuilder.class)
    public static class Created implements CreatedEntity {

        long publishedAt;
        long createdAt;
        long updatedAt;

        String id;
        String title;
        String subtitle;
        String type;
        String typeId;
        String companyId;
        String companyName;
        String status;
        String sourceLink;
        String sourceName;
        String photoDocumentId;
        String message;

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
        public static class CreatedBuilder {
        }
    }
}
