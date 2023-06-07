package me.KrazyManJ.KrazyEngine.Any.Regex;

import java.util.regex.Pattern;

@SuppressWarnings("unused")
public final class RegexConstants {

    @Deprecated
    private RegexConstants() {

    }

    public static final Pattern base64 = Pattern.compile(
            "(?:[A-Za-z0-9+/]{4})*(?:[A-Za-z0-9+/]{2}==|[A-Za-z0-9+/]{3}=)?",
            Pattern.MULTILINE | Pattern.CASE_INSENSITIVE
    );
    public static final Pattern romanNumeral = Pattern.compile(
            "M{0,4}(CM|CD|D?C{0,3})(XC|XL|L?X{0,3})(IX|IV|V?I{0,3})",
            Pattern.MULTILINE
    );
}
