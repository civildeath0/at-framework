package ru.sberbank.smartoffice.at.entities;

import ru.sberbank.smartoffice.at.entities.nested.User;

public interface CreatedEntity extends Controllability {

    User getAuthor();

    String getId();
}
