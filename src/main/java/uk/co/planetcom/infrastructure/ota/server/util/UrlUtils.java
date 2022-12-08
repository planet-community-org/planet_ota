package uk.co.planetcom.infrastructure.ota.server.utils;

import java.net.MalformedURLException;
import java.net.URL;
import java.nio.file.Paths;

public class UrlUtils {
    private UrlUtils() {}
    
    public static String getUrlFileName(String url) {
        try {
            return Paths.get(new URL(url).getPath()).getFileName().toString();
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }
    }
}