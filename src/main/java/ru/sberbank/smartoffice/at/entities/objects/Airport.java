package ru.sberbank.smartoffice.at.entities.objects;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Value;
import ru.sberbank.smartoffice.at.entities.CreatedEntity;
import ru.sberbank.smartoffice.at.entities.CreatingEntity;
import ru.sberbank.smartoffice.at.entities.engine.Engine;
import ru.sberbank.smartoffice.at.entities.nested.User;
import ru.sberbank.smartoffice.at.visitors.creator.Creator;
import ru.sberbank.smartoffice.at.visitors.changer.Changer;

import static lombok.AccessLevel.PRIVATE;

@AllArgsConstructor(access = PRIVATE)
public final class Airport extends Engine {

    @Value @Builder @AllArgsConstructor(access = PRIVATE)
    @JsonDeserialize(builder = Creating.CreatingBuilder.class)
    public static class Creating implements CreatingEntity {

        String code;
        String name;
        String city;
        String country;
        String nameEn;
        String cityEn;
        String countryEn;

        @Override
        public void accept(Creator creator) {
            creator.manage(this);
        }

        @JsonPOJOBuilder(withPrefix = "")
        public static final class CreatingBuilder {

            @JsonAlias("Код")
            public CreatingBuilder code(String code) {
                this.code = code;
                return this;
            }

            @JsonAlias("Название")
            public CreatingBuilder name(String name) {
                this.name = name;
                return this;
            }

            @JsonAlias("Город")
            public CreatingBuilder city(String city) {
                this.city = city;
                return this;
            }

            @JsonAlias("Страна")
            public CreatingBuilder country(String country) {
                this.country = country;
                return this;
            }

            @JsonAlias("Название на английском")
            public CreatingBuilder nameEn(String nameEn) {
                this.nameEn = nameEn;
                return this;

            }

            @JsonAlias("Город на английском")
            public CreatingBuilder cityEn(String cityEn) {
                this.cityEn = cityEn;
                return this;
            }

            @JsonAlias("Страна на английском")
            public CreatingBuilder countryEn(String countryEn) {
                this.countryEn = countryEn;
                return this;
            }
        }
    }

    @Value @Builder @AllArgsConstructor(access = PRIVATE)
    @JsonDeserialize(builder = Created.CreatedBuilder.class)
    public static class Created implements CreatedEntity {

        String id;
        String code;
        String name;
        String city;
        String country;
        String nameEn;
        String cityEn;
        String countryEn;

        long createdAt;
        long updatedAt;

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
