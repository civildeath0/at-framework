package ru.sberbank.smartoffice.at.pageobjects.core.errors;

import lombok.extern.slf4j.Slf4j;
import ru.sberbank.smartoffice.at.pageobjects.core.page.Constructor;
import ru.sberbank.smartoffice.at.pageobjects.core.page.Page;
import ru.sberbank.smartoffice.at.pageobjects.core.table.Table;

import java.lang.reflect.InvocationTargetException;

import static java.lang.String.format;

/**
 * <h1>Ошибка, вызываемая {@link Constructor}'ом и
 * строителем класса {@link Table}</h1>
 * <p>
 * Выбрасывается при ошибке инициализации {@link Page} в {@link Constructor}.
 */
@Slf4j
public class ClassInitializationError extends Error {

    /**
     * Вызывается во время ошибки
     * инициализации <code>pageClass</code>.
     *
     * @param ex        исключение вида {@link InstantiationException}
     * @param pageClass инициализируемый класс
     */
    public ClassInitializationError(InstantiationException ex, Class<?> pageClass) {
        super(format("Невозможно создать экземпляр класса \"%s\"", pageClass.getName()), ex);
        addErrorMessageToLogs();
    }

    /**
     * Вызывается, если невозможно получить доступ к
     * конструктору <code>pageClass</code>.
     *
     * @param ex        исключение вида {@link IllegalAccessException}
     * @param pageClass инициализируемый класс
     */
    public ClassInitializationError(IllegalAccessException ex, Class<?> pageClass) {
        super(String.format("Невозможно получить доступ к конструктору класса \"%s\"", pageClass.getName()), ex);
        addErrorMessageToLogs();
    }

    /**
     * Вызывается, если невозможно вызвать конструктор <code>pageClass</code>.
     *
     * @param ex        исключение вида {@link InvocationTargetException}
     * @param pageClass инициализируемый класс
     */
    public ClassInitializationError(InvocationTargetException ex, Class<?> pageClass) {
        super(format("Невозможно вызвать конструктор класса \"%s\"", pageClass.getName()), ex);
        addErrorMessageToLogs();
    }

    /**
     * Вызывается, если в классе <code>pageClass</code> отсутствует
     * искомый конструктор.
     *
     * @param ex        исключение вида {@link NoSuchMethodException}
     * @param pageClass инициализируемый класс
     */
    public ClassInitializationError(NoSuchMethodException ex, Class<?> pageClass) {
        super(format("У класса \"%s\" отсутствует искомый конструктор", pageClass.getName()), ex);
        addErrorMessageToLogs();
    }

    /**
     * Добавляет сообщение ошибки и ее причину в логи.
     */
    private void addErrorMessageToLogs() {
        log.error(getMessage(), getCause());
    }
}
