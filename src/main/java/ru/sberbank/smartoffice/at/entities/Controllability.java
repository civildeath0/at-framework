package ru.sberbank.smartoffice.at.entities;


import ru.sberbank.smartoffice.at.visitors.changer.Changer;

public interface Controllability {

    void accept(Changer changer);
}
