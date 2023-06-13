package me.KrazyManJ.KrazyEngine.Any;

import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.stream.Collectors;

@SuppressWarnings("unused")
public final class APIFetcher {

    private APIFetcher() {
    }

    public static JsonElement fetchJson(String url) {
        try {
            URL u = new URL(url);
            URLConnection request = u.openConnection();
            request.connect();
            return JsonParser.parseReader(new InputStreamReader((InputStream) request.getContent()));
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static String fetchString(String url) {
        try {
            URL u = new URL(url);
            InputStream in = u.openStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(in));
            return reader.lines().collect(Collectors.joining(System.lineSeparator()));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
