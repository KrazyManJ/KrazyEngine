package me.KrazyManJ.KrazyEngine.BungeeCord.Config;

import net.md_5.bungee.config.Configuration;

@FunctionalInterface
public interface BungeeConfigUtilizer<T> {
    T utilize(Configuration cfg, String path);
}
