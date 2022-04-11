package me.KrazyManJ.KrazyEngine.Spigot.Item;

import org.bukkit.OfflinePlayer;

@SuppressWarnings("unused")
public final class SkullStackMaker extends StackMaker {
    public SkullStackMaker(String base64) { super(Skull.fromValue(base64)); }
    public SkullStackMaker(OfflinePlayer player) { super(Skull.ofPlayer(player)); }
}
