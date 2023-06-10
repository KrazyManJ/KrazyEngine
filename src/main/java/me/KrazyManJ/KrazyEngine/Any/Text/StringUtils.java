package me.KrazyManJ.KrazyEngine.Any.Text;

import java.util.regex.Pattern;

@SuppressWarnings("unused")
public final class StringUtils {

    private StringUtils() {
    }

    public static String format(String text, Object... vars) {
        for (int i = 0; i < vars.length; i++) text = text.replace("{" + i + "}", vars[i].toString());
        return text;
    }

    public static int numberOfCharOccurence(String string, char ch) {
        int count = 0;
        for (int i = 0; i < string.length(); i++) if (string.charAt(i) == ch) count++;
        return count;
    }

    public static String[] splitKeepingDelimeter(String text, String... splitters) {
        String pt = Pattern.quote(String.join("|", splitters));
        return text.split("(?<=" + pt + ")|(?=" + pt + ")");
    }
}
