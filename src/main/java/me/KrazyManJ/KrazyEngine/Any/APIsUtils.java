package me.KrazyManJ.KrazyEngine.Any;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

@SuppressWarnings("unused")
public final class APIsUtils {
    public static JsonElement fetchJson(String link) {
        try {
            URL url = new URL(link);
            URLConnection request = url.openConnection();
            request.connect();
            return new JsonParser().parse(new InputStreamReader((InputStream) request.getContent()));
        }
        catch (IOException e) {
            e.printStackTrace();
            return new JsonObject();
        }
    }
}