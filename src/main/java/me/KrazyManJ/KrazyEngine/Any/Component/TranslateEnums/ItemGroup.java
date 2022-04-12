package me.KrazyManJ.KrazyEngine.Any.Component.TranslateEnums;

@SuppressWarnings("unused")
public enum ItemGroup {
    ITEMGROUP_BUILDINGBLOCKS("itemGroup.buildingBlocks"),
    ITEMGROUP_DECORATIONS("itemGroup.decorations"),
    ITEMGROUP_REDSTONE("itemGroup.redstone"),
    ITEMGROUP_TRANSPORTATION("itemGroup.transportation"),
    ITEMGROUP_MISC("itemGroup.misc"),
    ITEMGROUP_SEARCH("itemGroup.search"),
    ITEMGROUP_FOOD("itemGroup.food"),
    ITEMGROUP_TOOLS("itemGroup.tools"),
    ITEMGROUP_COMBAT("itemGroup.combat"),
    ITEMGROUP_BREWING("itemGroup.brewing"),
    ITEMGROUP_MATERIALS("itemGroup.materials"),
    ITEMGROUP_INVENTORY("itemGroup.inventory"),
    ITEMGROUP_HOTBAR("itemGroup.hotbar")
    ;
    private final String id;
    ItemGroup(String id) {this.id = id;}
    public String getId() {return id;}
}
