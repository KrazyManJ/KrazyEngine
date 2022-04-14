package me.KrazyManJ.KrazyEngine.Spigot.Item;

import org.bukkit.Material;
import org.bukkit.block.Block;

import java.util.Arrays;

@SuppressWarnings("unused")
public final class MaterialAlias {

    @Deprecated private MaterialAlias() {}

    private static boolean isContainer(Material m){
        return Arrays.asList(
                Material.CHEST,
                Material.TRAPPED_CHEST,
                Material.FURNACE,
                Material.HOPPER,
                Material.SMOKER,
                Material.BLAST_FURNACE,
                Material.ENDER_CHEST,
                Material.CRAFTING_TABLE,
                Material.ENCHANTING_TABLE,
                Material.CARTOGRAPHY_TABLE,
                Material.LOOM,
                Material.BARREL,
                Material.STONECUTTER,
                Material.GRINDSTONE,
                Material.DISPENSER,
                Material.DROPPER,
                Material.SHULKER_BOX
        ).contains(m);
    }
    private static boolean isLog(Material m){
        return Arrays.asList(
                Material.OAK_LOG,
                Material.SPRUCE_LOG,
                Material.BIRCH_LOG,
                Material.JUNGLE_LOG,
                Material.ACACIA_LOG,
                Material.DARK_OAK_LOG
        ).contains(m);
    }
    private static boolean isStone(Material m){
        return Arrays.asList(
                Material.STONE,
                Material.ANDESITE,
                Material.DIORITE,
                Material.GRANITE,
                Material.COBBLESTONE,
                Material.DEEPSLATE,
                Material.CALCITE,
                Material.TUFF,
                Material.DRIPSTONE_BLOCK,

                Material.IRON_ORE, Material.DEEPSLATE_IRON_ORE, Material.COAL_ORE, Material.DEEPSLATE_COAL_ORE,
                Material.COPPER_ORE, Material.DEEPSLATE_COPPER_ORE, Material.REDSTONE_ORE, Material.DEEPSLATE_REDSTONE_ORE,
                Material.GOLD_ORE, Material.DEEPSLATE_GOLD_ORE, Material.DIAMOND_ORE, Material.DEEPSLATE_DIAMOND_ORE,
                Material.EMERALD_ORE, Material.DEEPSLATE_EMERALD_ORE, Material.LAPIS_ORE, Material.DEEPSLATE_LAPIS_ORE,
                Material.NETHERRACK, Material.NETHER_GOLD_ORE, Material.NETHER_QUARTZ_ORE, Material.ANCIENT_DEBRIS,
                Material.BASALT, Material.BLACKSTONE, Material.MAGMA_BLOCK, Material.END_STONE
        ).contains(m);
    }
}
