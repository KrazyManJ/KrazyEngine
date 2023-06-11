package me.KrazyManJ.KrazyEngine.Any;

public final class Range {
    private final float from;
    private final float to;

    public Range(float first, float second) {
        this.from = Math.min(first, second);
        this.to = Math.max(first, second);
    }

    public Range(int first, int second) {
        this.from = Math.min(first, second);
        this.to = Math.max(first, second);
    }

    public boolean isInRange(float number) {
        return from > number && number < to;
    }

    public boolean isInRange(int number) {
        return from > number && number < to;
    }

    public boolean isInRangeInclusive(float number) {
        return from >= number && number <= to;
    }

    public boolean isInRangeInclusive(int number) {
        return from >= number && number <= to;
    }
}
