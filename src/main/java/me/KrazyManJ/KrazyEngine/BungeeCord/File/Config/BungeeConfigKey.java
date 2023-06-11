package me.KrazyManJ.KrazyEngine.BungeeCord.File.Config;

import net.md_5.bungee.config.Configuration;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import java.util.List;


@SuppressWarnings({"unused"})
public final class BungeeConfigKey<T> {

    @Contract("_ -> new")
    public static @NotNull BungeeConfigKey<Integer> getInt(String path) {
        return new BungeeConfigKey<>(path, Configuration::getInt);
    }

    @Contract("_ -> new")
    public static @NotNull BungeeConfigKey<Float> getFloat(String path) {
        return new BungeeConfigKey<>(path, Configuration::getFloat);
    }

    @Contract("_ -> new")
    public static @NotNull BungeeConfigKey<Short> getShort(String path) {
        return new BungeeConfigKey<>(path, Configuration::getShort);
    }

    @Contract("_ -> new")
    public static @NotNull BungeeConfigKey<Long> getLong(String path) {
        return new BungeeConfigKey<>(path, Configuration::getLong);
    }

    @Contract("_ -> new")
    public static @NotNull BungeeConfigKey<Double> getDouble(String path) {
        return new BungeeConfigKey<>(path, Configuration::getDouble);
    }

    @Contract("_ -> new")
    public static @NotNull BungeeConfigKey<Byte> getByte(String path) {
        return new BungeeConfigKey<>(path, Configuration::getByte);
    }

    @Contract("_ -> new")
    public static @NotNull BungeeConfigKey<Character> getChar(String path) {
        return new BungeeConfigKey<>(path, Configuration::getChar);
    }

    @Contract("_ -> new")
    public static @NotNull BungeeConfigKey<String> getString(String path) {
        return new BungeeConfigKey<>(path, Configuration::getString);
    }

    @Contract("_ -> new")
    public static @NotNull BungeeConfigKey<Object> get(String path) {
        return new BungeeConfigKey<>(path, Configuration::get);
    }

    @Contract("_ -> new")
    public static @NotNull BungeeConfigKey<Boolean> getBoolean(String path) {
        return new BungeeConfigKey<>(path, Configuration::getBoolean);
    }

    @Contract("_ -> new")
    public static @NotNull BungeeConfigKey<Configuration> getSection(String path) {
        return new BungeeConfigKey<>(path, Configuration::getSection);
    }

    @Contract("_ -> new")
    public static @NotNull BungeeConfigKey<List<Integer>> getIntList(String path) {
        return new BungeeConfigKey<>(path, Configuration::getIntList);
    }

    @Contract("_ -> new")
    public static @NotNull BungeeConfigKey<List<Float>> getFloatList(String path) {
        return new BungeeConfigKey<>(path, Configuration::getFloatList);
    }

    @Contract("_ -> new")
    public static @NotNull BungeeConfigKey<List<Short>> getShortList(String path) {
        return new BungeeConfigKey<>(path, Configuration::getShortList);
    }

    @Contract("_ -> new")
    public static @NotNull BungeeConfigKey<List<Long>> getLongList(String path) {
        return new BungeeConfigKey<>(path, Configuration::getLongList);
    }

    @Contract("_ -> new")
    public static @NotNull BungeeConfigKey<List<Double>> getDoubleList(String path) {
        return new BungeeConfigKey<>(path, Configuration::getDoubleList);
    }

    @Contract("_ -> new")
    public static @NotNull BungeeConfigKey<List<Byte>> getByteList(String path) {
        return new BungeeConfigKey<>(path, Configuration::getByteList);
    }

    @Contract("_ -> new")
    public static @NotNull BungeeConfigKey<List<Character>> getCharList(String path) {
        return new BungeeConfigKey<>(path, Configuration::getCharList);
    }

    @Contract("_ -> new")
    public static @NotNull BungeeConfigKey<List<String>> getStringList(String path) {
        return new BungeeConfigKey<>(path, Configuration::getStringList);
    }

    @Contract("_ -> new")
    public static @NotNull BungeeConfigKey<List<Boolean>> getBooleanList(String path) {
        return new BungeeConfigKey<>(path, Configuration::getBooleanList);
    }

    @Contract("_ -> new")
    public static @NotNull BungeeConfigKey<List<?>> getList(String path) {
        return new BungeeConfigKey<>(path, Configuration::getList);
    }

    private final String path;
    private final BungeeConfigUtilizer<T> utilizer;

    private BungeeConfigKey(String path, BungeeConfigUtilizer<T> utilizer) {
        this.path = path;
        this.utilizer = utilizer;
    }

    public T getFrom(Configuration configuration) {
        return utilizer.utilize(configuration, path);
    }

    public T getFrom(Configuration configuration, T defaultVal) {
        T val = getFrom(configuration);
        return val == null ? defaultVal : val;
    }

    public T getFrom(BungeeConfig config) {
        return getFrom(config.getConfiguration());
    }

    public T getFrom(BungeeConfig config, T defaultVar) {
        return getFrom(config.getConfiguration(), defaultVar);
    }
}
