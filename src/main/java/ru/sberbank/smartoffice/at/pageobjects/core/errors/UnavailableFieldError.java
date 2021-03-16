package ru.sberbank.smartoffice.at.pageobjects.core.errors;

import ru.sberbank.smartoffice.at.pageobjects.core.page.Constructor;
import ru.sberbank.smartoffice.at.pageobjects.core.page.Page;

import java.lang.reflect.Field;

import static java.lang.String.format;

/**
 * <h1>Ошибка, вызываемая {@link Constructor}'ом</h1>
 * <p>
 * Вызывается при ошибке добавления полей в {@link Page}.
 */
public class UnavailableFieldError extends IllegalAccessError {

    /**
     * Вызывается во время невозможности получения значения
     * поля у класса, репрезентирующего страницу.
     *
     * @param field поле, значение которого не удалось получить
     * @param page  класс, в котором находится поле
     */
    public UnavailableFieldError(Field field, Class<? extends Page> page) {
        super(format("Невозможно получить значение поля \"%s\" в классе \"%s\"",
                field.getName(), page.getName()));
    }
}
