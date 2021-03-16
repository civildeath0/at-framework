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
import ru.sberbank.smartoffice.at.entities.engine.Engine;
import ru.sberbank.smartoffice.at.entities.nested.Biography;
import ru.sberbank.smartoffice.at.entities.nested.Congratulation;
import ru.sberbank.smartoffice.at.entities.nested.Spouse;
import ru.sberbank.smartoffice.at.entities.nested.User;
import ru.sberbank.smartoffice.at.visitors.changer.Changer;
import ru.sberbank.smartoffice.at.visitors.creator.Creator;

import java.util.ArrayList;
import java.util.List;

import static lombok.AccessLevel.PRIVATE;

public class Contact extends Engine {

    @Value
    @Builder
    @AllArgsConstructor(access = PRIVATE)
    @JsonDeserialize(builder = Contact.Creating.CreatingBuilder.class)
    public static class Creating implements CreatingEntity {

        Biography biography;

        Congratulation congratulation;

        @Builder.Default
        String additionalContacts = "";
        @Builder.Default
        String verifyEmail = "";
        @Builder.Default
        String communityMembership = "";

        @Builder.Default
        String meetingHistory = "";

        @Builder.Default
        boolean isShownInContacts = true;

        @Builder.Default
        List<ObjectNode> emails = new ArrayList<>();

        @Builder.Default
        List<ObjectNode> phones = new ArrayList<>();

        @Builder.Default
        List<ObjectNode> messengers = new ArrayList<>();

        @Builder.Default
        List<ObjectNode> webLinks = new ArrayList<>();


        @JsonProperty(value = "isShownInContacts")
        public boolean isShownInContacts() {
            return isShownInContacts;
        }

        @Override
        public void accept(Creator creator) {
            creator.manage(this);
        }

        @JsonPOJOBuilder(withPrefix = "")
        public static final class CreatingBuilder {
            private Biography biography = new Biography();
            private Congratulation congratulation = new Congratulation();

            @JsonAlias("Фамилия")
            public CreatingBuilder lastName(String lastName) {
                this.biography.setLastName(lastName);
                return this;
            }

            @JsonAlias("Имя")
            public CreatingBuilder firstName(String firstName) {
                this.biography.setFirstName(firstName);
                return this;
            }

            @JsonAlias("Пол")
            public CreatingBuilder gender(String gender) {
                this.biography.setGender(gender);
                return this;
            }

            @JsonAlias("День рождения")
            public CreatingBuilder birthDay(String day) {
                this.biography.setBirthDay(Long.parseLong(day));
                return this;
            }

            @JsonAlias("Месяц рождения")
            public CreatingBuilder birthMonth(String month) {
                this.biography.setBirthMonth(Long.parseLong(month));
                return this;
            }

            @JsonAlias("Год рождения")
            public CreatingBuilder birthYear(String year) {
                this.biography.setBirthYear(Long.parseLong(year));
                return this;
            }
        }
    }

    @Value @Builder @AllArgsConstructor(access = PRIVATE)
    @JsonDeserialize(builder = Created.CreatedBuilder.class)
    public static class Created implements CreatedEntity {

        String id;

        Biography biography;

        Congratulation congratulation;

        String status;
        String additionalContacts;
        String verifyEmail;
        String communityMembership;

        String meetingHistory;

        boolean isShownInContacts;

        List<ObjectNode> notes;
        List<ObjectNode> assignments;
        List<ObjectNode> meetings;
        List<ObjectNode> emails;
        List<ObjectNode> phones;
        List<ObjectNode> messengers;
        List<ObjectNode> webLinks;

        long createdAt;
        long updatedAt;

        User author;
        User updateAuthor;

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
