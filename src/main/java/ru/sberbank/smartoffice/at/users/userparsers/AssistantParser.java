package ru.sberbank.smartoffice.at.users.userparsers;


import ru.sberbank.smartoffice.at.users.SmartAssistant;
import ru.sberbank.smartoffice.at.users.csvreader.UserParser;

import java.util.ArrayList;
import java.util.List;

import static ru.sberbank.smartoffice.at.users.SmartAssistant.builder;

/**
 * <h1>Парсит помощников</h1>
 */
public final class AssistantParser implements UserParser<SmartAssistant> {

    private final List<SmartAssistant> smartAssistants;

    public AssistantParser() {
        smartAssistants = new ArrayList<>();
    }

    @Override
    public void parseLine(String[] line) {
        smartAssistants.add(
                builder()
                        .firstName(line[0])
                        .lastName(line[1])
                        .middleName(line[2])
                        .roleName(line[3])
                        .id(line[4])
                        .login(line[5])
                        .password(line[6])
                        .chiefId(line[7])
                        .build()
        );
    }

    @Override
    public List<SmartAssistant> getCollected() {
        return smartAssistants;
    }
}
