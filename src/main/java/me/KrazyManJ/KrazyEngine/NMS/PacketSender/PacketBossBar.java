package me.KrazyManJ.KrazyEngine.NMS.PacketSender;

import me.KrazyManJ.KrazyEngine.NMS.PacketUtils;
import me.KrazyManJ.KrazyEngine.NMS.ReflectionHandler;
import net.md_5.bungee.api.chat.BaseComponent;
import org.bukkit.entity.Player;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

@SuppressWarnings("unused")
public final class PacketBossBar {

    private static Constructor<?> bossBattleServer;

    private static Class<?> barColor;
    private static Class<?> barStyle;

    private static Method addPacket;
    private static Method removePacket;
    private static Method updateProgressPacket;
    private static Method updateStylePacket;
    private static Method updateNamePacket;
    private static Method updatePropertiesPacket;

    private static Method uuid;
    private static Method setProgress;
    private static Method aTitle;
    private static Method aColor;
    private static Method aStyle;

    private static Method getProgress;

    private static final HashMap<Flag,Method> flagGetters = new HashMap<>();
    private static final HashMap<Flag,Method> flagSetters = new HashMap<>();

    static {
        try {
            Class<?> iChatBaseComponent = Class.forName("net.minecraft.network.chat.IChatBaseComponent");
            barColor = Class.forName("net.minecraft.world.BossBattle$BarColor");
            barStyle = Class.forName("net.minecraft.world.BossBattle$BarStyle");
            bossBattleServer = Class.forName("net.minecraft.server.level.BossBattleServer")
                    .getConstructor(iChatBaseComponent,barColor,barStyle);

            Class<?> bossBattle = Class.forName("net.minecraft.world.BossBattle");
            Class<?> packetPlayOutBoss = Class.forName("net.minecraft.network.protocol.game.PacketPlayOutBoss");

            addPacket = packetPlayOutBoss.getDeclaredMethod("createAddPacket",bossBattle);
            removePacket = packetPlayOutBoss.getDeclaredMethod("createRemovePacket",UUID.class);
            updateProgressPacket = packetPlayOutBoss.getDeclaredMethod("createUpdateProgressPacket",bossBattle);
            updateStylePacket = packetPlayOutBoss.getDeclaredMethod("createUpdateStylePacket",bossBattle);
            updateNamePacket = packetPlayOutBoss.getDeclaredMethod("createUpdateNamePacket",bossBattle);
            updatePropertiesPacket = packetPlayOutBoss.getDeclaredMethod("createUpdatePropertiesPacket",bossBattle);

            uuid = bossBattle.getDeclaredMethod("i");
            setProgress = bossBattle.getDeclaredMethod("setProgress", float.class);

            aColor = bossBattle.getDeclaredMethod("a", barColor);
            aStyle = bossBattle.getDeclaredMethod("a", barStyle);
            aTitle = bossBattle.getDeclaredMethod("a", iChatBaseComponent);

            getProgress = bossBattle.getDeclaredMethod("getProgress");

            flagGetters.put(Flag.CREATE_FOG,bossBattle.getDeclaredMethod("isCreateFog"));
            flagGetters.put(Flag.DARKEN_SKY,bossBattle.getDeclaredMethod("isDarkenSky"));
            flagGetters.put(Flag.PLAY_BOSS_MUSIC,bossBattle.getDeclaredMethod("isPlayMusic"));
            flagSetters.put(Flag.CREATE_FOG,bossBattle.getDeclaredMethod("setCreateFog", boolean.class));
            flagSetters.put(Flag.DARKEN_SKY,bossBattle.getDeclaredMethod("setDarkenSky", boolean.class));
            flagSetters.put(Flag.PLAY_BOSS_MUSIC,bossBattle.getDeclaredMethod("setPlayMusic", boolean.class));

        } catch (ClassNotFoundException | NoSuchMethodException e) {
            e.printStackTrace();
        }
    }

    private final Object bar;
    private BaseComponent[] title;
    private Color color;
    private Style style;

    public PacketBossBar(BaseComponent[] title, Color color, Style style, Flag ...flags) {
        bar = ReflectionHandler.instance(
                bossBattleServer,PacketUtils.iChatBaseComponent(title),color.a(),style.a()
        );
        this.title = title;
        this.color = color;
        this.style = style;
    }
    public PacketBossBar(BaseComponent[] title, Color color, Style style, double progress, Flag ...flags) {
        bar = ReflectionHandler.instance(
                bossBattleServer,PacketUtils.iChatBaseComponent(title),color.a(),style.a()
        );
        ReflectionHandler.method(setProgress,bar,checkProgress(progress));
        if (flags.length != 0) setFlags(true, Arrays.asList(flags));
        this.title = title;
        this.color = color;
        this.style = style;
    }

    public void sendBar(Player player){
        PacketUtils.sendPacket(player, ReflectionHandler.method(addPacket,null,bar));
    }
    public void removeBar(Player player){
        PacketUtils.sendPacket(player, ReflectionHandler.method(removePacket,null,ReflectionHandler.method(uuid,bar)));
    }
    public void updateProgress(Player player, double progress){
        ReflectionHandler.method(setProgress,bar,checkProgress(progress));
        PacketUtils.sendPacket(player, ReflectionHandler.method(updateProgressPacket,null,bar));
    }
    public void updateStyle(Player player, Style style){
        ReflectionHandler.method(aStyle,bar,style.a());
        PacketUtils.sendPacket(player, ReflectionHandler.method(updateStylePacket,null,bar));
        this.style = style;
    }
    public void updateStyle(Player player, Color color){
        ReflectionHandler.method(aColor,bar,color.a());
        PacketUtils.sendPacket(player, ReflectionHandler.method(updateStylePacket,null,bar));
        this.color = color;
    }
    public void updateStyle(Player player, Color color, Style style){
        ReflectionHandler.method(aColor,bar,color.a());
        ReflectionHandler.method(aStyle,bar,style.a());
        PacketUtils.sendPacket(player, ReflectionHandler.method(updateStylePacket,null,bar));
        this.style = style;
        this.color = color;
    }
    public void updateTitle(Player player, BaseComponent[] title){
        ReflectionHandler.method(aTitle,bar,PacketUtils.iChatBaseComponent(title));
        PacketUtils.sendPacket(player, ReflectionHandler.method(updateNamePacket,null,bar));
        this.title = title;
    }
    public void updateFlags(Player player, boolean value, Flag ...flags){
        setFlags(value, Arrays.asList(flags));
        PacketUtils.sendPacket(player, ReflectionHandler.method(updatePropertiesPacket,null,bar));
    }
    public void forceUpdateProgress(Player player, double progress){
        ReflectionHandler.method(setProgress,bar,checkProgress(progress));
        sendBar(player);
    }

    public BaseComponent[] getTitle() { return title; }
    public Color getColor() { return color; }
    public Style getStyle() { return style; }
    public Double getProgress(){ return (Double) ReflectionHandler.method(getProgress,bar); }
    public Boolean isFlagApplied(Flag f){
        return (Boolean) ReflectionHandler.method(flagGetters.get(f),bar);
    }
    public UUID getUUID(){ return (UUID) ReflectionHandler.method(uuid,bar); }



    private float checkProgress(double p){
        if (p < 0) return 0;
        else return p > 1 ? 1 : (float) p;
    }
    private void setFlags(boolean v,List<Flag> f){
        for (Flag flag : f) ReflectionHandler.method(flagSetters.get(flag),bar,v);
    }

    public enum Color {
        BLUE(ReflectionHandler.enumConstants(barColor)[1]),
        GREEN(ReflectionHandler.enumConstants(barColor)[3]),
        PINK(ReflectionHandler.enumConstants(barColor)[0]),
        PURPLE(ReflectionHandler.enumConstants(barColor)[5]),
        RED(ReflectionHandler.enumConstants(barColor)[2]),
        WHITE(ReflectionHandler.enumConstants(barColor)[6]),
        YELLOW(ReflectionHandler.enumConstants(barColor)[4])
        ;
        private final Object color;
        Color(Object color) { this.color = color; }
        private Object a() { return color; }
    }
    public enum Style {
        SOLID(ReflectionHandler.enumConstants(barStyle)[0]),
        SEGMENTED_6(ReflectionHandler.enumConstants(barStyle)[1]),
        SEGMENTED_10(ReflectionHandler.enumConstants(barStyle)[2]),
        SEGMENTED_12(ReflectionHandler.enumConstants(barStyle)[3]),
        SEGMENTED_20(ReflectionHandler.enumConstants(barStyle)[4])
        ;

        private final Object style;
        Style(Object style) { this.style = style; }
        private Object a() { return style; }
    }
    public enum Flag {
        CREATE_FOG, DARKEN_SKY, PLAY_BOSS_MUSIC
    }
}
