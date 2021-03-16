package ru.sberbank.smartoffice.at.entities.objects;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
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

import static java.lang.Boolean.parseBoolean;
import static lombok.AccessLevel.PRIVATE;

@AllArgsConstructor(access = PRIVATE)
public final class MaterialCategory extends Engine {

    @Value @Builder @AllArgsConstructor(access = PRIVATE)
    @JsonDeserialize(builder = Creating.CreatingBuilder.class)
    public static class Creating implements CreatingEntity {

        String name;
        String shortName;

        boolean showForWidget;

        @Override
        public void accept(Creator creator) {
            creator.manage(this);
        }

        @JsonPOJOBuilder(withPrefix = "")
        public static final class CreatingBuilder {

            @JsonAlias("Название")
            public Creating.CreatingBuilder name(String name) {
                this.name = name;
                return this;
            }

            @JsonAlias("Короткое название")
            public Creating.CreatingBuilder shortName(String shortName) {
                this.shortName = shortName;
                return this;
            }

            @JsonAlias("Показывать для виджета")
            public Creating.CreatingBuilder showForWidget(String show) {
                Converter converter = MaterialCategory.getConverter();
                this.showForWidget = converter != null ?
                        converter.convertBoolean(show) : parseBoolean(show);
                return this;
            }
        }
    }

    @Value @Builder @AllArgsConstructor(access = PRIVATE)
    @JsonDeserialize(builder = Created.CreatedBuilder.class)
    public static class Created implements CreatedEntity {

        String id;
        String name;
        String shortName;

        boolean showForWidget;

        long createdAt;
        long updatedAt;

        User updateAuthor;
        User author;

        @Override
        public void accept(Changer changer) {
            changer.manage(this);
        }

        @JsonPOJOBuilder(withPrefix = "")
        public static final class CreatedBuilder {
        }
    }
}
