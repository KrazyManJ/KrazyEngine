package me.KrazyManJ.KrazyEngine.Any.Text;

import net.md_5.bungee.api.ChatColor;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@SuppressWarnings("unused")
public final class ColorUtils {

    @Deprecated private ColorUtils() {}

    private static final Pattern hex = Pattern.compile("(?mi)&#[0-9a-f]{6}");

    public static String colorize(String text){
        return ChatColor.translateAlternateColorCodes('&',text);
    }
    public static String[] colorize(String ...texts){
        for (int i = 0; i < texts.length; i++) texts[i] = colorize(texts[i]);
        return texts;
    }
    public static List<String> colorize(List<String> texts){
        for (int i = 0; i < texts.size(); i++) texts.set(i,colorize(texts.get(i)));
        return texts;
    }

    public static String colorizeHex(String text){
        Matcher m = hex.matcher(text);
        while (m.find())
            text = text.replace(m.group(), ""+ ChatColor.of(m.group().replace("&", "")));
        return colorize(text);
    }
    public static String[] colorizeHex(String ...texts){
        for (int i = 0; i < texts.length; i++) texts[i] = colorizeHex(texts[i]);
        return texts;
    }
    public static List<String> colorizeHex(List<String> texts){
        for (int i = 0; i < texts.size(); i++) texts.set(i,colorizeHex(texts.get(i)));
        return texts;
    }
    public static String clearColors(String message, boolean legacy, boolean hex) {
        if (hex) message = message.replaceAll("(?i)&x(&[0-9a-f]){6}|&#[0-9a-f]{6}","");
        return legacy ? org.bukkit.ChatColor.stripColor(message) : message;
    }
}
