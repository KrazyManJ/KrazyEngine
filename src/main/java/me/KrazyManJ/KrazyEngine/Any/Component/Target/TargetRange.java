package me.KrazyManJ.KrazyEngine.Any.Component.Target;

@SuppressWarnings({"unused", "UnusedReturnValue"})
final class TargetRange {
    private Float lowest;
    private Float highest;

    public TargetRange(float lowest, float highest) throws NonRangeTargetException {
        if ( lowest > highest ) throw new NonRangeTargetException("Low value cannot be higher than high value!");
        this.lowest = lowest;
        this.highest = highest;
    }
    public TargetRange(float range) {this.lowest = range;}

    public boolean hasSecond(){return highest != null;}

    public Float getLowest() {return lowest;}
    public TargetRange setLowest(Float lowest) throws NonRangeTargetException {
        if (lowest > highest) throw new NonRangeTargetException("Lowest value cannot be higher than highest value!");
        this.lowest = lowest;return this;
    }
    public Float getHighest() {return highest;}
    public TargetRange setHighest(Float highest) throws NonRangeTargetException {
        if (highest < lowest) throw new NonRangeTargetException("Highest value cannot be lower than lowest value!");
        this.highest = highest;return this;
    }

    @Override public String toString() {return hasSecond() ? lowest.toString() : lowest +".."+ highest;}
}
