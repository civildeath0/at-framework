package ru.sberbank.smartoffice.at.steps.browser;

import com.browserup.harreader.model.HarEntry;
import com.browserup.harreader.model.HttpMethod;
import io.cucumber.java.ru.И;
import lombok.experimental.FieldDefaults;
import org.apache.hc.core5.http.NameValuePair;
import ru.sberbank.smartoffice.at.archivist.object.Angler;
import ru.sberbank.smartoffice.at.errors.TestError;
import ru.sberbank.smartoffice.at.utilities.Delayer;

import java.util.List;
import java.util.function.Supplier;

import static com.browserup.harreader.model.HttpMethod.GET;
import static lombok.AccessLevel.PRIVATE;
import static ru.sberbank.smartoffice.at.errors.TestErrorMessage.INVALID_FILTERING;

@FieldDefaults(level = PRIVATE, makeFinal = true)
public final class AnglerSteps {

    @И("был совершен {method} запрос на адрес {string}")
    public void searchRequest(HttpMethod method, String path) {
        searchRequest(() -> new Angler().searchEntry(method, path));
    }

    @И("был совершен {method} запрос на адрес {string}, со статусом {int}")
    public void searchRequest(HttpMethod method, String path, int status) {
        searchRequest(() -> new Angler().searchEntry(method, path, status));
    }

    @И("был совершен Query запрос на адрес {string}")
    public void searchRequest(String path, List<NameValuePair> params) {
        searchRequest(() -> new Angler().searchEntry(GET, path, params));
    }

    @И("был совершен Query запрос на адрес {string}, со статусом {int}")
    public void searchRequest(String path, int status, List<NameValuePair> params) {
        searchRequest(() -> new Angler().searchEntry(GET, path, params, status));
    }

    @И("очищаю консоль браузера")
    public void clearBrowserConsole() {
        new Angler().clearEntries();
    }

    private void searchRequest(Supplier<HarEntry> supplier) {
        try {
            Delayer.waitValue(supplier);
        } catch (Throwable throwable) {
            throw new TestError(INVALID_FILTERING, throwable);
        }
    }
}
