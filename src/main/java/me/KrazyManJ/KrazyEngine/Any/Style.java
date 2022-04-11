package me.KrazyManJ.KrazyEngine.Any;

@SuppressWarnings("unused")
public final class Style {

    @Deprecated private Style() {}

    public static String fancyText(String text){
        return text.replaceAll("a", "?")
                .replaceAll("b", "?").replaceAll("c", "?")
                .replaceAll("d", "?").replaceAll("e", "?")
                .replaceAll("f", "?").replaceAll("g", "?")
                .replaceAll("h", "?").replaceAll("i", "?")
                .replaceAll("j", "?").replaceAll("k", "?")
                .replaceAll("l", "?").replaceAll("m", "?")
                .replaceAll("n", "?").replaceAll("o", "?")
                .replaceAll("p", "?").replaceAll("r", "?")
                .replaceAll("s", "?").replaceAll("t", "?")
                .replaceAll("u", "?").replaceAll("v", "?")
                .replaceAll("w", "?").replaceAll("y", "?")
                .replaceAll("z", "?");
    }
}
