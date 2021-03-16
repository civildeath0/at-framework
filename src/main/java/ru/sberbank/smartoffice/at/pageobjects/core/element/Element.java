package ru.sberbank.smartoffice.at.pageobjects.core.element;

import com.codeborne.selenide.SelenideElement;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NonNull;
import lombok.experimental.FieldDefaults;
import ru.sberbank.smartoffice.at.pageobjects.core.errors.ElementCastError;

import static lombok.AccessLevel.PRIVATE;

/**
 * <h1>Репрезентация элемента страницы</h1>
 */
@Getter @AllArgsConstructor
@FieldDefaults(level = PRIVATE, makeFinal = true)
public abstract class Element {

    @NonNull String name;
    @NonNull SelenideElement element;

    /**
     * Кастует текущий класс к переданному.
     *
     * @param aClass класс, к которому кастуется текущий
     * @param <T>    тип класс, к которому происходит каст
     *
     * @return текущий класс, кастанутый к переданному
     */
    public final <T> T as(Class<T> aClass) {
        if (aClass.isAssignableFrom(this.getClass())) {
            return aClass.cast(this);
        }
        throw new ElementCastError(this.getClass(), aClass);
    }
}
