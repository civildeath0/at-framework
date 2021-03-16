package ru.sberbank.smartoffice.at.users;

import io.restassured.http.Cookie;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.FieldDefaults;
import lombok.experimental.NonFinal;

import static lombok.AccessLevel.PRIVATE;

/**
 * <h1>Абстрактная репрезентация пользователя</h1>
 */
@Getter
@ToString
@FieldDefaults(
        makeFinal = true,
        level = PRIVATE
)
@RequiredArgsConstructor
public abstract class SmartUser {

    String firstName;
    String lastName;
    String middleName;
    String roleName;
    String id;
    String login;
    String password;
    @Setter @NonFinal
    Cookie cookie;

    /**
     * <h1>Строитель класса {@link SmartUser}</h1>
     *
     * @param <T> тип класса, наследующий {@link SmartUser}
     * @param <S> тип строителя, наследующий {@link UserBuilder}
     */
    protected static abstract class UserBuilder<T extends SmartUser, S extends UserBuilder<T, S>> {

        protected String firstName;
        protected String lastName;
        protected String middleName;
        protected String roleName;
        protected String id;
        protected String login;
        protected String password;

        /**
         * Должен быть переопределен в наследуемом строителе и возвращать
         * себя, для исключения конфликта при приведении типов.
         *
         * @return <code>this</code>
         */
        protected abstract S self();

        public S firstName(String firstName) {
            this.firstName = firstName;
            return self();
        }

        public S lastName(String lastName) {
            this.lastName = lastName;
            return self();
        }

        public S middleName(String middleName) {
            this.middleName = middleName;
            return self();
        }

        public S roleName(String roleName) {
            this.roleName = roleName;
            return self();
        }

        public S id(String id) {
            this.id = id;
            return self();
        }

        public S login(String login) {
            this.login = login;
            return self();
        }

        public S password(String password) {
            this.password = password;
            return self();
        }

        public abstract T build();
    }
}
