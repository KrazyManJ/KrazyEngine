package me.KrazyManJ.KrazyEngine.Text;

@SuppressWarnings("unused")
public final class Style {
    public static String fancyText(String text){
        return text.replaceAll("a", "ᴀ")
                .replaceAll("b", "ʙ").replaceAll("c", "ᴄ")
                .replaceAll("d", "ᴅ").replaceAll("e", "ᴇ")
                .replaceAll("f", "ꜰ").replaceAll("g", "ɢ")
                .replaceAll("h", "ʜ").replaceAll("i", "ɪ")
                .replaceAll("j", "ᴊ").replaceAll("k", "ᴋ")
                .replaceAll("l", "ʟ").replaceAll("m", "ᴍ")
                .replaceAll("n", "ɴ").replaceAll("o", "ᴏ")
                .replaceAll("p", "ᴘ").replaceAll("r", "ʀ")
                .replaceAll("s", "ꜱ").replaceAll("t", "ᴛ")
                .replaceAll("u", "ᴜ").replaceAll("v", "ᴠ")
                .replaceAll("w", "ᴡ").replaceAll("y", "ʏ")
                .replaceAll("z", "ᴢ");
    }
}
