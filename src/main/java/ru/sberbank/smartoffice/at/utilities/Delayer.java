package ru.sberbank.smartoffice.at.utilities;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.Stopwatch;
import lombok.NonNull;

import java.util.function.Supplier;

@SuppressWarnings("unused")
public abstract class Delayer {

    /**
     * Делает тоже самое, что и {@link #waitValue(Supplier, long, long)},
     * но {@code pollingIntervalMilliseconds}, берется из {@link Configuration#pollingInterval},
     * а {@code timeoutMilliseconds} - из {@link Configuration#timeout}.
     *
     * @param supplier вызываемый метод, возвращающий объект типа {@link T}
     * @param <T>      тип возвращаемого значения
     *
     * @return значение, возвращаемое из {@link Supplier supplier}
     */
    public static <T> T waitValue(@NonNull Supplier<T> supplier) {
        return waitValue(supplier, Configuration.timeout, Configuration.pollingInterval);
    }

    /**
     * Делает тоже самое, что и {@link #waitValue(Supplier, long, long)},
     * но {@code pollingIntervalMilliseconds}, берется их {@link Configuration#pollingInterval}.
     *
     * @param supplier            вызываемый метод, возвращающий объект типа {@link T}
     * @param timeoutMilliseconds максимальное время ожидания в миллисекундах
     * @param <T>                 тип возвращаемого значения
     *
     * @return значение, возвращаемое из {@link Supplier supplier}
     */
    public static <T> T waitValue(@NonNull Supplier<T> supplier, long timeoutMilliseconds) {
        return waitValue(supplier, timeoutMilliseconds, Configuration.pollingInterval);
    }

    /**
     * Метод-аналог {@link SelenideElement#should(Condition...)}.
     * <p>
     * Вызывает {@link Supplier<T> supplier}, каждые {@code pollingIntervalMilliseconds}
     * миллисекунд, на протяжении {@code timeoutMilliseconds}.
     * <p>
     * Если до прошествия таймаута, {@link Supplier supplier} вернет не
     * {@code null} значение, метод вернет это значение.
     * <p>
     * Если же во время вызовов {@link Supplier supplier}, выбрасываются исклключения,
     * метод будет их отлавливать до прошествия {@code timeoutMilliseconds}, а в конце,
     * если возвращаемое значение все еще {@code null}, будет выброшено исключение,
     * которое было поймано из {@link Supplier supplier} последним.
     * <p>
     * Если же {@link Supplier supplier} возвращает {@code null} значение, и не выбрасывает
     * исключений, то по прошествии {@code timeoutMilliseconds} будет возвращен {@code null}.
     *
     * @param supplier                    вызываемый метод, возвращающий объект типа {@link T}
     * @param timeoutMilliseconds         максимальное время ожидания в миллисекундах
     * @param pollingIntervalMilliseconds интервал при вызове
     *                                    {@link Supplier supplier} в миллисекундах
     * @param <T>                         тип возвращаемого значения
     *
     * @return значение, возвращаемое из {@link Supplier supplier}
     */
    public static <T> T waitValue(@NonNull Supplier<T> supplier,
                                  long timeoutMilliseconds, long pollingIntervalMilliseconds) {
        T var;
        Throwable lastError = null;
        Stopwatch stopwatch = new Stopwatch(timeoutMilliseconds);
        do {
            try {
                var = supplier.get();
                if (var != null) {
                    return var;
                }
            } catch (Throwable throwable) {
                lastError = throwable;
            }
            stopwatch.sleep(pollingIntervalMilliseconds);
        } while (!stopwatch.isTimeoutReached());

        if (lastError instanceof AssertionError) {
            throw new AssertionError(lastError);
        } else if (lastError != null) {
            throw new Error(lastError);
        } else {
            return null;
        }
    }
}
