package ru.sberbank.smartoffice.at.steps.types;

import io.cucumber.java.ParameterType;
import ru.sberbank.smartoffice.at.users.*;

import java.util.NoSuchElementException;
import java.util.function.Predicate;
import java.util.stream.Stream;

import static java.lang.String.format;

public final class UserType {

    @ParameterType("\"([а-яА-Я]+) ([а-яА-Я]+) ([а-яА-Я]+)\"")
    public SmartChief chief(String lastName, String firstName, String middleName) {
        return find(SmartUsers.chiefsStream(), firstName, lastName, middleName);
    }

    @ParameterType("\"([а-яА-Я]+) ([а-яА-Я]+) ([а-яА-Я]+)\"")
    public SmartAssistant assistant(String lastName, String firstName, String middleName) {
        return find(SmartUsers.assistantsStream(), firstName, lastName, middleName);
    }

    @ParameterType("\"([а-яА-Я]+) ([а-яА-Я]+) ([а-яА-Я]+)\"")
    public SmartAdmin admin(String lastName, String firstName, String middleName) {
        return find(SmartUsers.administratorsStream(), firstName, lastName, middleName);
    }

    private <T extends SmartUser> T find(Stream<T> stream, String firstName, String lastName, String middleName) {
        return stream.filter(searchedUser(firstName, lastName, middleName)).findFirst().orElseThrow(() -> {
            throw new NoSuchElementException(format("Пользователь \"%s %s %s\" не найден",
                    lastName, firstName, middleName));
        });
    }

    private Predicate<? super SmartUser> searchedUser(String firstName, String lastName, String middleName) {
        return user -> firstName.startsWith(user.getFirstName())
                && middleName.startsWith(user.getMiddleName())
                && lastName.startsWith(user.getLastName());
    }
}
