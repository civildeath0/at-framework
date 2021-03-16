package ru.sberbank.smartoffice.at.annotations;

import lombok.NonNull;

import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE})
@Inherited
public @interface Name {
    @NonNull String value();
}
