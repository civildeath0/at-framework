package ru.sberbank.smartoffice.at.searcher.results.items;

import lombok.AllArgsConstructor;
import lombok.Value;
import ru.sberbank.smartoffice.at.entities.nested.User;

import static lombok.AccessLevel.PRIVATE;

@Value
@AllArgsConstructor(access = PRIVATE)
public class NewsType {

    String id;
    String name;

    User author;

    long updatedAt;
}
