package me.KrazyManJ.KrazyEngine.Spigot.GUI;

import org.bukkit.event.inventory.InventoryClickEvent;

@FunctionalInterface
public interface GUIClick {
    void click(InventoryClickEvent clickEvent);
}
