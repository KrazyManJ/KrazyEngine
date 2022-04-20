package me.KrazyManJ.KrazyEngine.Any;

@SuppressWarnings("unused")
public final class LogicGates {
    /**
     * Returns true if both values are true
     * <br><br>
     * <pre>
     * |---|---|-----|
     * | A | B | Out |
     * |---|---|-----|
     * | 0 | 0 |  0  |
     * | 1 | 0 |  0  |
     * | 0 | 1 |  0  |
     * | 1 | 1 |  1  |
     * |---|---|-----|
     * </pre>
     * @return value of gate
     */
    public static boolean AND(boolean a, boolean b){
        return a && b;
    }
    /**
     * Returns true if one value, or both of them are true
     * <br><br>
     * <pre>
     * |---|---|-----|
     * | A | B | Out |
     * |---|---|-----|
     * | 0 | 0 |  0  |
     * | 1 | 0 |  1  |
     * | 0 | 1 |  1  |
     * | 1 | 1 |  1  |
     * |---|---|-----|
     * </pre>
     * @return value of gate
     */
    public static boolean OR(boolean a, boolean b){
        return a || b;
    }
    /**
     * Returns true if single value is true only
     * <br><br>
     * <pre>
     * |---|---|-----|
     * | A | B | Out |
     * |---|---|-----|
     * | 0 | 0 |  0  |
     * | 1 | 0 |  1  |
     * | 0 | 1 |  1  |
     * | 1 | 1 |  0  |
     * |---|---|-----|
     * </pre>
     * @return value of gate
     */
    public static boolean XOR(boolean a, boolean b){
        return a != b;
    }
    /**
     * Negative AND gate. Returns true if one value is true and second one false or both of values are false
     * <br><br>
     * <pre>
     * |---|---|-----|
     * | A | B | Out |
     * |---|---|-----|
     * | 0 | 0 |  1  |
     * | 1 | 0 |  1  |
     * | 0 | 1 |  1  |
     * | 1 | 1 |  0  |
     * |---|---|-----|
     * </pre>
     * @return value of gate
     */
    public static boolean NAND(boolean a, boolean b){
        return !(a && b);
    }
    /**
     * Negative OR gate. Returns true only if both values are false
     * <br><br>
     * <pre>
     * |---|---|-----|
     * | A | B | Out |
     * |---|---|-----|
     * | 0 | 0 |  1  |
     * | 1 | 0 |  0  |
     * | 0 | 1 |  0  |
     * | 1 | 1 |  0  |
     * |---|---|-----|
     * </pre>
     * @return value of gate
     */
    public static boolean NOR(boolean a, boolean b){
        return !(a || b);
    }
    /**
     * Returns true only if both values are equal to each other
     * <br><br>
     * <pre>
     * |---|---|-----|
     * | A | B | Out |
     * |---|---|-----|
     * | 0 | 0 |  1  |
     * | 1 | 0 |  0  |
     * | 0 | 1 |  0  |
     * | 1 | 1 |  1  |
     * |---|---|-----|
     * </pre>
     * @return value of gate
     */
    public static boolean XNOR(boolean a, boolean b){
        return a == b;
    }

}
