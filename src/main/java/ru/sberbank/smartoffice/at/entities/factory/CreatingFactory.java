package ru.sberbank.smartoffice.at.entities.factory;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.experimental.FieldDefaults;
import ru.sberbank.smartoffice.at.entities.CreatingEntity;
import ru.sberbank.smartoffice.at.entities.Factory;
import ru.sberbank.smartoffice.at.entities.factory.errors.EntityNotFound;
import ru.sberbank.smartoffice.at.entities.objects.*;

import java.util.HashMap;
import java.util.Map;

import static lombok.AccessLevel.PRIVATE;

@Getter
@AllArgsConstructor(access = PRIVATE)
@FieldDefaults(level = PRIVATE, makeFinal = true)
public enum CreatingFactory implements Factory {

    AIRPORT("Аэропорт") {
        @Override
        public CreatingEntity fillEntity(Map<String, String> filledFields) {
            return fillEntity(filledFields, Airport.Creating.class);
        }
    },
    ASSIGNMENT("Задача") {
        @Override
        public CreatingEntity fillEntity(Map<String, String> filledFields) {
            return fillEntity(filledFields, Assignment.Creating.class);
        }
    },
    FLIGHT("Перелет") {
        @Override
        public CreatingEntity fillEntity(Map<String, String> filledFields) {
            return fillEntity(filledFields, Flight.Creating.class);
        }
    },
    MATERIAL_CATEGORY("Категория материалов") {
        @Override
        public CreatingEntity fillEntity(Map<String, String> filledFields) {
            return fillEntity(filledFields, MaterialCategory.Creating.class);
        }
    },
    MEETING("Встреча") {
        @Override
        public CreatingEntity fillEntity(Map<String, String> filledFields) {
            return fillEntity(filledFields, Meeting.Creating.class);
        }
    },
    NEWS("Новость") {
        @Override
        public CreatingEntity fillEntity(Map<String, String> filledFields) {
            return fillEntity(filledFields, News.Creating.class);
        }
    },
    CONFERENCE("Мероприятие") {
        @Override
        public CreatingEntity fillEntity(Map<String, String> filledFields) {
            return fillEntity(filledFields, Conference.Creating.class);
        }
    },
    CONTACT("Контакт") {
        @Override
        public CreatingEntity fillEntity(Map<String, String> filledFields) {
            return fillEntity(filledFields, Contact.Creating.class);
        }
    },
    MATERIAL("Материал") {
        @Override
        public CreatingEntity fillEntity(Map<String, String> filledFields) {
            return fillEntity(filledFields, Material.Creating.class);
        }
    };

    static Map<String, CreatingFactory> lookup;
    static ObjectMapper objectMapper;

    static {
        objectMapper = new ObjectMapper();
        lookup = new HashMap<>() {{
            for (CreatingFactory factory : CreatingFactory.values()) {
                put(factory.getName(), factory);
            }
        }};
    }

    String name;

    public static CreatingFactory find(String entityName) {
        CreatingFactory founded = lookup.get(entityName);
        if (founded == null) {
            throw new EntityNotFound(entityName);
        }
        return founded;
    }

    protected CreatingEntity fillEntity(Map<String, String> filledFields,
                                        Class<? extends CreatingEntity> entityClass) {
        return objectMapper.convertValue(filledFields, entityClass);
    }
}
