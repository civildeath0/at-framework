package ru.sberbank.smartoffice.at.pageobjects.core.page;

import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;
import ru.sberbank.smartoffice.at.annotations.Name;
import ru.sberbank.smartoffice.at.pageobjects.core.element.Element;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static java.lang.String.format;
import static lombok.AccessLevel.PRIVATE;

/**
 * <h1>Репрезентация web-страницы</h1>
 * <p>
 * Для создания класса, реализующего паттерн
 * <a href=https://habr.com/ru/company/wapstart/blog/138674/>PageObject</a>,
 * он должен наследовать {@link Page} и в аннотации {@link Name} должно
 * быть присвоено имя страницы.
 */
@Slf4j
@FieldDefaults(level = PRIVATE, makeFinal = true)
public abstract class Page {

    /**
     * Хранилище полей.
     * <p>
     * У класса {@link HashMap} переопределены методы
     * {@link HashMap#get(Object) get(Object)} и
     * {@link HashMap#put(Object, Object) put(String, Page)}.
     * <p>
     * Вызов метода {@link HashMap#get(Object) get(Object)} возвращает элемент
     * с переданным именем. Если такой элемент отсутствует,
     * будет выброшено исключение {@link NullPointerException}.
     * <p>
     * Вызов метода {@link HashMap#put(Object, Object) put(String, Element)} добавляет
     * элемент в хранилище. Если элемент с таким именем уже существует, значение
     * будет перезаписано. Предупреждение о перезаписи будет записано в логи.
     */
    Map<String, Element> elements = new HashMap<>() {

        @Override
        public Element get(Object fieldName) {
            Element element = super.get(fieldName);
            if (element == null) {
                throw new NullPointerException(format("Элемент с именем \"%s\" не существует", fieldName));
            }
            return element;
        }

        @Override
        public Element put(@NotNull String fieldName, @NotNull Element field) {
            Element existingElement = super.put(fieldName, field);
            if (existingElement != null) {
                log.warn("Элемент с именем \"{}\" не найдено в классе \"{}\"",
                        fieldName, existingElement.getClass().getName());
                return existingElement;
            }
            return null;
        }
    };

    /**
     * Добавляет список {@link Element} в хранилище.
     *
     * @param elements список {@link Element}, которыми обладает страница
     */
    public final void addElements(List<Element> elements) {
        elements.forEach(element -> this.elements.put(element.getName(), element));
    }

    /**
     * Возвращает {@link Element} с переданным именем.
     *
     * @param elementName имя искомого {@link Element}
     *
     * @return {@link Element} с переданным именем
     */
    public final Element getElement(String elementName) {
        return elements.get(elementName);
    }
}
