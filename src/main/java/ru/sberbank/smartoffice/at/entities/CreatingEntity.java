package ru.sberbank.smartoffice.at.entities;

import ru.sberbank.smartoffice.at.visitors.creator.Creator;

public interface CreatingEntity {

    void accept(Creator creator);
}
