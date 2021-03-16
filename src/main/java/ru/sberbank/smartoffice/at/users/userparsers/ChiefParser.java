package ru.sberbank.smartoffice.at.users.userparsers;

import org.jetbrains.annotations.NotNull;
import ru.sberbank.smartoffice.at.users.SmartChief;
import ru.sberbank.smartoffice.at.users.csvreader.UserParser;

import java.util.ArrayList;
import java.util.List;

import static ru.sberbank.smartoffice.at.users.SmartChief.builder;

/**
 * <h1>Парсит руководителей</h1>
 */
public final class ChiefParser implements UserParser<SmartChief> {

    private final List<SmartChief> smartChiefs;

    public ChiefParser() {
        smartChiefs = new ArrayList<>();
    }

    @Override
    public void parseLine(@NotNull String[] line) {
        smartChiefs.add(
                builder()
                        .firstName(line[0])
                        .lastName(line[1])
                        .middleName(line[2])
                        .roleName(line[3])
                        .id(line[4])
                        .login(line[5])
                        .password(line[6])
                        .build()
        );
    }

    @Override
    public List<SmartChief> getCollected() {
        return smartChiefs;
    }
}
