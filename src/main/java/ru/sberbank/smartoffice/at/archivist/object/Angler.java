package ru.sberbank.smartoffice.at.archivist.object;

import com.browserup.harreader.model.HarEntry;
import com.browserup.harreader.model.HttpMethod;
import com.codeborne.selenide.Condition;
import com.codeborne.selenide.WebDriverRunner;
import org.apache.hc.core5.http.NameValuePair;
import ru.sberbank.smartoffice.at.archivist.Archivist;
import ru.sberbank.smartoffice.at.archivist.errors.EmptyCatch;

import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;

import static ru.sberbank.smartoffice.at.utilities.QueryURLShaper.formQueryURL;

public class Angler implements Archivist {

    @Override
    public HarEntry searchEntry(HttpMethod method, String path) {
        return searchEntry(filterByMethod(method).and(filterByURL(path))).orElseThrow(() -> {
            throw new EmptyCatch(method, path);
        });
    }

    @Override
    public HarEntry searchEntry(HttpMethod method, String path, int status) {
        return searchEntry(filterByMethod(method)
                .and(filterByURL(path)).and(filterByStatus(status))).orElseThrow(() -> {
            throw new EmptyCatch(method, path, status);
        });
    }

    @Override
    public HarEntry searchEntry(HttpMethod method, String path, List<NameValuePair> params) {
        return searchEntry(method, formQueryURL(path, params));
    }

    @Override
    public HarEntry searchEntry(HttpMethod method, String path, List<NameValuePair> params, int status) {
        return searchEntry(method, formQueryURL(path, params), status);
    }

    @Override
    public void clearEntries() {
        getEntries().clear();
    }

    private Optional<HarEntry> searchEntry(Predicate<HarEntry> predicate) {
        return getEntries().stream().filter(predicate).findFirst();
    }

    private Predicate<HarEntry> filterByMethod(HttpMethod method) {
        return entry -> entry.getRequest().getMethod() == method;
    }

    private Predicate<HarEntry> filterByStatus(int status) {
        return entry -> entry.getResponse().getStatus() == status;
    }

    private Predicate<HarEntry> filterByURL(String path) {
        return entry -> entry.getRequest().getUrl().endsWith(path);
    }

    private List<HarEntry> getEntries() {
        return WebDriverRunner.getSelenideProxy().getProxy()
                .getHar().getLog().getEntries();
    }
}
