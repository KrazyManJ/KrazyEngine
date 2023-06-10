package me.KrazyManJ.KrazyEngine.Any.Text;

import org.apache.commons.lang3.Range;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@SuppressWarnings("unused")
public final class ColorTranslator {

    private ColorTranslator() {
    }

    private static final Pattern LEGACY_FULL = Pattern.compile("(?mi)&[0-9a-fk-or]");
    private static final Pattern LEGACY_COLOR = Pattern.compile("(?mi)&[0-9a-f]");
    private static final Pattern LEGACY_FORMAT = Pattern.compile("(?mi)&[k-or]");

    private static final Pattern FORMATTED_LEGACY_FULL = Pattern.compile("(?mi)§[0-9a-fk-or]");
    private static final Pattern FORMATTED_LEGACY_COLOR = Pattern.compile("(?mi)§[0-9a-f]");
    private static final Pattern FORMATTED_LEGACY_FORMAT = Pattern.compile("(?mi)§[k-or]");


    private static String translateHexColor(String color, @NotNull HexColorFormat from, @NotNull HexColorFormat to) {
        return to.getEncoder().apply(from.getDecoder().apply(color));
    }

    private static String translateLegacy(@NotNull Pattern pattern, String text) {
        Matcher m = pattern.matcher(text);
        while (m.find()) text = text.replace(m.group(), m.group().replace('&', '§'));
        return text;
    }

    private static @NotNull List<Range<Integer>> listAllHexColorsStartsIndexes(String text) {
        List<Range<Integer>> starts = new ArrayList<>();
        for (HexColorFormat format : HexColorFormat.values()) {
            Matcher m = format.getPattern().matcher(text);
            while (m.find()) starts.add(Range.between(m.start(), m.end()));
        }
        return starts;
    }

    private static String clearExcludingHex(@NotNull Pattern pattern, String text) {
        List<Range<Integer>> ranges = listAllHexColorsStartsIndexes(text);
        Matcher m = pattern.matcher(text);
        while (m.find()) {
            if (ranges.stream().noneMatch(r -> r.contains(m.start())))
                text = text.replace(m.group(), "");
        }
        return text;
    }


    /**
     * Formats legacy colors like &amp;1, &amp;3, &amp;a, &amp;f
     *
     * @param text string to be formatted
     * @return formatted text
     */
    public static String formatLegacyColors(String text) {
        return translateLegacy(LEGACY_COLOR, text);
    }

    /**
     * Formats legacy formats like &amp;k, &amp;l, &amp;m
     *
     * @param text string to be formatted
     * @return formatted text
     */
    public static String formatLegacyFormats(String text) {
        return translateLegacy(LEGACY_FORMAT, text);
    }

    /**
     * Formats legacy colors and formats. It combines two functions:
     * <ul>
     *     <li>{@link #formatLegacyColors(String)}</li>
     *     <li>{@link #formatLegacyFormats(String)}</li>
     * </ul>
     *
     * @param text string to be formatted
     * @return formatted text
     */
    public static String formatFullLegacy(String text) {
        return translateLegacy(LEGACY_FULL, text);
    }

    /**
     * Translates all hex colors from one format to another
     *
     * @param text string to translate
     * @param from format to find and convert from
     * @param to   result format of translation
     * @return translated string
     */
    public static String translateHexColors(String text, @NotNull HexColorFormat from, HexColorFormat to) {
        Matcher m = from.getPattern().matcher(text);
        while (m.find()) text = text.replace(m.group(), translateHexColor(m.group(), from, to));
        return text;
    }

    /**
     * Formats all hex colors of specified format to display in minecraft
     *
     * @param text string to format
     * @param from format to find and convert from
     * @return formatted string
     */
    public static String formatHexColors(String text, HexColorFormat from) {
        return translateHexColors(text, from, HexColorFormat.FORMATTED);
    }

    /**
     * Formats all hex colors of any formatting (except default one)
     *
     * @param text string to format
     * @return formatted string
     */
    public static String formatAnyHexColors(String text) {
        String returnment = text;
        for (HexColorFormat format : HexColorFormat.nonDefaultValues())
            returnment = formatHexColors(returnment, format);
        return returnment;
    }

    /**
     * Formats all colors and formats in text, including hex colors
     * <ul>
     *     <li>{@link #formatFullLegacy(String)}</li>
     *     <li>{@link #formatAnyHexColors(String)}</li>
     * </ul>
     *
     * @param text string to format
     * @return formatted string
     */
    public static String formatEverything(String text) {
        return formatFullLegacy(formatAnyHexColors(text));
    }

    /**
     * Clears all unformatted legacy colors
     *
     * @param text string to clear colors from
     * @return string with no unformatted legacy colors
     */
    public static @NotNull String clearUnformattedLegacyColors(String text) {
        return clearExcludingHex(LEGACY_COLOR, text);
    }

    /**
     * Clears all formatted legacy colors
     *
     * @param text string to clear colors from
     * @return string with no formatted legacy colors
     */
    public static @NotNull String clearFormattedLegacyColors(String text) {
        return clearExcludingHex(FORMATTED_LEGACY_COLOR, text);
    }

    /**
     * Clears all unformatted legacy formats
     *
     * @param text string to clear formats from
     * @return string with no unformatted legacy formats
     */
    public static @NotNull String clearUnformattedLegacyFormats(@NotNull String text) {
        return text.replaceAll(LEGACY_FORMAT.pattern(), "");
    }

    /**
     * Clears all formatted legacy formats
     *
     * @param text string to clear formats from
     * @return string with no formatted legacy formats
     */
    public static @NotNull String clearFormattedLegacyFormats(@NotNull String text) {
        return text.replaceAll(FORMATTED_LEGACY_FORMAT.pattern(), "");
    }

    /**
     * Clears all unformatted legacy colors and formats
     *
     * @param text string to clear colors and formats from
     * @return string with no unformatted legacy colors and formats
     */
    public static @NotNull String clearUnformattedLegacy(String text) {
        return clearUnformattedLegacyFormats(clearUnformattedLegacyColors(text));
    }

    /**
     * Clears all formatted legacy colors and formats
     *
     * @param text string to clear colors and formats from
     * @return string with no formatted legacy colors and formats
     */
    public static @NotNull String clearFormattedLegacy(String text) {
        return clearFormattedLegacyFormats(clearFormattedLegacyColors(text));
    }

    /**
     * Clears all hex colors of specific type of formatting
     *
     * @param text   string to clear colors from
     * @param format format to clear colors of
     * @return string with no hex colors of specific type
     */
    public static @NotNull String clearHexColorsOfFormat(String text, @NotNull HexColorFormat format) {
        Matcher m = format.getPattern().matcher(text);
        while (m.find()) text = text.replace(m.group(), "");
        return text;
    }

    /**
     * Clears all hex colors of any type of formatting
     *
     * @param text string to clear colors from
     * @return string with no hex colors
     */
    public static @NotNull String clearHexColors(String text) {
        String returnment = text;
        for (HexColorFormat format : HexColorFormat.values())
            returnment = returnment.replaceAll(format.getPattern().pattern(), "");
        return returnment;
    }

    /**
     * Clears all formatted colors
     *
     * @param text string to clear colors from
     * @return string with no formatted colors
     */
    public static @NotNull String clearFormattedColors(@NotNull String text) {
        return text.replaceAll(FORMATTED_LEGACY_FULL.pattern(), "");
    }

    /**
     * Clears all unformatted colors
     *
     * @param text string to clear colors from
     * @return string with no unformatted colors
     */
    public static @NotNull String clearUnformattedColors(@NotNull String text) {
        return text.replaceAll(LEGACY_FULL.pattern(), "");
    }

    /**
     * Clears all colors, combines these methods:
     * <ul>
     *     <li>{@link #clearHexColors(String)}</li>
     *     <li>{@link #clearUnformattedColors(String)}</li>
     *     <li>{@link #clearFormattedColors(String)}</li>
     * </ul>
     *
     * @param text string to clear colors from
     * @return string with no unformatted colors
     */
    public static @NotNull String clearAllColors(String text) {
        return clearFormattedColors(clearUnformattedColors(clearHexColors(text)));
    }
}
