package ru.sberbank.smartoffice.at.users;

import lombok.EqualsAndHashCode;
import lombok.ToString;
import lombok.Value;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

/**
 * <h1>Репрезентация помощника в системе SmartOffice</h1>
 */
@Value
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class SmartAssistant extends SmartUser {

    String chiefId;

    public SmartAssistant(String firstName, String lastName, String middleName, String roleName,
                          String id, String login, String password, String chiefId) {
        super(firstName, lastName, middleName, roleName, id, login, password);
        this.chiefId = chiefId;
    }

    @NotNull
    @Contract(" -> new")
    public static AssistantBuilder builder() {
        return new AssistantBuilder();
    }

    public static class AssistantBuilder extends UserBuilder<SmartAssistant, AssistantBuilder> {
        private String chiefLogin;

        @Override
        protected AssistantBuilder self() {
            return this;
        }

        @Override
        public SmartAssistant build() {
            return new SmartAssistant(firstName, lastName, middleName, roleName, id, login, password, chiefLogin);
        }

        public AssistantBuilder chiefId(String chiefLogin) {
            this.chiefLogin = chiefLogin;
            return this;
        }
    }
}
