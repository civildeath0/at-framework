package ru.sberbank.smartoffice.at.users.userparsers;


import ru.sberbank.smartoffice.at.users.SmartAdmin;
import ru.sberbank.smartoffice.at.users.csvreader.UserParser;

import java.util.ArrayList;
import java.util.List;

import static ru.sberbank.smartoffice.at.users.SmartAdmin.builder;

/**
 * <h1>Парсит администраторов</h1>
 */
public final class AdminParser implements UserParser<SmartAdmin> {

    private final List<SmartAdmin> smartAdmins;

    public AdminParser() {
        smartAdmins = new ArrayList<>();
    }

    @Override
    public void parseLine(String[] line) {
        smartAdmins.add(
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
    public List<SmartAdmin> getCollected() {
        return smartAdmins;
    }
}
