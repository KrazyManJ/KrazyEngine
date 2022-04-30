package me.KrazyManJ.KrazyEngine.Any.Text;

import org.bukkit.map.MinecraftFont;

@SuppressWarnings("unused")
public final class Centerer {

    @Deprecated private Centerer() {}

    private static String center(String t, int rowsize){
        if (t.length() > rowsize) return t;
        int spaceAmount = Math.round((rowsize - getWidth(t)) / 4F);
        return " ".repeat(spaceAmount/2)+t;
    }
    public static String centerChat(String text){
        return center(text,319);
    }
    public static String centerMOTD(String text){
        return centerMOTD(text,false);
    }
    public static String centerMOTD(String text, boolean fullwide){
        return center(text,fullwide ? 270 : 266);
    }

    public static int getWidth(String t){
        int size = 0;
        for (String split : t.replaceAll("(?i)§x(§[0-9a-f]){6}","§a").split("(?i)(?=§[a-f0-9lr])")) {
            String cleared = ColorUtils.clearColors(split);
            int width = 0;
            for (char ch : cleared.toCharArray()){
                try {
                    width += MinecraftFont.Font.getWidth(String.valueOf(ch));
                } catch (IllegalArgumentException e){
                    width += 5;
                }
                width++;
            }
            width = width > 0 ? width - 1 : 0;
            size += split.startsWith("&l") || split.startsWith("§l")
                    ? width + cleared.length()
                    : width;
        }
        return size;
    }
}
