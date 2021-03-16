package ru.sberbank.smartoffice.at.pageobjects.classes;

import lombok.Value;
import ru.sberbank.smartoffice.at.pageelements.fields.PlaceholderField;
import ru.sberbank.smartoffice.at.users.SmartUser;

import static com.codeborne.selenide.Condition.enabled;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$x;
import static java.lang.String.format;

/**
 * <h1>
 * Репрезентация страницы
 * <a href=https://ift2-app2.smartoffice.sberbank.ru:15443/login>"Авторизация"</a>
 * SmartOffice
 * </h1>
 */
@Value
public class AuthorizationPage {

    PlaceholderField login;
    PlaceholderField password;

    public AuthorizationPage() {
        login = new PlaceholderField("Логин");
        password = new PlaceholderField("Пароль");
    }

    /**
     * Вводит логин пользователя в поле логина.
     *
     * @param user пользователь из под которого просходит авторизация
     *
     * @return this
     */
    public AuthorizationPage enterLogin(SmartUser user) {
        login.sendKeys(user.getLogin());
        return this;
    }

    /**
     * Вводит пароль пользователя в поле пароля.
     *
     * @param user пользователь из под которого происходит авторизация
     *
     * @return this
     */
    public AuthorizationPage enterPassword(SmartUser user) {
        password.sendKeys(user.getPassword());
        return this;
    }

    /**
     * Выбирает порт на странице.
     *
     * @param port номер выбираемого порта
     *
     * @return this
     */
    public AuthorizationPage selectPort(int port) {
        $("div[class*=select][class*=value]")
                .click();
        $x(format("//li[contains(text(), '%d')]", port))
                .click();
        return this;
    }

    /**
     * Нажимает кнопку войти.
     *
     * @return this
     */
    public AuthorizationPage submit() {
        $("button[type=submit]")
                .shouldBe(enabled).click();
        return this;
    }
}


//ИЗМЕНИТСЯ И ПЕРЕЕДЕТ