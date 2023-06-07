package me.KrazyManJ.KrazyEngine.Spigot.Inventory;

import org.bukkit.event.inventory.InventoryClickEvent;

@FunctionalInterface
public interface GUIClick {
    void click(InventoryClickEvent clickEvent);
}
