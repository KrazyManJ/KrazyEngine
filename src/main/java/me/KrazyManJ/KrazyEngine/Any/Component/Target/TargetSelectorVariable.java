package me.KrazyManJ.KrazyEngine.Any.Component.Target;

/**
 * Selector variable used in {@link TargetSelectorBuilder} to determine, which kind of player or entity its targeting
 */
@SuppressWarnings("unused")
public enum TargetSelectorVariable {
    NEAREST_PLAYER("@p"),
    RANDOM_PLAYER("@r"),
    ALL_PLAYERS("@a"),
    ALL_ENTITIES("@e"),
    ENTITY_EXECUTING_THE_COMMAND("@s");
    final String sv;

    public String selectorValue() {
        return sv;
    }

    TargetSelectorVariable(String sv) {
        this.sv = sv;
    }
}
