package me.KrazyManJ.KrazyEngine.Spigot.Item;

import me.KrazyManJ.KrazyEngine.Any.LogicGates;
import me.KrazyManJ.KrazyEngine.NMS.NBTEditor.NBTCompound;
import me.KrazyManJ.KrazyEngine.NMS.NBTEditor.NBTEditor;
import org.bukkit.inventory.ItemStack;

public final class ItemComparator {
    public static boolean compare(ItemStack first, ItemStack second){
        NBTCompound c1 = NBTEditor.getNBTCompound(first);
        NBTCompound c2 = NBTEditor.getNBTCompound(second);
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
