package ru.sberbank.smartoffice.at.entities.engine;

import lombok.experimental.FieldDefaults;
import ru.sberbank.smartoffice.at.entities.converter.Converter;
import ru.sberbank.smartoffice.at.entities.searcher.Searcher;

import static lombok.AccessLevel.PRIVATE;

@FieldDefaults(level = PRIVATE, makeFinal = true)
public abstract class Engine {

    static ThreadLocal<Converter> converter = new ThreadLocal<>();
    static ThreadLocal<Searcher> searcher = new ThreadLocal<>();

    protected static Converter getConverter() {
        return Engine.converter.get();
    }

    public static void setConverter(Converter converter) {
        Engine.converter.set(converter);
    }

    protected static Searcher getSearcher() {
        return Engine.searcher.get();
    }

    public static void setSearcher(Searcher searcher) {
        Engine.searcher.set(searcher);
    }
}
