package ru.sberbank.smartoffice.at.users;

import lombok.EqualsAndHashCode;
import lombok.ToString;
import lombok.Value;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

/**
 * <h1>Репрезентация администратора в системе SmartOffice</h1>
 */
@Value
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class SmartAdmin extends SmartUser {

    public SmartAdmin(String firstName, String lastName, String middleName, String roleName,
                      String id, String login, String password) {
        super(firstName, lastName, middleName, roleName, id, login, password);
    }

    @NotNull
    @Contract(" -> new")
    public static AdministratorBuilder builder() {
        return new AdministratorBuilder();
    }

    public static class AdministratorBuilder extends UserBuilder<SmartAdmin, AdministratorBuilder> {

        @Override
        protected AdministratorBuilder self() {
            return this;
        }

        @Override
        public SmartAdmin build() {
            return new SmartAdmin(firstName, lastName, middleName, roleName, id, login, password);
        }
    }
}
