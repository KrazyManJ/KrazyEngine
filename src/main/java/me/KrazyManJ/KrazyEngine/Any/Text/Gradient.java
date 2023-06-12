package me.KrazyManJ.KrazyEngine.Any.Text;

import me.KrazyManJ.KrazyEngine.Any.MathUtils;
import org.jetbrains.annotations.NotNull;

import java.awt.*;

/**
 * Creates text gradients
 *
 * @author KrazyManJ
 */
@SuppressWarnings("unused")
public final class Gradient {

    private Gradient() {
    }

    /**
     * Makes text gradient in rgb model, this model adds/subtracts each color (red, green, blue) in proportion
     * related to text length.
     *
     * <p>
     * Also supports legacy formatting <code>&amp;[klmnor]</code>, where <code>r</code> resets legacy format only
     * </p>
     *
     * @param text       Text to make gradient text with
     * @param startColor Color to start gradient with
     * @param endColor   Color to end gradient with
     * @return Text with gradient
     * @author KrazyManJ
     */
    public static @NotNull String rgb(String text, Color startColor, Color endColor) {
        int[] rgb1 = {startColor.getRed(), startColor.getGreen(), startColor.getBlue()};
        int[] rgb2 = {endColor.getRed(), endColor.getGreen(), endColor.getBlue()};
        float[] rgbP = new float[3];
        for (int j = 0; j < 3; j++)
            rgbP[j] = (rgb1[j] < rgb2[j] ? rgb2[j] - rgb1[j] : rgb1[j] - rgb2[j]) / ((float) ColorTranslator.clearAllColors(text).length() - 1);
        StringBuilder result = new StringBuilder();
        String lastFormat = "";
        for (int i = 0; i < text.length(); i++) {
            if ("&§".contains(String.valueOf(text.charAt(i))) && "klmno".contains(String.valueOf(text.charAt(i + 1)))) {
                lastFormat += text.charAt(i) + "" + text.charAt(i + 1);
                i++;
            } else if ("&§".contains(String.valueOf(text.charAt(i))) && text.charAt(i + 1) == 'r') {
                lastFormat = "";
                i++;
            } else {
                if (i > 0) {
                    for (int j = 0; j < 3; j++)
                        rgb1[j] = MathUtils.minmax(Math.round(rgb1[j] < rgb2[j] ? rgb1[j] + rgbP[j] : rgb1[j] - rgbP[j]), 0, 255);
                }
                result.append(String.format("&#%02x%02x%02x", rgb1[0], rgb1[1], rgb1[2]))
                        .append(lastFormat)
                        .append(text.charAt(i));
            }
        }
        return ColorTranslator.formatEverything(result.toString());
    }

    /**
     * Makes text gradient in rgb model, this model adds/subtracts each color (red, green, blue) in proportion
     * related to text length.
     *
     * <p>
     * Also supports legacy formatting <code>&amp;[klmnor]</code>, where <code>r</code> resets legacy format only
     * </p>
     *
     * @param text       Text to make gradient text with
     * @param startColor Color to start gradient with
     * @param endColor   Color to end gradient with
     * @return Text with gradient
     * @author KrazyManJ
     */
    public static @NotNull String rgb(String text, String startColor, String endColor) {
        return rgb(text, Color.decode(startColor), Color.decode(endColor));
    }
}
