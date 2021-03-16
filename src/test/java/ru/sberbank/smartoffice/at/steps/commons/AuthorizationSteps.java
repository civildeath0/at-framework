package ru.sberbank.smartoffice.at.steps.commons;


import io.cucumber.java.ru.Дано;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import ru.sberbank.smartoffice.at.pageobjects.classes.AuthorizationPage;
import ru.sberbank.smartoffice.at.users.SmartAdmin;
import ru.sberbank.smartoffice.at.users.SmartAssistant;
import ru.sberbank.smartoffice.at.users.SmartChief;
import ru.sberbank.smartoffice.at.users.SmartUser;
import ru.sberbank.smartoffice.at.utilities.Proxer;
import ru.sberbank.smartoffice.at.world.World;

import static com.codeborne.selenide.Selenide.open;
import static lombok.AccessLevel.PRIVATE;

@Slf4j @AllArgsConstructor
@FieldDefaults(level = PRIVATE, makeFinal = true)
public final class AuthorizationSteps {

    World world;

    @Дано("я авторизовался как помощник {assistant}")
    public void loginAsAssistant(SmartAssistant assistant) {
        auth(assistant);
    }

    @Дано("я авторизовался как руководитель {chief}")
    public void loginAsChief(SmartChief chief) {
        auth(chief);
    }

    @Дано("я авторизовался как администратор {admin}")
    public void loginAsAdmin(SmartAdmin admin) {
        auth(admin);
    }

    private void auth(SmartUser user) {
        open("/login", AuthorizationPage.class).selectPort(17443)
                .enterLogin(user).enterPassword(user).submit();
        world.setCurrentUser(user);
        Proxer.startProxy();
    }
}
