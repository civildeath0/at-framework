package ru.sberbank.smartoffice.at.pageobjects.core.page;

import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.reflections.Reflections;
import org.reflections.util.ConfigurationBuilder;
import ru.sberbank.smartoffice.at.annotations.Name;
import ru.sberbank.smartoffice.at.pageobjects.core.element.Element;
import ru.sberbank.smartoffice.at.pageobjects.core.errors.ClassInitializationError;
import ru.sberbank.smartoffice.at.pageobjects.core.errors.UnavailableFieldError;

import javax.annotation.Nonnull;
import java.lang.reflect.InvocationTargetException;
import java.util.List;

import static java.util.Arrays.stream;
import static java.util.stream.Collectors.toList;
import static java.util.stream.Collectors.toMap;
import static lombok.AccessLevel.PRIVATE;
import static org.reflections.util.ClasspathHelper.forPackage;

/**
 * <h1>Сборщик страниц</h1>
 */
@Slf4j
public abstract class Constructor {

    /**
     * Находит в переданном пакете все классы, обладающие аннотацией
     * {@link Name} и наследующиеся от {@link Page}, инициализирует
     * их поля и добавляет в хранилище {@link Pages}.
     *
     * @param packet пакет, в котором находятся классы
     */
    public static void initPages(String packet) {
        ConfigurationBuilder configurationBuilder = new ConfigurationBuilder().setUrls(forPackage(packet));
        Reflections reflections = new Reflections(configurationBuilder);

        Pages.pages.putAll(reflections.getTypesAnnotatedWith(Name.class).stream()
                .filter(Page.class::isAssignableFrom).map(PageClass::new)
                .collect(toMap(PageClass::getPageName, PageClass::init)));
    }

    /**
     * <h1>Класс-инициализор {@link Page} класса</h1>
     */
    @AllArgsConstructor(access = PRIVATE)
    @FieldDefaults(level = PRIVATE, makeFinal = true)
    private static class PageClass {

        Class<?> pageClass;

        /**
         * Создает экземпляр класса, переданный при
         * инициализации {@link PageClass}.
         *
         * @return инициализированная
         * страница типа {@link Page}
         */
        public Page init() {
            Page page;
            try {
                page = pageClass.asSubclass(Page.class)
                        .getDeclaredConstructor().newInstance();
            } catch (InstantiationException ex) {
                throw new ClassInitializationError(ex, pageClass);
            } catch (IllegalAccessException ex) {
                throw new ClassInitializationError(ex, pageClass);
            } catch (InvocationTargetException ex) {
                throw new ClassInitializationError(ex, pageClass);
            } catch (NoSuchMethodException ex) {
                throw new ClassInitializationError(ex, pageClass);
            }
            page.addElements(getPageElement(page));
            return page;
        }

        /**
         * Возвращает имя страницы, указанное в аннотации {@link Name}.
         *
         * @return имя страницы, указанное в аннотации {@link Page}
         */
        @Nonnull
        private String getPageName() {
            return pageClass.getAnnotation(Name.class).value();
        }

        /**
         * Возвращает список полец класса, наследованных от {@link Element}.
         *
         * @param page инициализированный класс {@link Page}
         *
         * @return список полей класса, наследованных от {@link Element}.
         */
        private List<Element> getPageElement(Page page) {
            return stream(pageClass.getDeclaredFields()).filter(field ->
                    Element.class.isAssignableFrom(field.getType())).map(field -> {
                field.setAccessible(true);
                try {
                    return (Element) field.get(page);
                } catch (IllegalAccessException ex) {
                    throw new UnavailableFieldError(field, page.getClass());
                }
            }).collect(toList());
        }
    }
}
