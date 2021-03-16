package ru.sberbank.smartoffice.at.errors;

import lombok.extern.slf4j.Slf4j;

/**
 * <h1>Исключение, которое вызывается во время
 * критической ошибки в системе.</h1>
 */
@Slf4j
public class CriticalError extends Error {

    /**
     * Вызывает ошибку и выводит в консоль переданное сообщение.
     *
     * @param message сообщение об ошибке
     */
    public CriticalError(String message) {
        super(message);
        log.error(message);
    }

    /**
     * Вызывает ошибку и выводит в консоль переданное сообщение.
     *
     * @param message сообщение об ошибке
     * @param ex      произошедшая ошибка
     */
    public CriticalError(String message, Throwable ex) {
        super(message, ex);
        log.error(message, ex);
    }
}
