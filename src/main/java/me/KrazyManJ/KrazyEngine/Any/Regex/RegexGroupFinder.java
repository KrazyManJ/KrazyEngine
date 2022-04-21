package me.KrazyManJ.KrazyEngine.Any.Regex;

import org.intellij.lang.annotations.Language;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;


@SuppressWarnings("unused")
public final class RegexGroupFinder {

    private Pattern pattern;
    private final String defaultGroupName;
    private final HashMap<String, String> groups = new HashMap<>();

    public RegexGroupFinder(String defaultGroupName) {
        this.defaultGroupName = defaultGroupName;
    }
    public RegexGroupFinder addGroup(String name, @Language("RegExp") String regex){
        groups.put(name,regex);
        return this;
    }
    public Pattern buildPattern(){
        if (pattern == null || !pattern.pattern().equals(buildRegex())) pattern = Pattern.compile(buildRegex());
        return pattern;
    }
    public String buildRegex(){
        return groups.size() == 0
                ? "(?<"+defaultGroupName+">.+)"
                : groups.entrySet().stream().map(t -> "(?<"+t.getKey()+">"+t.getValue()+")")
                        .collect(Collectors.joining("|"))+
                        "|(?<"+defaultGroupName+">(?:(?!"+String.join("|",groups.values())+").)+)"
        ;
    }

    public List<RegexGroupMatch> match(String text){
        List<RegexGroupMatch> r = new ArrayList<>();
        Matcher m = buildPattern().matcher(text);
        while (m.find()){
            for (String g : Stream.concat(groups.keySet().stream(), Stream.of(defaultGroupName)).toList()){
                if (m.group(g) != null) {
                    r.add(new RegexGroupMatch(g,m.group(g)));
                    break;
                }
            }
        }
        return r;
    }

}
