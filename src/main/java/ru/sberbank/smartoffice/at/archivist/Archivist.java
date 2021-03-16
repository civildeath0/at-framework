package ru.sberbank.smartoffice.at.archivist;

import com.browserup.harreader.model.HarEntry;
import com.browserup.harreader.model.HttpMethod;
import org.apache.hc.core5.http.NameValuePair;

import java.util.List;

@SuppressWarnings({"UnusedReturnValue", "unused"})
public interface Archivist {

    HarEntry searchEntry(HttpMethod method, String path);

    HarEntry searchEntry(HttpMethod method, String path, List<NameValuePair> params);

    HarEntry searchEntry(HttpMethod method, String path, List<NameValuePair> params, int status);

    HarEntry searchEntry(HttpMethod method, String path, int status);

    void clearEntries();
}
