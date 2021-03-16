package ru.sberbank.smartoffice.at.hooks;


import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.WebDriverRunner;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import ru.sberbank.smartoffice.at.visitors.changer.objects.EntityDeleter;
import ru.sberbank.smartoffice.at.world.World;

import static lombok.AccessLevel.PRIVATE;

/**
 * <h1>Класс, содержащий методы, которые вызываются
 * до и после каждого сценария</h1>
 * <p>
 * {@link Before @Before(order = int)} : Это выполняется в
 * порядке увеличения, что означает, что значение 0 будет
 * запускаться первым, а 1 будет после 0.
 * <p>
 * {@link After @After(order = int)} : Это выполняется в
 * порядке убывания, что означает, что начение 1 будет
 * запускаться первым, а 0 будет после 1.
 */
@Slf4j @RequiredArgsConstructor
@FieldDefaults(level = PRIVATE, makeFinal = true)
public final class Hooks {

    World world;

    /**
     * Вызывается перед каждым сценарием.
     * <p>
     * Записывает в логи информацию о начавшемся сценарии.
     *
     * @param scenario текущий сценарий вида {@link Scenario}
     */
    @Before(order = 0)
    public void startScenario(Scenario scenario) {
        log.debug("Начался сценарий: {}", scenario.getName());
    }

    /**
     * Запускается после каждого сценария.
     * <p>
     * Записывает в логи информацию о завершившимся
     * сценарии, закрывает браузер, очищает
     * cookies и хранилище браузера.
     *
     * @param scenario завершившийся сценарий вида {@link Scenario}
     */
    @After(order = 100)
    public void stopScenario(Scenario scenario) {
        if (WebDriverRunner.hasWebDriverStarted()) {
            Selenide.clearBrowserLocalStorage();
            Selenide.clearBrowserCookies();
            Selenide.closeWebDriver();
        }
        log.debug("Закончился сценарий: {}", scenario.getName());
    }

    /**
     * Запускается после каждого сценария.
     * <p>
     * Отправляет запросы на удаления, для каждой
     * сущности в списке, полученного методом {@link World#getCreatedEntities()}.
     */
    @After(order = 10)
    public void clearEntities() {
        EntityDeleter entityDeleter = new EntityDeleter();
        world.getCreatedEntities().forEach(entityDeleter::manage);
    }
}
