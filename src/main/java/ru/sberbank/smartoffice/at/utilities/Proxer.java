package ru.sberbank.smartoffice.at.utilities;

import com.browserup.bup.BrowserUpProxy;
import com.codeborne.selenide.WebDriverRunner;
import lombok.extern.slf4j.Slf4j;

import static com.browserup.bup.proxy.CaptureType.getAllContentCaptureTypes;

@Slf4j
@SuppressWarnings("SpellCheckingInspection")
public abstract class Proxer {

    public static void startProxy() {
        BrowserUpProxy proxy = WebDriverRunner.getSelenideProxy().getProxy();

        proxy.setHarCaptureTypes(getAllContentCaptureTypes());
        log.info("Proxy будет отлавливать весь контект");

        proxy.enableHarCaptureTypes(getAllContentCaptureTypes());
        log.info("Proxy будет отлавливать и запрос и ответ");

        proxy.newHar("web");
        log.info("Создан новый HAR для записи");
    }
}
