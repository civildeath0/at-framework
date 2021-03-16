package ru.sberbank.smartoffice.at.users.csvreader;

import lombok.experimental.FieldDefaults;
import ru.sberbank.smartoffice.at.users.SmartUser;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

import static java.nio.charset.StandardCharsets.UTF_8;
import static lombok.AccessLevel.PRIVATE;

@FieldDefaults(
        makeFinal = true,
        level = PRIVATE
)
public final class CSVReader {

    File file;
    /**
     * Разделитель использующийся в csv-файле.
     * <p>
     * По умолчанию будет использоваться <code>','</code>.
     * <p>
     * Для переопределения в конструкторе
     * {@link #CSVReader(File, String)} следует передать
     * вторым параметром требуемый разделитель.
     */
    String separator;

    /**
     * Конструктор класса {@link CSVReader}.
     *
     * @param file      файл для чтения
     * @param separator разделитель, использующийся в файле
     */
    public CSVReader(File file, String separator) {
        this.file = file;
        this.separator = separator;
    }

    /**
     * Конструктор класса {@link CSVReader}.
     *
     * @param file файл для чтения
     */
    public CSVReader(File file) {
        this(file, ",");
    }

    /**
     * Парсит файл, переданный при инициализации класса, с помощью
     * класса, имплементирующего интерфейс {@link UserParser}.
     *
     * @param lineParser класс, имплементирующий интерфейс {@link UserParser}
     * @param <T>        тип пользователя, который будет парситься.
     *                   Обязан наследоваться от {@link SmartUser}
     *
     * @return {@link List} <{@link T}>, где {@link T} - тип
     * пользователя, отнаследованный от {@link SmartUser}
     */
    public <T extends SmartUser> List<T> parseWith(UserParser<T> lineParser) {
        try (BufferedReader reader = new BufferedReader(new FileReader(file, UTF_8))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] args = line.split(separator);
                lineParser.parseLine(args);
            }
        } catch (IOException ex) {
            throw new Error(ex.getMessage(), ex);
        }
        return lineParser.getCollected();
    }
}
