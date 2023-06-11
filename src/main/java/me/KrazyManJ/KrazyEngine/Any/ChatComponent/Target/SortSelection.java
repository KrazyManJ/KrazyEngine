package me.KrazyManJ.KrazyEngine.Any.ChatComponent.Target;

@SuppressWarnings("unused")
public enum SortSelection {
    NEAREST,
    FURTHEST,
    RANDOM,
    ARBITRARY;
    public String s(){ return this.toString().toLowerCase(); }
}
