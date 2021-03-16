package ru.sberbank.smartoffice.at.searcher.results;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Value;

import java.util.List;

import static lombok.AccessLevel.PRIVATE;

@Value @Builder
@AllArgsConstructor(access = PRIVATE)
public class SearchResult<T> {

    int limitFrom;
    int limitSize;

    List<T> items;
}
