package me.KrazyManJ.KrazyEngine.Any.ChatComponent.Target;

import org.bukkit.GameMode;
import org.bukkit.entity.EntityType;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Team;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

// TODO: finish documentation
/**
 * Creates Target Selector, which is string in format
 * <code>@&lt;variable&gt;[&lt;argument&gt;=&lt;value&gt;,&lt;argument&gt;=&lt;value&gt;,...]</code>.
 *
 * <p>
 * This class can be used in method {@link me.KrazyManJ.KrazyEngine.Any.ChatComponent.ComponentMaker#makeTarget(TargetSelectorBuilder) make(TargetSelectorBuilder)}
 * </p>
 *
 * @author KrazyManJ
 * @see <a href="https://minecraft.fandom.com/wiki/Target_selectors">Target Selectors Wiki page</a>
 */
@SuppressWarnings({"unused", "UnusedReturnValue"})
public final class TargetSelectorBuilder {

    private TargetSelectorVariable selector;
    private final HashMap<String, Object> values = new HashMap<>();
    private final HashMap<Objective, TargetRange> scoreValues = new HashMap<>();
    private final List<String> tagValues = new ArrayList<>();

    public TargetSelectorBuilder(TargetSelectorVariable target) {
        this.selector = target;
    }

    public TargetSelectorBuilder setSelector(TargetSelectorVariable selector) {
        this.selector = selector;
        return this;
    }

    private boolean hasNullValues() {
        return values.isEmpty() && scoreValues.isEmpty() && tagValues.isEmpty();
    }

    public String build() {
        if (hasNullValues()) return selector.selectorValue();
        List<String> components = new ArrayList<>();
        if (!values.isEmpty())
            components.add(values.entrySet().stream().map(e -> e.getKey() + "=" + e.getValue()).collect(Collectors.joining(",")));
        if (!scoreValues.isEmpty())
            components.add("scores={" + scoreValues.entrySet().stream().map(e -> e.getKey().getName() + "=" + e.getValue().toString()).collect(Collectors.joining(",")) + "}");
        if (!tagValues.isEmpty()) components.add("tag=" + String.join(",tag=", tagValues));
        return selector.selectorValue() + "["+String.join(",",components)+"]";
    }

    @Override
    public String toString() {
        return build();
    }

    //Position

    public TargetSelectorBuilder setPosition(double x, double y, double z) {
        values.put("x", x);
        values.put("y", y);
        values.put("z", z);
        return this;
    }

    //Distance

    public TargetSelectorBuilder setDistance(TargetRange blockRange) {
        values.put("distance", blockRange);
        return this;
    }

    //Volume

    public TargetSelectorBuilder setVolume(double x, double y, double z) {
        values.put("dx", x);
        values.put("dy", y);
        values.put("dz", z);
        return this;
    }

    //Scores

    public TargetSelectorBuilder addScoreValue(Objective objective, int value) {
        scoreValues.put(objective, new TargetRange(value));
        return this;
    }

    public TargetSelectorBuilder addScoreValue(Objective objective, int lowest, int highest) {
        scoreValues.put(objective, new TargetRange(lowest, highest));
        return this;
    }

    public HashMap<Objective, TargetRange> getScoreValues() {
        return scoreValues;
    }

    //Tags

    public TargetSelectorBuilder addTag(String tag) {
        tagValues.add(tag);
        return this;
    }

    public TargetSelectorBuilder addTag(String tag, boolean bool) {
        tagValues.add((bool ? "" : "!") + tag);
        return this;
    }

    public List<String> getTagValues() {
        return tagValues;
    }

    //Team

    public TargetSelectorBuilder setTeam(Team team) {
        values.put("team", team.getName());
        return this;
    }

    @Deprecated
    public TargetSelectorBuilder setTeam(String team) {
        values.put("team", team);
        return this;
    }

    //Limit and sorting

    public TargetSelectorBuilder setLimit(int limit) {
        values.put("limit", limit);
        return this;
    }

    public TargetSelectorBuilder setSortingPriority(SortSelection s) {
        values.put("sort", s.s());
        return this;
    }

    //Experience level

    public TargetSelectorBuilder setExpLevel(TargetRange level) {
        values.put("level", level);
        return this;
    }

    //Gamemode

    public TargetSelectorBuilder setGamemode(GameMode mode) {
        values.put("gamemode", mode.name().toLowerCase());
        return this;
    }

    public TargetSelectorBuilder setGamemode(GameMode mode, boolean bool) {
        values.put("gamemode", (bool ? "" : "!") + mode.name().toLowerCase());
        return this;
    }

    //Name

    public TargetSelectorBuilder setName(String name) {
        values.put("name", name);
        return this;
    }

    public TargetSelectorBuilder setName(String name, boolean bool) {
        values.put("name", (bool ? "" : "!") + name);
        return this;
    }

    //Vertical rotation

    public TargetSelectorBuilder setVerticalRotation(TargetRange degreeRange) {
        values.put("x_rotation", degreeRange);
        return this;
    }

    //Horizontal rotation

    public TargetSelectorBuilder setHorizontalRotation(TargetRange degreeRange) {
        values.put("y_rotation", degreeRange);
        return this;
    }

    //Type

    public TargetSelectorBuilder setType(EntityType type) {
        values.put("type", type.getKey().getKey());
        return this;
    }

    public TargetSelectorBuilder setType(EntityType type, boolean bool) {
        values.put("type", (bool ? "" : "!") + type.getKey().getKey());
        return this;
    }

    //Family: bedrock exclusive - skip

    //NBT

    public TargetSelectorBuilder setNbt(String nbt) {
        values.put("nbt", nbt);
        return this;
    }

    // TODO: Advancement

    // TODO: Predicate
    // https://minecraft.fandom.com/wiki/Predicate


    //Item: bedrock exclusive - skip
    //Permission: bedrock exclusive - skip
}
