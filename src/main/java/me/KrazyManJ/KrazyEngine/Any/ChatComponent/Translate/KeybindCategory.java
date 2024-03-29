package me.KrazyManJ.KrazyEngine.Any.ChatComponent.Translate;

import me.KrazyManJ.KrazyEngine.Core.NeedsUpdateEachMinecraftVersion;

@SuppressWarnings("unused")
@NeedsUpdateEachMinecraftVersion(lastUpdated = "1.17")
public enum KeybindCategory {
    KEY_CATEGORIES_MOVEMENT("key.categories.movement"),
    KEY_CATEGORIES_MISC("key.categories.misc"),
    KEY_CATEGORIES_MULTIPLAYER("key.categories.multiplayer"),
    KEY_CATEGORIES_GAMEPLAY("key.categories.gameplay"),
    KEY_CATEGORIES_UI("key.categories.ui"),
    KEY_CATEGORIES_INVENTORY("key.categories.inventory"),
    KEY_CATEGORIES_CREATIVE("key.categories.creative");

    private final String id;
    KeybindCategory(String id) {this.id = id;}
    public String getId() {return id;}
}
