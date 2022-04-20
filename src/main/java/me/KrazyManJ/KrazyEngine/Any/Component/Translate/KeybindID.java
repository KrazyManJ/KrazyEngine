package me.KrazyManJ.KrazyEngine.Any.Component.Translate;

@SuppressWarnings("unused")
public enum KeybindID {
    MOVEMENT_JUMP("key.jump"),
    MOVEMENT_SNEAK("key.sneak"),
    MOVEMENT_SPRINT("key.sprint"),
    MOVEMENT_LEFT("key.left"),
    MOVEMENT_RIGHT("key.right"),
    MOVEMENT_BACK("key.back"),
    MOVEMENT_FORWARD("key.forward"),
    GAMEPLAY_ATTACK("key.attack"),
    GAMEPLAY_PICK_ITEM("key.pickItem"),
    GAMEPLAY_USE("key.use"),
    INVENTORY_DROP("key.drop"),
    INVENTORY_HOTBAR_1("key.hotbar.1"),
    INVENTORY_HOTBAR_2("key.hotbar.2"),
    INVENTORY_HOTBAR_3("key.hotbar.3"),
    INVENTORY_HOTBAR_4("key.hotbar.4"),
    INVENTORY_HOTBAR_5("key.hotbar.5"),
    INVENTORY_HOTBAR_6("key.hotbar.6"),
    INVENTORY_HOTBAR_7("key.hotbar.7"),
    INVENTORY_HOTBAR_8("key.hotbar.8"),
    INVENTORY_HOTBAR_9("key.hotbar.9"),
    INVENTORY_INVENTORY("key.inventory"),
    INVENTORY_SWAP_OFF_HAND("key.swapOffhand"),
    CREATIVE_LOAD_TOOL_BAR_ACTIVATOR("key.loadToolbarActivator"),
    CREATIVE_SAVE_TOOL_BAR_ACTIVATOR("key.saveToolbarActivator"),
    MULTIPLAYER_PLAYER_LIST("key.playerlist"),
    MULTIPLAYER_CHAT("key.chat"),
    MULTIPLAYER_COMMAND("key.command"),
    MULTIPLAYER_SOCIAL_INTERACTIONS("key.socialInteractions"),
    MISCELLANEOUS_ADVANCEMENTS("key.advancements"),
    MISCELLANEOUS_SPECTATOR_OUTLINES("key.spectatorOutlines"),
    MISCELLANEOUS_SCREENSHOT("key.screenshot"),
    MISCELLANEOUS_SMOOTH_CAMERA("key.smoothCamera"),
    MISCELLANEOUS_FULLSCREEN("key.fullscreen"),
    MISCELLANEOUS_TOGGLE_PERSPECTIVE("key.togglePerspective"),
    ;
    private final String id;
    KeybindID(String id) {this.id = id;}
    public String getId() {return id;}
}
