package ru.sberbank.smartoffice.at.utilities;

import org.apache.hc.core5.http.NameValuePair;
import org.apache.hc.core5.http.message.BasicNameValuePair;
import org.apache.hc.core5.net.URLEncodedUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static java.nio.charset.StandardCharsets.UTF_8;

public abstract class QueryURLShaper {

    public static String formQueryURL(String path, List<NameValuePair> params) {
        return String.format("%s?%s", path, URLEncodedUtils.format(params, UTF_8));
    }

    public static String formQueryURL(String path, Map<String, String> params) {
        return formQueryURL(path, new ArrayList<>() {{
            for (Map.Entry<String, String> entry : params.entrySet()) {
                add(new BasicNameValuePair(entry.getKey(), entry.getValue()));
            }
        }});
    }
}
