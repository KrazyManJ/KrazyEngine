package me.KrazyManJ.KrazyEngine.Any.Component;

import me.KrazyManJ.KrazyEngine.NMS.NBTEditor.NBTEditor;
import net.md_5.bungee.api.chat.BaseComponent;
import net.md_5.bungee.api.chat.HoverEvent;
import net.md_5.bungee.api.chat.ItemTag;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.chat.hover.content.Item;
import net.md_5.bungee.api.chat.hover.content.Text;
import org.bukkit.entity.Entity;
import org.bukkit.inventory.ItemStack;

@SuppressWarnings("unused")
public final class HoverMaker {
    public static HoverEvent fromText(String text){
        return new HoverEvent(HoverEvent.Action.SHOW_TEXT,new Text(text));
    }
    public static HoverEvent fromText(BaseComponent[] text){
        return new HoverEvent(HoverEvent.Action.SHOW_TEXT,new Text(text));
    }
    public static HoverEvent fromItem(ItemStack item){
        return new HoverEvent(HoverEvent.Action.SHOW_ITEM,
            new Item(
                item.getType().getKey().getKey(),
                item.getAmount(),
                ItemTag.ofNbt(NBTEditor.getNBTCompound(item,"tag").toJson())
            )
        );
    }
    public static HoverEvent fromEntity(Entity entity){
        return new HoverEvent(HoverEvent.Action.SHOW_ENTITY,
            new net.md_5.bungee.api.chat.hover.content.Entity(
                    entity.getType().getKey().toString(),
                    entity.getUniqueId().toString(),
                    ComponentUtils.toSingle(
                            TextComponent.fromLegacyText(entity.getCustomName() != null ? entity.getCustomName() : entity.getName())
                    )
            )
        );
    }
}
