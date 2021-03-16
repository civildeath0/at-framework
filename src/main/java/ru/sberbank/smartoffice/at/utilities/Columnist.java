package ru.sberbank.smartoffice.at.utilities;

import io.cucumber.datatable.DataTable;

import java.util.List;
import java.util.Map;

import static java.util.stream.Collectors.toMap;

/**
 * <h1>Вспомогательный класс для работы с {@link DataTable}</h1>
 */
public final class Columnist {

    private final List<Map<String, String>> data;

    /**
     * Создает экземпляр класса {@link Columnist}.
     *
     * @param dataTable {@link DataTable} значений
     */
    private Columnist(DataTable dataTable) {
        this.data = dataTable.asMaps();
    }

    /**
     * Статичный конструктор для создания экземпляра класса {@link Columnist}.
     * <p>
     * Добавлено для "синтаксического сахарка".
     *
     * @param dataTable {@link DataTable} значений
     *
     * @return экземпляр класса {@link Columnist}
     */
    public static Columnist fromTable(DataTable dataTable) {
        return new Columnist(dataTable);
    }

    /**
     * Соединяет значения двух столбцов из переданной в
     * метод {@link #fromTable(DataTable)} таблицы и соединяет
     * их в {@link Map}, где ключ - <code>firstColumn</code>,
     * а значение - <code>secondColumn</code>.
     *
     * @param firstColumn  столбец, значения которого
     *                     становятся ключом создаваемой {@link Map}
     * @param secondColumn столбец, значения которого
     *                     становятся значением создаваемой {@link Map}
     *
     * @return {@link Map}, где ключ - <code>firstColumn</code>,
     * а значение - <code>secondColumn</code>.
     */
    public Map<String, String> mapColumns(String firstColumn, String secondColumn) {
        return data.stream().collect(toMap(entry -> entry.get(firstColumn), entry -> entry.get(secondColumn)));
    }
}
