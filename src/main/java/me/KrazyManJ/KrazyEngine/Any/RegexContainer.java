package me.KrazyManJ.KrazyEngine.Any;

import java.util.regex.Pattern;

@SuppressWarnings("unused")
public final class RegexContainer {
    public static final Pattern base64 = Pattern.compile(
            "(?:[A-Za-z0-9+/]{4})*(?:[A-Za-z0-9+/]{2}==|[A-Za-z0-9+/]{3}=)?",
            Pattern.MULTILINE | Pattern.CASE_INSENSITIVE
    );
}
