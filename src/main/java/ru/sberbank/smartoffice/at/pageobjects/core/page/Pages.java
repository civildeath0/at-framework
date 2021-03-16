package ru.sberbank.smartoffice.at.pageobjects.core.page;

import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;
import ru.sberbank.smartoffice.at.annotations.Name;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import static java.lang.String.format;

/**
 * <h1>Хранитель страниц</h1>
 * <p>
 * Инициализация страниц происходит
 * вызовом метода {@link Constructor#initPages(String)}.
 */
@Slf4j
public abstract class Pages {

    /**
     * Хранилище страниц.
     * <p>
     * У класса {@link ConcurrentHashMap} переопределены методы
     * {@link ConcurrentHashMap#get(Object) get(Object)} и
     * {@link ConcurrentHashMap#put(Object, Object) put(String, Page)}.
     * <p>
     * Вызов метода {@link ConcurrentHashMap#get(Object) get(Object)} возвращает страницу
     * с переданным именем. Если такая страница отсутствует,
     * будет выброшено исключение {@link NullPointerException}.
     * <p>
     * Вызов метода {@link ConcurrentHashMap#put(Object, Object) put(String, Page)} добавляет
     * страницу в хранилище. Если страница с таким именем уже существует, значение
     * будет перезаписано. Предупреждение о перезаписи будет записано в логи с указанием
     * классов, у которых в аннотации {@link Name} выставлены одинаковые значения.
     */
    static final Map<String, Page> pages = new ConcurrentHashMap<>() {

        @Override
        public Page get(Object pageName) {
            Page page = super.get(pageName);
            if (page == null) {
                throw new NullPointerException(format("Страница с имененм \"%s\" не существует", pageName));
            }
            return page;
        }

        @Override
        public Page put(@NotNull String pageName, @NotNull Page page) {
            Page existingPage = super.put(pageName, page);
            if (existingPage != null) {
                log.warn("Страница с имененем \"{}\" класса \"{}\" была перезапиана страницей с таким же именем из класса \"{}\"",
                        pageName, existingPage.getClass().getName(), page.getClass().getName());
                return page;
            }
            return null;
        }
    };

    /**
     * Возвращает {@link Page} с переданным именем.
     *
     * @param name имя страницы, объявленное
     *             в {@link Name} аннотации
     *
     * @return {@link Page} с переданным именем
     */
    public static Page getPage(String name) {
        return pages.get(name);
    }

    /**
     * Возвращает {@link Page}, соответствующую переданному классу.
     * <p>
     * Среди проинициализированных страниц, находит ту, класс
     * которой соответствует типу {@link T}, и возвращает
     * ее, приведенную к {@link T}.
     *
     * @param className класс искомой страницы
     * @param <T>       тип класса искомой страницы
     *
     * @return {@link Page}, приведенную к типу {@link T}
     */
    public static <T extends Page> T getPage(Class<T> className) {
        return className.cast(pages.values().stream().filter(page -> page.getClass().equals(className))
                .findFirst().orElseThrow(() -> {
                    throw new NullPointerException(format("Класс \"%s\" не найден среди страниц",
                            className.getName()));
                }));
    }
}
