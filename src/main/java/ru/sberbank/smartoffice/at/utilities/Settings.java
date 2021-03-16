package ru.sberbank.smartoffice.at.utilities;

import com.codeborne.selenide.Configuration;
import io.qameta.allure.selenide.AllureSelenide;
import io.restassured.RestAssured;
import lombok.Synchronized;
import lombok.extern.slf4j.Slf4j;

import static com.codeborne.selenide.logevents.SelenideLogger.addListener;
import static io.restassured.parsing.Parser.JSON;
import static ru.sberbank.smartoffice.at.pageobjects.core.page.Constructor.initPages;

/**
 * <h1>Статичные настройки проекта</h1>
 */
@Slf4j
public abstract class Settings {

    private static boolean isConfigured = false;

    /**
     * Выставляет базовые настройки проекта.
     */
    public static void setBasicSettings() {
        if (!isConfigured) {
            configure();
        }
    }

    /**
     * {@link Synchronized} метод, выставляющий
     * глобальные настройки один раз.
     */
    @Synchronized
    private static void configure() {
        if (!isConfigured) {
            setConfigureOnce();
        }
    }

    /**
     * Непосредственно отвечает за
     * выставление первоначальных настроек.
     */
    private static void setConfigureOnce() {

        AllureSelenide allureSelenide = new AllureSelenide()
                .screenshots(true)
                .savePageSource(false)
                .includeSelenideSteps(false);

        RestAssured.defaultParser = JSON;
        log.info("RestAssured парсер по умолчанию установлен на JSON");

        Configuration.fastSetValue = false;
        log.info("Установка значений в Selenide не будет осуществляться через JS");

        Configuration.proxyEnabled = true;
        log.info("Включен proxy для Selenide");

        initPages("ru.sberbank.smartoffice.at.pageobjects.classes");
        log.info("Страницы проинициализированы");

        addListener("AllureSelenide", allureSelenide);
        log.info("Добавлен Allure Selenide слушатель");

        isConfigured = true;
    }
}
