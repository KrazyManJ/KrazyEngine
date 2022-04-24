package me.KrazyManJ.KrazyEngine.Spigot.Item;

import org.bukkit.DyeColor;
import org.bukkit.Material;
import org.bukkit.block.banner.Pattern;
import org.bukkit.block.banner.PatternType;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.BannerMeta;

@SuppressWarnings("unused")
public class BannerMaker {
    private final BannerMeta meta;
    private final DyeColor baseColor;
    public BannerMaker(DyeColor baseColor) {
        this.baseColor = baseColor;
        meta = (BannerMeta) new ItemStack(bannerFromColor(baseColor)).getItemMeta();
    }
    public void add(DyeColor color, PatternType type){
        meta.addPattern(new Pattern(color, type));
    }
    public ItemStack make(){
        ItemStack i = new ItemStack(bannerFromColor(baseColor));
        i.setItemMeta(meta);
        return i;
    }
    private Material bannerFromColor(DyeColor color){
        return switch (color){
            case WHITE -> Material.WHITE_BANNER;
            case ORANGE -> Material.ORANGE_BANNER;
            case MAGENTA -> Material.MAGENTA_BANNER;
            case LIGHT_BLUE -> Material.LIGHT_BLUE_BANNER;
            case YELLOW -> Material.YELLOW_BANNER;
            case LIME -> Material.LIME_BANNER;
            case PINK -> Material.PINK_BANNER;
            case GRAY -> Material.GRAY_BANNER;
            case LIGHT_GRAY -> Material.LIGHT_GRAY_BANNER;
            case CYAN -> Material.CYAN_BANNER;
            case PURPLE -> Material.PURPLE_BANNER;
            case BLUE -> Material.BLUE_BANNER;
            case BROWN -> Material.BROWN_BANNER;
            case GREEN -> Material.GREEN_BANNER;
            case RED -> Material.RED_BANNER;
            case BLACK -> Material.BLACK_BANNER;
        };
    }
}
