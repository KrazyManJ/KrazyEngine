package me.KrazyManJ.KrazyEngine.Spigot.Item;

import me.KrazyManJ.KrazyEngine.Any.Text.Gradient;
import org.bukkit.DyeColor;
import org.bukkit.Material;
import org.bukkit.block.banner.Pattern;
import org.bukkit.block.banner.PatternType;
import org.bukkit.entity.Creeper;
import org.bukkit.entity.Pig;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.BannerMeta;
import org.checkerframework.checker.units.qual.C;

@SuppressWarnings("unused")
public final class BannerMaker {
    private final BannerMeta meta;
    private final DyeColor baseColor;
    public BannerMaker(DyeColor baseColor) {
        this.baseColor = baseColor;
        meta = (BannerMeta) new ItemStack(bannerFromColor(baseColor)).getItemMeta();
    }
    public BannerMaker add(DyeColor color, PatternType type){
        meta.addPattern(new Pattern(color, type));
        return this;
    }
    public BannerMaker add(DyeColor color, PatternDisplayType type){
        meta.addPattern(new Pattern(color, type.toBukkitType()));
        return this;
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

    public static ItemStack omniousBanner(){
        return new StackMaker(
                new BannerMaker(DyeColor.WHITE)
                        .add(DyeColor.CYAN,PatternDisplayType.LOZENGE)
                        .add(DyeColor.LIGHT_GRAY,PatternDisplayType.BASE)
                        .add(DyeColor.GRAY, PatternDisplayType.PALE)
                        .add(DyeColor.LIGHT_GRAY, PatternDisplayType.BORDURE)
                        .add(DyeColor.BLACK, PatternDisplayType.FESS)
                        .add(DyeColor.LIGHT_GRAY, PatternDisplayType.PER_FESS)
                        .add(DyeColor.LIGHT_GRAY, PatternDisplayType.ROUNDEL)
                        .add(DyeColor.BLACK, PatternDisplayType.BORDURE)
                        .make()
                )
                .displayName("&6&oOmnious Banner")
                .make();
    }
    public static ItemStack endCityBanner(){
        return new BannerMaker(DyeColor.MAGENTA)
                .add(DyeColor.BLACK,PatternDisplayType.INVERTED_CHEVRON)
                .add(DyeColor.BLACK,PatternDisplayType.CHEVRON)
                .make();
    }

    public enum PatternDisplayType{
        FULLY_COLOR_FIELD(PatternType.BASE,"b"),
        BASE(PatternType.STRIPE_BOTTOM,"bs"),
        CHIEF(PatternType.STRIPE_TOP,"ts"),
        PALE_DEXTER(PatternType.STRIPE_LEFT,"ls"),
        PALE_SINISTER(PatternType.STRIPE_RIGHT,"rs"),
        PALE(PatternType.STRIPE_CENTER,"cs"),
        FESS(PatternType.STRIPE_MIDDLE,"ms"),
        BEND(PatternType.STRIPE_DOWNRIGHT,"drs"),
        BEND_SINISTER(PatternType.STRIPE_DOWNLEFT,"dls"),
        PALY(PatternType.STRIPE_SMALL,"ss"),
        SALTIRE(PatternType.CROSS,"cr"),
        CROSS(PatternType.STRAIGHT_CROSS,"sc"),
        PER_BEND_SINISTER(PatternType.DIAGONAL_LEFT,"ld"),
        PER_BEND(PatternType.DIAGONAL_RIGHT_MIRROR,"rud"),
        PER_BEND_INVERTED(PatternType.DIAGONAL_LEFT_MIRROR,"lud"),
        PER_BEND_SINISTER_INVERTED(PatternType.DIAGONAL_RIGHT,"rd"),
        PER_PALE(PatternType.HALF_VERTICAL,"vh"),
        PER_PALE_INVERTED(PatternType.HALF_VERTICAL_MIRROR,"vhr"),
        PER_FESS(PatternType.HALF_HORIZONTAL,"hh"),
        PER_FESS_INVERTED(PatternType.HALF_HORIZONTAL_MIRROR,"hhb"),
        BASE_DEXTER_CANTON(PatternType.SQUARE_BOTTOM_LEFT,"bl"),
        BASE_SINISTER_CANTON(PatternType.SQUARE_BOTTOM_RIGHT,"br"),
        CHIEF_DEXTER_CANTON(PatternType.SQUARE_TOP_LEFT,"tl"),
        CHIEF_SINISTER_CANTON(PatternType.SQUARE_TOP_RIGHT,"tr"),
        CHEVRON(PatternType.TRIANGLE_BOTTOM,"bt"),
        INVERTED_CHEVRON(PatternType.TRIANGLE_TOP,"tt"),
        BASE_INDENTED(PatternType.TRIANGLES_BOTTOM,"bts"),
        CHIEF_INDENTED(PatternType.TRIANGLES_TOP,"tts"),
        ROUNDEL(PatternType.CIRCLE_MIDDLE,"mc"),
        LOZENGE(PatternType.RHOMBUS_MIDDLE,"mr"),
        BORDURE(PatternType.BORDER,"bo"),
        BORDURE_INDENTED(PatternType.CURLY_BORDER,"cbo"),
        FIELD_MASONED(PatternType.BRICKS,"bri"),
        GRADIENT(PatternType.GRADIENT,"gra"),
        BASE_GRADIENT(PatternType.GRADIENT_UP,"gru"),
        CREEPER_CHARGE(PatternType.CREEPER,"cre"),
        SKULL_CHARGE(PatternType.SKULL,"sku"),
        FLOWER_CHARGE(PatternType.FLOWER,"flo"),
        THING(PatternType.MOJANG,"moj"),
        GLOBE(PatternType.GLOBE,"glb"),
        SNOUT(PatternType.PIGLIN,"pig");

        private final PatternType type;
        private final String code;
        PatternDisplayType(PatternType type, String code){
            this.type = type;
            this.code = code;
        }
        public PatternType toBukkitType() { return type; }
        public String getCode() { return code; }
    }
}
