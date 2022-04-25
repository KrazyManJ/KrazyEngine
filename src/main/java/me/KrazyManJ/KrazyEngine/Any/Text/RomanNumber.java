package me.KrazyManJ.KrazyEngine.Any.Text;

import me.KrazyManJ.KrazyEngine.Any.Regex.RegexList;
import org.jetbrains.annotations.NotNull;

import java.util.Arrays;
import java.util.HashMap;

@SuppressWarnings("unused")
public final class RomanNumber {

    @Deprecated private RomanNumber() {}

    public static String encode(int integer){
        StringBuilder roman = new StringBuilder();
        for(RomanLiteral literal : RomanLiteral.values()) {
            while(integer >= literal.getValue()) {
                integer -= literal.getValue();
                roman.append(literal);
            }
        }
        return roman.toString();
    }
    public static int decode(@NotNull String roman) {
        if (roman.length() == 0 || !isRoman(roman)) return -1;
        HashMap<Character, Integer> map = new HashMap<>();
        for (RomanLiteral r : RomanLiteral.getSubtractive()) map.put(r.toString().charAt(0),r.getValue());
        int result = map.get(roman.charAt(roman.length() - 1));
        for (int i = roman.length() - 2; i >= 0; i--) {
            if (map.get(roman.charAt(i)) >= map.get(roman.charAt(i + 1))) result += map.get(roman.charAt(i));
            else result -= map.get(roman.charAt(i));
        }
        return result;
    }
    public static boolean isRoman(@NotNull String roman){
        return roman.matches("^"+RegexList.romanNumeral.pattern()+"$");
    }

    private enum RomanLiteral {
        M(1000),
        CM(900),
        D(500),
        CD(400),
        C(100),
        XC(90),
        L(50),
        XL(40),
        X(10),
        IX(9),
        V(5),
        IV(4),
        I(1);

        private final int value;
        RomanLiteral(int value) { this.value = value; }
        public int getValue(){ return value; }
        public static RomanLiteral[] getSubtractive(){
            return Arrays.stream(values()).filter(f -> (f.ordinal() + 1) % 2 != 0).toList().toArray(new RomanLiteral[0]);
        }
        @Override public String toString() {return name();}
    }
}
