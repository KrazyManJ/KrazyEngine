package me.KrazyManJ.KrazyEngine.Any.Component.Target;

import org.bukkit.GameMode;
import org.bukkit.entity.EntityType;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Team;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

// https://minecraft.fandom.com/wiki/Target_selectors#Selecting_targets_by_distance

@SuppressWarnings({"unused", "UnusedReturnValue"})
public final class TargetBuilder {
    private TargetSelectorVariable selector;
    private final HashMap<String, Object> values = new HashMap<>();
    private final HashMap<Objective, TargetRange> scoreValues = new HashMap<>();
    private final List<String> tagValues = new ArrayList<>();
    private final HashMap<EntityFamily, Boolean> families = new HashMap<>();

    public TargetBuilder(TargetSelectorVariable target) {this.selector = target; }
    public TargetBuilder setSelector(TargetSelectorVariable selector) { this.selector = selector; return this;}

    private boolean hasNullValues(){
        return values.isEmpty() && scoreValues.isEmpty() && tagValues.isEmpty() && families.isEmpty();
    }

    public String build(){
        String args = "";
        if (!hasNullValues()){
            args += "[";
            if (!values.isEmpty())
                args += values.entrySet().stream().map(e -> e.getKey()+"="+e.getValue()).collect(Collectors.joining(","))+",";
            if (!scoreValues.isEmpty())
                args += "scores={"+scoreValues.entrySet().stream().map(e -> e.getKey().getName()+"="+e.getValue().toString()).collect(Collectors.joining(","))+"},";
            if (!tagValues.isEmpty()) args += "tag="+String.join(",tag=",tagValues)+",";
            if (!families.isEmpty())
                args += families.entrySet().stream().map(e -> "family="+(e.getValue() ? "" : "!")+e.getKey().name().toLowerCase()).collect(Collectors.joining(","))+",";
            args = args.substring(args.length()-1);
            args += "]";
        }
        return selector.selectorValue()+args;
    }
    @Override public String toString() {return build();}

    public TargetBuilder setXYZ(double x, double y, double z){ values.put("x",x);values.put("y",y);values.put("z",z);return this;}
    public TargetBuilder setDistance(float distance){values.put("distance",distance);return this;}
    public TargetBuilder setDistance(float distanceFrom,float distanceTo){
        try { values.put("distance",new TargetRange(distanceFrom,distanceTo).toString()); } catch (NonRangeTargetException e) {e.printStackTrace();}return this;
    }
    public TargetBuilder setVolume(double x, double y, double z){values.put("dx",x);values.put("dy",y);values.put("dz",z);return this;}
    public TargetBuilder addScoreValue(Objective objective, int value){
        scoreValues.put(objective,new TargetRange(value));
        return this;
    }
    public TargetBuilder addScoreValue(Objective objective, int lowest,int highest){
        try {scoreValues.put(objective,new TargetRange(lowest,highest));} catch (NonRangeTargetException e) {e.printStackTrace();}
        return this;
    }
    public HashMap<Objective, TargetRange> getScoreValues(){ return scoreValues; }
    public TargetBuilder addTag(String tag){ tagValues.add(tag); return this; }
    public TargetBuilder addTags(String ...tags) { tagValues.addAll(List.of(tags)); return this;}
    public TargetBuilder addTag(String tag,boolean bool){ tagValues.add((bool ? "" : "!")+tag); return this; }
    public List<String> getTagValues(){ return tagValues; }
    public TargetBuilder setTeam(Team team){values.put("team",team.getName());return this;}
    @Deprecated public TargetBuilder setTeam(String team){values.put("team",team);return this;}
    public TargetBuilder setLimit(int limit){values.put("limit",limit);return this;}
    public TargetBuilder setSortingPriority(SortSelection s){values.put("sort",s.s());return this;}
    public TargetBuilder setExpLevel(int level){values.put("level",level);return this;}
    public TargetBuilder setGamemode(GameMode mode){values.put("gamemode",mode.name().toLowerCase());return this;}
    public TargetBuilder setGamemode(GameMode mode,boolean bool){values.put("gamemode",(bool ? "" : "!")+mode.name().toLowerCase());return this;}
    public TargetBuilder setName(String name){values.put("name",name);return this;}
    public TargetBuilder setName(String name,boolean bool){values.put("name",(bool ? "" : "!")+name);return this;}
    public TargetBuilder setVerticalRotation(float degree){values.put("x_rotation",new TargetRange(degree));return this;}
    public TargetBuilder setVerticalRotation(float degreeFrom,float degreeTo){
        try { values.put("x_rotation",new TargetRange(degreeFrom, degreeTo).toString()); } catch (NonRangeTargetException e) {e.printStackTrace();}return this;
    }
    public TargetBuilder setHorizontalRotation(TargetRange degreeRange){values.put("y_rotation",this);return this;}
    public TargetBuilder setType(EntityType type){values.put("type",type.getKey().getKey());return this;}
    public TargetBuilder setType(EntityType type,boolean bool){ values.put("type",(bool ? "" : "!")+type.getKey().getKey());return this; }
    public TargetBuilder addFamily(EntityFamily family){ families.put(family,false); return this; }
    public TargetBuilder addFamily(EntityFamily family, boolean bool){ families.put(family,bool); return this; }
    public HashMap<EntityFamily, Boolean> getFamilies() { return families; }
    public TargetBuilder setNbt(String nbt){ values.put("nbt",nbt);return this; }
    //Advancement selector
    //By items selection
}
