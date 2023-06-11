package me.KrazyManJ.KrazyEngine.Any;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.stream.JsonReader;
import net.md_5.bungee.api.plugin.Plugin;

import java.io.*;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.ParameterizedType;

/**
 * This class loads and saves json file. It uses package by google, so it can handle any kind of class.
 * <p>
 * Data structure of this json file is specified by generic parameter.
 * I recommend to define its params with public attributes and with default values, for initialization.
 * </p>
 * <p>Here is an example of data structure class:</p>
 * <pre>
 * public class DataStructure {
 *     public bool active = false;
 *     public String lastUsername = "KrazyManJ";
 * }
 * </pre>
 *
 * @param <T> Data structure of json data
 * @author KrazyManJ
 */
@SuppressWarnings({"unchecked", "ResultOfMethodCallIgnored", "unused"})
public final class JsonFile<T> {

    private static final Gson gson = new GsonBuilder().setPrettyPrinting().create();

    private final Class<T> T = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];

    private final File file;
    private T data;

    public JsonFile(File file) {
        this.file = file;
        load();
    }

    public static <T> JsonFile<T> defaultDataFile(File dataFolder){
        return new JsonFile<>(new File(dataFolder, "data.json"));
    }

    /**
     * Load data from json file. If json file do not exist, it uses default data specified by generic class.
     */
    public void load() {
        try {
            data = gson.fromJson(new JsonReader(new FileReader(file)), T);
        } catch (FileNotFoundException e) {
            try {
                data = T.getDeclaredConstructor().newInstance();
            } catch (InstantiationException | IllegalAccessException | InvocationTargetException |
                     NoSuchMethodException ex) {
                throw new RuntimeException(ex);
            }
        }
    }

    /**
     * Get data in type of data structure specified by generic parameter
     *
     * @return dat in structure specified by generic parameter
     */
    public T getData() {
        return data;
    }

    /**
     * Saves data into json file
     */
    public void save() {
        if (!file.getParentFile().exists()) file.getParentFile().mkdirs();
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        try {
            FileWriter w = new FileWriter(file);
            w.write(gson.toJson(data));
            w.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
