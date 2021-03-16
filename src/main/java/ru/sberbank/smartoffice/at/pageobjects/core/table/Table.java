package ru.sberbank.smartoffice.at.pageobjects.core.table;


import com.codeborne.selenide.SelenideElement;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.NonNull;
import lombok.ToString;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import ru.sberbank.smartoffice.at.pageobjects.core.element.Element;
import ru.sberbank.smartoffice.at.pageobjects.core.errors.ClassInitializationError;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;

import static java.lang.String.format;
import static lombok.AccessLevel.PRIVATE;

/**
 * <h1>Репрезентация таблицы</h1>
 */
@Slf4j
@ToString
@EqualsAndHashCode(callSuper = true)
public final class Table extends Element {

    @NonNull Map<String, Column> columns;

    /**
     * Конструктор класса {@link Table}.
     *
     * @param name    имя таблицы
     * @param table   {@link SelenideElement}, указывающий на таблицу
     * @param columns {@link Map}, где ключ - название столбца, а
     *                значение - {@link Column}
     */
    @Builder
    private Table(String name, SelenideElement table, Map<String, Column> columns) {
        super(name, table);
        this.columns = columns;
    }

    /**
     * Возвращает {@link SelenideElement}, описывающий искомую
     * строку на оcнове переданной {@link Map}.
     *
     * @param textByColumns {@link Map}, где ключ - название столбца, а значение - текст
     *                      в этом столбце.
     *                      <p>
     *                      Значение будет трансформировано из
     *                      понятной читателю формы в соответствующий xpath
     *                      с помощью метода {@link Column#getFilteredValue(String)}
     *
     * @return {@link SelenideElement}, описывающий искомую
     * строку на сонове переданной {@link Map}
     */
    public SelenideElement getRow(@NonNull Map<String, String> textByColumns) {
        StringBuilder builder = new StringBuilder().append(".//tr");
        textByColumns.forEach((columnName, text) -> {
            Column column = columns.get(columnName);
            builder.append(format("[.//td[%d][%s]]",
                    column.getIndex(), column.getFilteredValue(text)));
        });
        return getElement().$x(builder.toString());
    }

    /**
     * <h1>Строитель класса {@link Table}</h1>
     */
    @SuppressWarnings({"FieldMayBeFinal", "MismatchedQueryAndUpdateOfCollection", "unused"})
    public static final class TableBuilder {

        /**
         * Хранилище столбцов.
         * <p>
         * У класса {@link HashMap} переопределены методы
         * {@link HashMap#get(Object) get(Object)} и
         * {@link HashMap#put(Object, Object) put(String, Page)}.
         * <p>
         * Вызов метода {@link HashMap#get(Object) get(Object)} возвращает {@link Column}
         * с переданным именем. Если такой столбец отсутствует,
         * будет выброшено исключение {@link NullPointerException}.
         * <p>
         * Вызов метода {@link HashMap#put(Object, Object) put(String, Column)} добавляет
         * {@link Column} в хранилище. Если столбец с таким именем уже существует, значение
         * будет перезаписано. Предупреждение о перезаписи будет записано в логи.
         */
        private Map<String, Column> columns = new HashMap<>() {

            @Override
            public Column get(Object name) {
                Column column = super.get(name);
                if (column == null) {
                    throw new NoSuchElementException(format("Столбец с названием \"%s\" не найден", name));
                }
                return column;
            }

            @Override
            public Column put(String name, Column column) {
                Column existingColumn = super.put(name, column);
                if (existingColumn != null) {
                    log.warn(format("Столбец с названием \"%s\" был перезаписан", name));
                }
                return existingColumn;
            }
        };
        private int index = 1;

        /**
         * Добавляет в хранилище {@link Column} новый столбец, класса
         * {@link Class<T> cClass}, с именем <code>name</code>.
         * <p>
         * Автоматически проставляет индекс (нумерация начинается с 1), в
         * зависимости от порядка добавления.
         *
         * @param cClass тип, добавляемого столбца
         * @param name   текст столбца
         * @param <T>    тип столбца, наследующий {@link Column}
         *
         * @return <code>this</code>
         */
        public <T extends Column> TableBuilder column(Class<T> cClass, String name) {
            Column column = new Initializer<>(cClass)
                    .getInstance(index++, table, name);
            this.columns.put(column.getTitle(), column);
            return this;
        }

        /**
         * Так как индексирование, добавляемых столбцов, происходит в
         * автоматическом режиме, то для инкремента счетчика и добавления
         * последующего столбца с правильным индексом добавлен этот метод.
         * <p>
         * Иными словами, метод создан для инкремента
         * индекса, без добавления столбца.
         *
         * @return <code>this</code>
         */
        public TableBuilder column() {
            index++;
            return this;
        }
    }

    /**
     * <h1>Инициализатор класса {@link Column}</h1>
     *
     * @param <T> тип класса, наследующий {@link Column}
     */
    @FieldDefaults(level = PRIVATE, makeFinal = true)
    private static class Initializer<T extends Column> {

        Class<T> cClass;

        /**
         * <h2>Инициализатор класса</h2>
         *
         * @param cClass инициализируемый класс, наследованный от {@link Column}.
         */
        private Initializer(Class<T> cClass) {
            this.cClass = cClass;
        }

        /**
         * Возвращает конструктор <code>cClass</code>.
         * <p>
         * Находит конструктор удовлетворяющей сигнатуре
         * <code>({@link Integer}, {@link SelenideElement}, {@link String})</code> и возвращает его.
         *
         * @return конструктор <code>cClass</code>
         */
        private Constructor<T> getConstructor() {
            try {
                return cClass.getConstructor(Integer.class, SelenideElement.class, String.class);
            } catch (NoSuchMethodException ex) {
                throw new ClassInitializationError(ex, cClass);
            }
        }

        /**
         * Возвращает экземпляр класса <code>cClass</code>.
         *
         * @param index      индекс колонки
         * @param table      родительская таблицы
         * @param columnName название колонки
         *
         * @return экземпляр класса <code>cClass</code>
         */
        private T getInstance(int index, SelenideElement table, String columnName) {
            try {
                return getConstructor().newInstance(index, table, format("[%s]", columnName));
            } catch (IllegalAccessException ex) {
                throw new ClassInitializationError(ex, cClass);
            } catch (InvocationTargetException ex) {
                throw new ClassInitializationError(ex, cClass);
            } catch (InstantiationException ex) {
                throw new ClassInitializationError(ex, cClass);
            }
        }
    }
}
