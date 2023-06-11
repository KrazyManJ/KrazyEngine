package me.KrazyManJ.KrazyEngine.Any;

/**
 * Contains all logic gates functions. Logic gates are logical functions that return
 * logical response (<code>Out</code>) based on two inputs (<code>A</code>, <code>B</code>)
 */
@SuppressWarnings("unused")
public final class LogicGates {

    private LogicGates() {

    }

    /**
     * Returns true if both values are true.
     * <br>
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
     *
     * @return value of gate
     */
    public static boolean AND(boolean a, boolean b) {
        return a && b;
    }

    /**
     * Returns true if one value, or both of them are true.
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
     *
     * @return value of gate
     */
    public static boolean OR(boolean a, boolean b) {
        return a || b;
    }

    /**
     * Returns true if single value is true only.
     * <br>
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
     *
     * @return value of gate
     */
    public static boolean XOR(boolean a, boolean b) {
        return a != b;
    }

    /**
     * Negative {@link #AND} gate - returns true if one value is true and second one false or both of values are false.
     * <br>
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
     *
     * @return value of gate
     */
    public static boolean NAND(boolean a, boolean b) {
        return !(a && b);
    }

    /**
     * Negative {@link #OR} gate - returns true only if both values are false.
     * <br>
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
     *
     * @return value of gate
     */
    public static boolean NOR(boolean a, boolean b) {
        return !(a || b);
    }

    /**
     * Returns true only if both values are equal to each other.
     * <br>
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
     *
     * @return value of gate
     */
    public static boolean XNOR(boolean a, boolean b) {
        return a == b;
    }

}
