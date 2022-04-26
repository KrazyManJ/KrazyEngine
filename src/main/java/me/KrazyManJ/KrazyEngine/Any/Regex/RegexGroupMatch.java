package me.KrazyManJ.KrazyEngine.Any.Regex;

import org.intellij.lang.annotations.Language;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;

@SuppressWarnings("unused")
public final class RegexGroupMatch {
    private final String group;
    private final String value;

    public RegexGroupMatch(@NotNull String group,@NotNull String value) {
        this.group = group;
        this.value = value;
    }
    public String getGroup() { return group; }
    public String getValue() { return value; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RegexGroupMatch that = (RegexGroupMatch) o;
        return group.equals(that.group) && value.equals(that.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(group, value);
    }
}
