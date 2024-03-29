package uk.co.planetcom.infrastructure.ota.server.utils;

import java.net.MalformedURLException;
import java.net.URL;
import java.nio.file.Paths;

public final class UrlUtils {
    public static String getUrlFileName(final String url) throws MalformedURLException {
        try {
            return Paths.get(new URL(url).getPath()).getFileName().toString();
        } catch (final MalformedURLException e) {
            throw new MalformedURLException("Error getting filename from path.");
        }
    }
}
