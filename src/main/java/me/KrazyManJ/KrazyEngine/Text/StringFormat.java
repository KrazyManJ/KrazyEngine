package me.KrazyManJ.KrazyEngine.Text;

public class StringFormat {
    public static String format(String text, Object ...vars){
        for (int i = 0; i < vars.length; i++) text = text.replace("{"+i+"}",vars[i].toString());

        return text;
    }
}
