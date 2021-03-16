package ru.sberbank.smartoffice.at.errors;

import org.jetbrains.annotations.Contract;

import javax.annotation.Nonnull;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.function.BiConsumer;
import java.util.function.Consumer;

import static java.lang.String.join;

/**
 * <h1>Класс для сбора исключений</h1>
 */
@SuppressWarnings("unused")
public final class Collector {

    private final ArrayList<Throwable> failures;

    public Collector() {
        failures = new ArrayList<>();
    }

    /**
     * Проверяет, присутствуют ли в памяти исключение. Если да, выбрасывает {@link TestError} с описанием всех собранных
     * ошибок, в противном случае - ничего не происходит.
     */
    public void verify() {
        if (!failures.isEmpty()) {
            throw formSingleMessage();
        }
    }

    /**
     * Формирует единое сообщение об ошибке из списка ошибок и возвращает их как {@link TestError}.
     *
     * @return исключение вида {@link TestError}
     */
    @Nonnull
    private TestError formSingleMessage() {
        Throwable collect = new Throwable();
        List<String> messages = new ArrayList<>();
        failures.forEach(error -> {
            messages.add(error.getMessage());
            collect.addSuppressed(error);
        });
        return new TestError(join("\n", messages), collect.getSuppressed()[0]);
    }

    /**
     * Выполняет функцию {@link Consumer}, передавая в качестве аргумента элементы {@link Iterable} коллекции,
     * отлавливая {@link Exception}.
     *
     * @param iterable коллекция элементов
     * @param consumer функция, применяемая к каждому элементу коллекчии
     * @param <T>      тип коллекции
     *
     * @return this {@link Collector}
     */
    @Contract("_, _ -> this")
    public <T> Collector checkCollection(@Nonnull Iterable<T> iterable, Consumer<T> consumer) {
        for (T element : iterable) {
            try {
                consumer.accept(element);
            } catch (AssertionError error) {
                addError(error);
            }
        }
        return this;
    }

    /**
     * Выполняет функцию {@link Consumer}, передавая в качестве аргемента элементы {@link Iterable} коллекции,
     * отлавливая {@link Exception}.
     *
     * @param map      коллекция элементов
     * @param consumer функция, применяемая к каждому элементу коллекчии
     * @param <K>      тип ключа у словаря
     * @param <V>      тип значения у словаря
     *
     * @return this {@link Collector}
     */
    @Contract("_, _ -> this")
    public <K, V> Collector checkCollection(@Nonnull Map<K, V> map, BiConsumer<K, V> consumer) {
        for (Entry<K, V> set : map.entrySet()) {
            try {
                consumer.accept(set.getKey(), set.getValue());
            } catch (AssertionError error) {
                addError(error);
            }
        }
        return this;
    }

    public void addError(Throwable error) {
        failures.add(error);
    }
}
