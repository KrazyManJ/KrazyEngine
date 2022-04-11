package me.KrazyManJ.KrazyEngine.Text;

import net.md_5.bungee.api.ChatColor;

public class Gradient {
    public static String rgb(String text, String color1, String color2){
        int[] rgb1 = {Integer.valueOf( color1.substring( 1, 3 ), 16 ), Integer.valueOf( color1.substring( 3, 5 ), 16 ), Integer.valueOf( color1.substring( 5, 7 ), 16 )};
        int[] rgb2 = {Integer.valueOf( color2.substring( 1, 3 ), 16 ), Integer.valueOf( color2.substring( 3, 5 ), 16 ), Integer.valueOf( color2.substring( 5, 7 ), 16 )};
        float[] rgbP = new float[3];
        for (int j = 0; j < 3; j++) rgbP[j] = rgb1[j] < rgb2[j] ? (rgb2[j]-rgb1[j])/(float)text.length() : (rgb1[j]-rgb2[j])/(float)text.length();
        String result = "";
        String lastFormat = "";
        for (int i = 0; i < text.length(); i++){
            if (text.charAt(i) == '&' && "klmno".contains(String.valueOf(text.charAt(i+1)))){ lastFormat += text.charAt(i)+""+text.charAt(i+1);i++;i++; }
            else if (text.charAt(i) == '&' && text.charAt(i+1) == 'r'){ lastFormat = "";i++;i++; }
            for (int j = 0; j < 3; j++) {
                rgb1[j] = rgb1[j] < rgb2[j] ? (Math.round(rgb1[j] + rgbP[j])) : (Math.round(rgb1[j]-rgbP[j]));
                if (rgb1[j] < 0) rgb1[j] = 0;
                else if (rgb1[j] > 255) rgb1[j] = 255;
            }
            result += ChatColor.of(String.format("#%02x%02x%02x", rgb1[0], rgb1[1], rgb1[2]))+lastFormat+text.charAt(i);
        }
        return ChatColor.translateAlternateColorCodes('&',result);
    }
}
