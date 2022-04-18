package me.KrazyManJ.KrazyEngine.Any.Regex;

import org.intellij.lang.annotations.Language;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


@SuppressWarnings("unused")
public class RegexGroupFinder {

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
        if (groups.size() == 0) return Pattern.compile("(?<"+defaultGroupName+">.+)");
        List<String> grp = new ArrayList<>();
        for (String key : groups.keySet()) grp.add("(?<"+key+">"+groups.get(key)+")");
        return Pattern.compile(String.join("|",grp)+"|(?<"+defaultGroupName+">(?:(?!"+String.join("|",groups.values())+").)+)");
    }

    public List<GroupMatch> match(String text){
        List<GroupMatch> r = new ArrayList<>();

        Matcher m = buildPattern().matcher(text);
        while (m.find()){
            for (String g : groups.keySet()){
                if (m.group(defaultGroupName) != null) {
                    r.add(new GroupMatch(defaultGroupName,m.group(defaultGroupName)));
                    break;
                }
                if (m.group(g) != null) {
                    r.add(new GroupMatch(g,m.group(g)));
                    break;
                }
            }
        }
        return r;
    }
}
