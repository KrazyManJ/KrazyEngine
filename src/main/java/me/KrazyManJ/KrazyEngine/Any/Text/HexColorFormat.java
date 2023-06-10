package me.KrazyManJ.KrazyEngine.Any.Text;

import org.jetbrains.annotations.Range;

import java.util.Arrays;
import java.util.function.Function;
import java.util.regex.Pattern;

@SuppressWarnings("unused")
public enum HexColorFormat {
    /**
     * This format is used in default by minecraft to translate hex colors
     * Format: §x§0§9§a§f§A§F
     */
    FORMATTED(
            Pattern.compile("(?mi)§x(?:§[0-9a-f]){6}"),
            s -> s.substring(3).split("§"),
            chars -> "&x&" + String.join("§", chars)
    ),
    /**
     * Format: &amp;#09afAF
     */
    BASIC(
            Pattern.compile("(?mi)&#[0-9a-f]{6}"),
            s -> s.substring(2).split(""),
            chars -> "&#" + String.join("", chars)
    ),
    /**
     * Format: &amp;x&amp;0&amp;9&amp;a&amp;f&amp;A&amp;F
     */
    LEGACY(
            Pattern.compile("(?mi)&x(?:&[0-9a-f]){6}"),
            s -> s.substring(3).split("&"),
            chars -> "&x&" + String.join("&", chars)
    ),
    /**
     * Format: &#92;u00A7x&#92;u00A70&#92;u00A79&#92;u00A7a&#92;u00A7f&#92;u00A7A&#92;u00A7F
     */
    MOTD(
            Pattern.compile("(?mi)\\\\u00A7x(?:\\\\u00A7[0-9a-f]){6}"),
            s -> s.substring(3).split("\\\\u00A7"),
            chars -> "&x&" + String.join("\\\\u00A7", chars)
    ),
    /**
     * Format: &lt;#09afAF&gt;
     */
    CHAT(
            Pattern.compile("(?mi)<#[0-9a-f]{6}>"),
            s -> s.substring(2, s.length() - 1).split(""),
            chars -> "<#" + String.join("", chars) + ">"
    ),
    /**
     * Format: &lt;##09afAF&gt;
     */
    SKRIPT(
            Pattern.compile("(?mi)<##[0-9a-f]{6}>"),
            s -> s.substring(3, s.length() - 1).split(""),
            chars -> "<##" + String.join("", chars) + ">"
    ),
    /**
     * Format: {#09afAF}
     */
    CMI(
            Pattern.compile("(?mi)\\{#[0-9a-f]{6}}"),
            s -> s.substring(2, s.length() - 1).split(""),
            chars -> "{#" + String.join("", chars) + "}"
    );
    private final Pattern pattern;
    private final Function<String, @Range(from = 6, to = 6) String[]> decoder;
    private final Function<@Range(from = 6, to = 6) String[], String> encoder;

    HexColorFormat(
            Pattern pattern,
            Function<String, String[]> decoder,
            Function<String[], String> encoder
    ) {
        this.pattern = pattern;
        this.encoder = encoder;
        this.decoder = decoder;
    }

    public Pattern getPattern() {
        return pattern;
    }

    public Function<String, @Range(from = 6, to = 6) String[]> getDecoder() {
        return decoder;
    }

    public Function<@Range(from = 6, to = 6) String[], String> getEncoder() {
        return encoder;
    }

    public static HexColorFormat[] nonDefaultValues() {
        return Arrays.copyOfRange(values(), 1, values().length);
    }
}
