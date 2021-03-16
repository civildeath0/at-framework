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
import ru.sberbank.smartoffice.at.entities.nested.AirportObject;
import ru.sberbank.smartoffice.at.entities.nested.User;
import ru.sberbank.smartoffice.at.entities.searcher.Searcher;
import ru.sberbank.smartoffice.at.visitors.creator.Creator;
import ru.sberbank.smartoffice.at.visitors.changer.Changer;

import java.util.ArrayList;
import java.util.List;

import static java.lang.Integer.parseInt;
import static lombok.AccessLevel.PRIVATE;

@AllArgsConstructor(access = PRIVATE)
public final class Flight extends Engine {

    @Value @Builder @AllArgsConstructor(access = PRIVATE)
    @JsonDeserialize(builder = Creating.CreatingBuilder.class)
    public static class Creating implements CreatingEntity {

        String flightCode;
        String conferenceId;
        String title;
        String description;
        String shortDescription;

        long dateStart;
        long dateEnd;

        int sourceGmt;
        int destinationGmt;

        AirportObject sourceAirport;
        AirportObject destinationAirport;

        @Builder.Default
        boolean isChecked = true;

        List<ObjectNode> participants;
        @Builder.Default
        List<ObjectNode> documents = new ArrayList<>();

        @JsonProperty(value = "isChecked")
        public boolean isChecked() {
            return isChecked;
        }

        @Override
        public void accept(Creator creator) {
            creator.manage(this);
        }

        @JsonPOJOBuilder(withPrefix = "")
        @JsonIgnoreProperties(ignoreUnknown = true)
        public static final class CreatingBuilder {

            @JsonAlias("Заголовок")
            public CreatingBuilder title(String title) {
                this.title = title;
                return this;
            }

            @JsonAlias("Дата и время вылета")
            public CreatingBuilder dateStart(String time) {
                Converter converter = Flight.getConverter();
                this.dateStart = converter != null ?
                        converter.convertDateTime(time) : parseInt(time);
                return this;
            }

            @JsonAlias("Дата и время прибытия")
            public CreatingBuilder dateEnd(String time) {
                Converter converter = Flight.getConverter();
                this.dateEnd = converter != null ?
                        converter.convertDateTime(time) : parseInt(time);
                return this;
            }

            @JsonAlias("Часовой пояс вылета")
            public CreatingBuilder sourceGmt(String gmt) {
                Converter converter = Flight.getConverter();
                this.sourceGmt  = converter != null ?
                        converter.convertGmt(gmt) : parseInt(gmt);
                return this;
            }

            @JsonAlias("Часовой пояс прибытия")
            public CreatingBuilder destinationGmt(String gmt) {
                Converter converter = Flight.getConverter();
                this.destinationGmt  = converter != null ?
                        converter.convertGmt(gmt) : parseInt(gmt);
                return this;
            }

            @JsonAlias("Откуда")
            public CreatingBuilder sourceAirport(String airport) {
                sourceAirport = new AirportObject();
                Searcher searcher = Flight.getSearcher();
                if (searcher != null) {
                    this.sourceAirport.setId(searcher.searchAirportId(airport));
                }
                return this;
            }

            @JsonAlias("Куда")
            public CreatingBuilder destinationAirport(String airport) {
                destinationAirport = new AirportObject();
                Searcher searcher = Flight.getSearcher();
                if (searcher != null) {
                    this.destinationAirport.setId(searcher.searchAirportId(airport));
                }
                return this;
            }
        }
    }

    @Value @Builder @AllArgsConstructor(access = PRIVATE)
    @JsonDeserialize(builder = Created.CreatedBuilder.class)
    public static class Created implements CreatedEntity {

        String id;
        String title;
        String flightCode;
        String description;
        String conferenceId;
        String shortDescription;

        long createdAt;
        long updatedAt;
        long dateStart;
        long dateEnd;

        User author;
        User updateAuthor;

        int sourceGmt;
        int destinationGmt;

        AirportObject sourceAirport;
        AirportObject destinationAirport;

        List<ObjectNode> participants;
        List<ObjectNode> documents;

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
