package me.KrazyManJ.KrazyEngine.Spigot.ChatComponent;

import io.github.bananapuncher714.nbteditor.NBTEditor;
import me.KrazyManJ.KrazyEngine.Any.ChatComponent.ComponentMaker;
import me.KrazyManJ.KrazyEngine.Any.ChatComponent.HoverMaker;
import net.md_5.bungee.api.chat.HoverEvent;
import net.md_5.bungee.api.chat.ItemTag;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.chat.hover.content.Item;
import org.bukkit.entity.Entity;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

@SuppressWarnings("unused")
public class SpigotHoverMaker extends HoverMaker {

    private SpigotHoverMaker() {
        super();
    }

    @Contract("_ -> new")
    public static @NotNull HoverEvent fromItem(@NotNull ItemStack item) {
        return new HoverEvent(HoverEvent.Action.SHOW_ITEM,
                new Item(
                        item.getType().getKey().getKey(),
                        item.getAmount(),
                        ItemTag.ofNbt(NBTEditor.getNBTCompound(item, "tag").toJson())
                )
        );
    }

    @Contract("_ -> new")
    public static @NotNull HoverEvent fromEntity(@NotNull Entity entity) {
        return new HoverEvent(HoverEvent.Action.SHOW_ENTITY,
                new net.md_5.bungee.api.chat.hover.content.Entity(
                        entity.getType().getKey().toString(),
                        entity.getUniqueId().toString(),
                        ComponentMaker.toSingle(
                                TextComponent.fromLegacyText(entity.getCustomName() != null ? entity.getCustomName() : entity.getName())
                        )
                )
        );
    }
}
