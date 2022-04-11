package me.KrazyManJ.KrazyEngine.Any;

import net.md_5.bungee.api.ChatColor;

import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("unused")
public final class ColorUtils {

    @Deprecated private ColorUtils() {}

    public static String colorize(String text){
        return ChatColor.translateAlternateColorCodes('&',text);
    }
    public static List<String> colorize(List<String> colorize){
        List<String> returnment = new ArrayList<>();
        for (String l : colorize) returnment.add(colorize(l));
        return returnment;
    }
}
