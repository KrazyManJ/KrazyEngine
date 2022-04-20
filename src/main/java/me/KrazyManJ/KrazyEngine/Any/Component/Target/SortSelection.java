package me.KrazyManJ.KrazyEngine.Any.Component.Target;

@SuppressWarnings("unused")
public enum SortSelection {
    NEAREST,
    FURTHEST,
    RANDOM,
    ARBITRARY;
    public String s(){ return this.toString().toLowerCase(); }
}
