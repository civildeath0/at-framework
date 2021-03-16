package ru.sberbank.smartoffice.at.pageobjects.core.errors;

import ru.sberbank.smartoffice.at.pageobjects.core.element.Element;

/**
 * <h1>Ошибка вызываемая методом {@link Element#as(Class)}</h1>
 * <p>
 * Вызывается при невозможности привести
 * {@link Element} к передаваемому типу класса.
 */
public class ElementCastError extends Error {

    /**
     * Вызвается при невозможности кастинга
     * класса <code>from</code> к <code>to</code>
     *
     * @param from кастуемый класс
     * @param to   класс, к которому происходит кастинг
     */
    public ElementCastError(Class<?> from, Class<?> to) {
        super(String.format("Невозможно кастануть класс \"%s\" к классу \"%s\"",
                from.getSimpleName(), to.getSimpleName()));
    }
}
