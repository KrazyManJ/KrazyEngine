package me.KrazyManJ.KrazyEngine.Any.Component;

import me.KrazyManJ.KrazyEngine.Any.Component.Enums.KeybindID;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.chat.KeybindComponent;
import net.md_5.bungee.api.chat.TranslatableComponent;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.Statistic;
import org.bukkit.block.Biome;
import org.bukkit.block.Block;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Villager;
import org.bukkit.inventory.ItemStack;

@SuppressWarnings({"unused", "UnusedReturnValue"})
public final class ComponentMaker {

    @Deprecated private ComponentMaker() {}

    public static TranslatableComponent makeTranslate(ItemStack i){
        return new TranslatableComponent((i.getType().isBlock() ? "block" : "item")+".minecraft."+i.getType().getKey().getKey());
    }
    public static TranslatableComponent makeTranslate(Material m){
        return new TranslatableComponent((m.isBlock() ? "block" : "item")+".minecraft."+m.getKey().getKey());
    }
    public static TranslatableComponent makeTranslate(Block b){
        return new TranslatableComponent("block.minecraft."+b.getType().getKey().getKey());
    }
    public static TranslatableComponent makeTranslate(Entity e){
        return new TranslatableComponent("entity.minecraft."+e.getType().getKey().getKey());
    }
    public static TranslatableComponent makeTranslate(EntityType e){
        return new TranslatableComponent("entity.minecraft."+e.getKey().getKey());
    }
    public static TranslatableComponent makeTranslate(Villager.Profession v){
        return new TranslatableComponent("entity.minecraft.villager."+v.getKey().getKey());
    }
    public static TranslatableComponent makeTranslate(Biome b) {
        return new TranslatableComponent("biome.minecraft."+b.getKey().getKey());
    }
    public static TranslatableComponent makeTranslate(GameMode m) {
        return new TranslatableComponent("gameMode."+m);
    }
    public static TranslatableComponent makeTranslate(ChatColor c) {
        return new TranslatableComponent("color.minecraft."+c.toString().toLowerCase());
    }
    public static TranslatableComponent makeTranslate(Statistic s) {
        return new TranslatableComponent("stat.minecraft."+s.getKey().getKey());
    }

    public static KeybindComponent makeKey(KeybindID id){
        return new KeybindComponent(id.getId());
    }

}
