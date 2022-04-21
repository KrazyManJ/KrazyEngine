package me.KrazyManJ.KrazyEngine.Any.Regex;

import org.intellij.lang.annotations.Language;

public record RegexGroupMatch(String group, @Language("RegExp") String value) {}
