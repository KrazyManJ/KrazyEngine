package me.KrazyManJ.KrazyEngine.Any.ChatComponent.Target;


import me.KrazyManJ.KrazyEngine.Core.NeedsUpdateEachMinecraftVersion;

/**
 * Minecraft Gamemoe Enumeration
 *
 * <p>
 *     Replace for {@link TargetSelectorBuilder} to not use Bukkit class
 * </p>
 */
@NeedsUpdateEachMinecraftVersion(lastUpdated = "1.20")
public enum GameMode {
    SURVIVAL,
    CREATIVE,
    ADVENTURE,
    SPECTATOR
}
