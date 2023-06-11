package me.KrazyManJ.KrazyEngine.Any.Component.Target;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;


/**
 * Class used in {@link TargetSelectorBuilder} to define ranges
 *
 * @author KrazyManJ
 */
@SuppressWarnings({"unused", "UnusedReturnValue"})
final class TargetRange {
    private @NotNull Float lowest;
    private @Nullable Float highest;

    /**
     * Creates range of from-to [lowest..highest]
     *
     * @param lowest  lowest value in range
     * @param highest highest value in range
     * @throws NonRangeTargetException When lowest parameter is higher than highest parameter
     * @author KrazyManJ
     */
    public TargetRange(float lowest, float highest) {
        if (lowest > highest) throw new NonRangeTargetException("Low value cannot be higher than high value!");
        this.lowest = lowest;
        this.highest = highest;
    }

    /**
     * Creates range of specific value (param of {@link TargetSelectorBuilder} needs to that exact value
     *
     * @param value value of
     * @author KrazyManJ
     */
    public TargetRange(float value) {
        this.lowest = value;
    }

    public boolean isExact() {
        return highest == null;
    }

    public @NotNull Float getLowest() {
        return lowest;
    }

    public TargetRange setLowest(float lowest) {
        if (!isExact() && lowest > highest) throw new NonRangeTargetException("Lowest value cannot be higher than highest value!");
        this.lowest = lowest;
        return this;
    }

    public @Nullable Float getHighest() {
        return highest;
    }

    public TargetRange setHighest(@Nullable Float highest) {
        if (highest == null){
            this.highest = null;
            return this;
        }
        if (highest < lowest) throw new NonRangeTargetException("Highest value cannot be lower than lowest value!");
        this.highest = highest;
        return this;
    }

    @Override
    public String toString() {
        return isExact() ? lowest.toString() : lowest + ".." + highest;
    }

    private static final class NonRangeTargetException extends RuntimeException {
        NonRangeTargetException(String message) {
            super(message);
        }
    }
}
