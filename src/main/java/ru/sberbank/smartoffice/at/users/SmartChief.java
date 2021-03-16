package ru.sberbank.smartoffice.at.users;


import lombok.ToString;
import lombok.experimental.FieldDefaults;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

import static lombok.AccessLevel.PRIVATE;

/**
 * <h1>Репрезентация руководителя в системе SmartOffice</h1>
 */
@FieldDefaults(
        makeFinal = true,
        level = PRIVATE
)
@ToString(callSuper = true)
public final class SmartChief extends SmartUser {

    List<SmartAssistant> smartAssistants;

    public SmartChief(String firstName, String lastName, String middleName, String roleName,
                      String id, String login, String password, List<SmartAssistant> smartAssistants) {
        super(firstName, lastName, middleName, roleName, id, login, password);
        this.smartAssistants = smartAssistants;
    }

    @NotNull
    @Contract(" -> new")
    public static ChiefBuilder builder() {
        return new ChiefBuilder();
    }

    /**
     * Добавляет переданного помощника в спсок
     * помощников текущего реководителя.
     *
     * @param smartAssistant добавляемый помощник
     */
    void addAssistant(SmartAssistant smartAssistant) {
        this.smartAssistants.add(smartAssistant);
    }

    public static final class ChiefBuilder extends UserBuilder<SmartChief, ChiefBuilder> {
        private final List<SmartAssistant> smartAssistants;

        private ChiefBuilder() {
            smartAssistants = new ArrayList<>();
        }

        @Override
        protected ChiefBuilder self() {
            return this;
        }

        @NotNull
        @Override
        @Contract(" -> new")
        public SmartChief build() {
            return new SmartChief(firstName, lastName, middleName, roleName, id, login, password, smartAssistants);
        }
    }
}
