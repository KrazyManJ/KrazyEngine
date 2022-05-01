package me.KrazyManJ.KrazyEngine.Any.Component;


import me.KrazyManJ.KrazyEngine.Any.Component.Target.TargetBuilder;
import me.KrazyManJ.KrazyEngine.Any.Component.Translate.KeybindID;
import me.KrazyManJ.KrazyEngine.Any.Text.ColorUtils;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.chat.*;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.Statistic;
import org.bukkit.block.Biome;
import org.bukkit.block.Block;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Villager;
import org.bukkit.inventory.ItemStack;

@SuppressWarnings("unused")
public final class ComponentMaker {

    @Deprecated private ComponentMaker() {}
    
    public static final class Keybind {
        @Deprecated private Keybind() {}
        public static KeybindComponent make(KeybindID id){
            return new KeybindComponent(id.getId());
        }
    }
    public static final class Target {
        @Deprecated private Target() {}
        public static SelectorComponent make(TargetBuilder builder){
            return new SelectorComponent(builder.build());
        }
    }
    public static final class Translate {
        @Deprecated private Translate() {}
        public static TranslatableComponent make(ItemStack i){
            return new TranslatableComponent((i.getType().isBlock() ? "block" : "item")+".minecraft."+i.getType().getKey().getKey());
        }
        public static TranslatableComponent make(Material m){
            return new TranslatableComponent((m.isBlock() ? "block" : "item")+".minecraft."+m.getKey().getKey());
        }
        public static TranslatableComponent make(Block b){
            return new TranslatableComponent("block.minecraft."+b.getType().getKey().getKey());
        }
        public static TranslatableComponent make(Entity e){
            return new TranslatableComponent("entity.minecraft."+e.getType().getKey().getKey());
        }
        public static TranslatableComponent make(EntityType e){
            return new TranslatableComponent("entity.minecraft."+e.getKey().getKey());
        }
        public static TranslatableComponent make(Villager.Profession v){
            return new TranslatableComponent("entity.minecraft.villager."+v.getKey().getKey());
        }
        public static TranslatableComponent make(Biome b) {
            return new TranslatableComponent("biome.minecraft."+b.getKey().getKey());
        }
        public static TranslatableComponent make(GameMode m) {
            return new TranslatableComponent("gameMode."+m);
        }
        public static TranslatableComponent make(ChatColor c) {
            return new TranslatableComponent("color.minecraft."+c.toString().toLowerCase());
        }
        public static TranslatableComponent make(Statistic s) {
            return new TranslatableComponent("stat.minecraft."+s.getKey().getKey());
        }
    }
    public static final class Text {

        @Deprecated private Text() {}

        public static BaseComponent[] colored(String text){
            return TextComponent.fromLegacyText(ColorUtils.colorizeHex(text));
        }
        public static BaseComponent coloredSingle(String text){
            return ComponentUtils.toSingle(colored(text));
        }
    }
}
