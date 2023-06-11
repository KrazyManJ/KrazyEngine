package me.KrazyManJ.KrazyEngine.Spigot.ChatComponent;

import me.KrazyManJ.KrazyEngine.Any.ChatComponent.ComponentMaker;
import net.md_5.bungee.api.chat.TranslatableComponent;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.Statistic;
import org.bukkit.block.Biome;
import org.bukkit.block.Block;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Villager;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

@SuppressWarnings("unused")
public class SpigotComponentMaker extends ComponentMaker {

    private SpigotComponentMaker(){
        super();
    }

    @Contract("_ -> new")
    public static @NotNull TranslatableComponent makeTranslatable(@NotNull Material m) {
        return new TranslatableComponent((m.isBlock() ? "block" : "item") + ".minecraft." + m.getKey().getKey());
    }

    @Contract("_ -> new")
    public static @NotNull TranslatableComponent makeTranslatable(@NotNull Block b) {
        return new TranslatableComponent("block.minecraft." + b.getType().getKey().getKey());
    }

    @Contract("_ -> new")
    public static @NotNull TranslatableComponent makeTranslatable(@NotNull Entity e) {
        return new TranslatableComponent("entity.minecraft." + e.getType().getKey().getKey());
    }

    @Contract("_ -> new")
    public static @NotNull TranslatableComponent makeTranslatable(@NotNull EntityType e) {
        return new TranslatableComponent("entity.minecraft." + e.getKey().getKey());
    }

    @Contract("_ -> new")
    public static @NotNull TranslatableComponent makeTranslatable(@NotNull Villager.Profession v) {
        return new TranslatableComponent("entity.minecraft.villager." + v.getKey().getKey());
    }

    @Contract("_ -> new")
    public static @NotNull TranslatableComponent makeTranslatable(@NotNull Biome b) {
        return new TranslatableComponent("biome.minecraft." + b.getKey().getKey());
    }

    @Contract("_ -> new")
    public static @NotNull TranslatableComponent makeTranslatable(@NotNull GameMode m) {
        return new TranslatableComponent("gameMode." + m);
    }

    @Contract("_ -> new")
    public static @NotNull TranslatableComponent makeTranslatable(@NotNull Statistic s) {
        return new TranslatableComponent("stat.minecraft." + s.getKey().getKey());
    }

}
