package me.KrazyManJ.KrazyEngine.BungeeCord.File.Config;

import net.md_5.bungee.config.Configuration;

import java.lang.reflect.ParameterizedType;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@SuppressWarnings({"unused", "unchecked"})
public final class BungeeConfigKey<T> {

    private final Class<T> T = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];

    private static final Map<Class<?>, BungeeConfigUtilizer<?>> utilizers = new HashMap<>();
    private static final BungeeConfigUtilizer<?> DEFAULT_UTILIZER = Configuration::get;

    static {
        //Numbers
        utilizers.put(Integer.class, Configuration::getInt);
        utilizers.put(Float.class, Configuration::getFloat);
        utilizers.put(Short.class, Configuration::getShort);
        utilizers.put(Long.class, Configuration::getLong);
        utilizers.put(Double.class, Configuration::getDouble);

        //String
        utilizers.put(Character.class, Configuration::getChar);
        utilizers.put(String.class, Configuration::getString);

        //Other
        utilizers.put(Boolean.class, Configuration::getBoolean);
        utilizers.put(List.class, Configuration::getList);
        utilizers.put(Configuration.class, Configuration::getSection);
    }

    private final String path;

    public BungeeConfigKey(String path) {
        this.path = path;
    }

    public T getFrom(Configuration configuration) {
        return (T) utilizers.getOrDefault(T, DEFAULT_UTILIZER).utilize(configuration, path);
    }

    public T getFrom(Configuration configuration, T defaultVal) {
        T val = getFrom(configuration);
        return val == null ? defaultVal : val;
    }

    public T getFrom(BungeeConfig config) {
        return getFrom(config.get());
    }

    public T getFrom(BungeeConfig config, T defaultVar) {
        return getFrom(config.get(), defaultVar);
    }
}
