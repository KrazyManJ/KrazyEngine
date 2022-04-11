package me.KrazyManJ.KrazyEngine.Any;

public final class StringFormat {
    public static String format(String text, Object ...vars){
        for (int i = 0; i < vars.length; i++) text = text.replace("{"+i+"}",vars[i].toString());
        return text;
    }
}
