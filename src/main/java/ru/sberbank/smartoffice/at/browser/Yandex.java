package ru.sberbank.smartoffice.at.browser;

import com.codeborne.selenide.WebDriverProvider;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import ru.sberbank.smartoffice.at.errors.CriticalError;

import javax.annotation.Nonnull;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

/**
 * <h1>Базовый настройки для запуска Яндекс.Браузера
 * через Selenide</h1>
 */
public class Yandex implements WebDriverProvider {

    /**
     * Создает драйвер, который ипользуется Selenide
     *
     * @param capabilities набор пар ключ-значение, закодированных как объект JSON
     *
     * @return экземпляр {@link WebDriver}
     */
    @Nonnull
    @Override
    public final WebDriver createDriver(@Nonnull DesiredCapabilities capabilities) {
        String operatingSystemName = System.getProperty("os.name");
        if (operatingSystemName.equals("Mac OS X"))
            return setBrowserProperties("macDriver", "macBrowser", capabilities);
        else if (operatingSystemName.contains("Windows"))
            return setBrowserProperties("windowsDriver", "windowsBrowser", capabilities);
        else
            throw new CriticalError("Операционная система \"" + operatingSystemName + "\" не поддерживается");
    }

    /**
     * Устанавливает свойства, определенные в файле browser.property
     *
     * @param driverProperty  имя свойства, хранящее в себе путь до драйвера
     * @param browserProperty имя свойства, хранящее в себе путь до браузера
     * @param capabilities    набор пар ключ-значение, закодированных как объект JSON
     */
    @Nonnull
    private WebDriver setBrowserProperties(String driverProperty, String browserProperty,
                                           DesiredCapabilities capabilities) {
        Properties properties = new Properties();
        File file = new File("src/main/resources/browser.properties");
        try (FileInputStream inputStream = new FileInputStream(file)) {
            properties.load(inputStream);
        } catch (FileNotFoundException e) {
            throw new CriticalError("Файл со свойствами браузера не существует", e);
        } catch (IOException e) {
            throw new CriticalError("Не удалось прочитать файл со свойствами браузера", e);
        }
        String pathToBrowser = properties.getProperty(browserProperty);
        String pathToDriver = properties.getProperty(driverProperty);

        if (pathToDriver == null && pathToBrowser == null) {
            throw new CriticalError("В файле browser.properties отсутсвуют обязательные свойства \"" +
                    driverProperty + "\" и \"" + browserProperty + '\"');
        } else if (pathToDriver == null || pathToBrowser == null) {
            throw new CriticalError("В файле browser.properties отсутствует обязательное свойство \"" +
                    (pathToDriver == null ? driverProperty : browserProperty) + "\"");
        }
        return setDriver(pathToDriver, pathToBrowser, capabilities);
    }

    /**
     * Присваивает переменной driver экземпляр класса {@link ChromeDriver} и устанавливает ему
     * необходимые свойства, а именно: путь до драйвера и путь до браузера
     *
     * @param pathToDriver  путь до драйвера
     * @param pathToBrowser путь до браузера
     * @param capabilities  набор пар ключ-значение, закодированных как объект JSON
     */
    @Nonnull
    private WebDriver setDriver(String pathToDriver, String pathToBrowser, DesiredCapabilities capabilities) {
        try {
            System.setProperty("webdriver.chrome.driver", pathToDriver);
        } catch (SecurityException ex) {
            throw new CriticalError("Недостаточно прав для чтения файла драйвера", ex);
        }

        ChromeOptions options = new ChromeOptions()
                .addArguments("disable-popup-blocking", "browser-test", "disable-infobars",
                        "disable-extensions", "disable-default-apps", "no-default-browser-check",
                        "safebrowsing-disable-auto-update", "disable-notifications",
                        "disable-translate", "enable-automation")
                .setBinary(pathToBrowser)
                .merge(capabilities);
        return new ChromeDriver(options);
    }
}
