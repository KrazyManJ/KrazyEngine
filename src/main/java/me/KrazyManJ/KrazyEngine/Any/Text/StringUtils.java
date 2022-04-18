package me.KrazyManJ.KrazyEngine.Any.Text;

@SuppressWarnings("unused")
public final class StringUtils {

    @Deprecated private StringUtils() {}

    public static String format(String text, Object ...vars){
        for (int i = 0; i < vars.length; i++) text = text.replace("{"+i+"}",vars[i].toString());
        return text;
    }
    public static int numberOfCharOccurence(String string, char ch){
        int count = 0;
        for (int i = 0; i < string.length(); i++) if (string.charAt(i) == ch) count++;
        return count;
    }
}
