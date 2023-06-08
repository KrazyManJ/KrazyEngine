package me.KrazyManJ.KrazyEngine.Spigot.Item;

import io.github.bananapuncher714.nbteditor.NBTEditor;
import me.KrazyManJ.KrazyEngine.Any.LogicGates;

import org.bukkit.inventory.ItemStack;

@SuppressWarnings("unused")
public final class ItemComparator {

    @Deprecated private ItemComparator() {}

    public static boolean compare(ItemStack first, ItemStack second){
        NBTEditor.NBTCompound c1 = NBTEditor.getNBTCompound(first);
        NBTEditor.NBTCompound c2 = NBTEditor.getNBTCompound(second);
        if (LogicGates.AND(c1 == null,c2 == null)) return true;
        if (LogicGates.XOR(c1 == null, c2 == null)) return false;
        assert c1 != null;
        return c1.equals(c2);
    }
    public static boolean compareIgnoringAmount(ItemStack first, ItemStack second){
        return compare(
                new StackMaker(first).amount(1).make(),
                new StackMaker(second).amount(1).make()
        );
    }
}
