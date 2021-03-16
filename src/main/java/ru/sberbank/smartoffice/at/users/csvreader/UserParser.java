package ru.sberbank.smartoffice.at.users.csvreader;

import ru.sberbank.smartoffice.at.users.SmartUser;

import java.util.List;

/**
 * <h1>Имплементирует парсер пользователя</h1>
 * <p>
 * <h3>>>> Для использования требуется переопределить
 * два метода по следующей логике:</h3>
 * <p>
 * > {@link #parseLine(String[])} - принимает в качестве параметра массив
 * {@link String}, полученный после {@link String#split(String)} по
 * {@link CSVReader#separator}. Внутри должен содержать логику обработки
 * массива для создания пользователя и его сохранения в список.
 * <p>
 * В свою очередь {@link CSVReader} читает файл строка за строкой
 * и передает их по очереди в функцию {@link #parseLine(String[])}.
 * <p>
 * > {@link #getCollected()} - возвращает созданных во время
 * чтения файла пользователей.
 *
 * @param <T> тип пользователя, который будет парситься
 */
public interface UserParser<T extends SmartUser> {

    void parseLine(String[] line);

    List<T> getCollected();
}
