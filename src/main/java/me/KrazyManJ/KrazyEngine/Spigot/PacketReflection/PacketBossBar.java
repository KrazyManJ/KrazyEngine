package me.KrazyManJ.KrazyEngine.Spigot.PacketReflection;

import net.md_5.bungee.api.chat.BaseComponent;
import net.md_5.bungee.chat.ComponentSerializer;
import net.minecraft.network.chat.IChatBaseComponent;
import net.minecraft.network.protocol.game.PacketPlayOutBoss;
import net.minecraft.server.level.BossBattleServer;
import net.minecraft.world.BossBattle;
import org.bukkit.entity.Player;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

@SuppressWarnings("unused")
public final class PacketBossBar {

    private final BossBattle bar;
    private BaseComponent[] title;
    private Color color;
    private Style style;

    public PacketBossBar(BaseComponent[] title, Color color, Style style, Flag ...flags) {
        bar = new BossBattleServer(
                IChatBaseComponent.ChatSerializer.a(ComponentSerializer.toString(title)),
                color.a(),
                style.a()
        );
        this.title = title;
        this.color = color;
        this.style = style;
    }
    public PacketBossBar(BaseComponent[] title, Color color, Style style, double progress, Flag ...flags) {
        bar = new BossBattleServer(
                IChatBaseComponent.ChatSerializer.a(ComponentSerializer.toString(title)),
                color.a(),
                style.a()
        );
        bar.setProgress(checkProgress(progress));
        if (flags.length != 0) setFlags(true, Arrays.asList(flags));
        this.title = title;
        this.color = color;
        this.style = style;
    }

    public void sendBar(Player player){
        PacketUtils.sendPacket(player, PacketPlayOutBoss.createAddPacket(bar));
    }
    public void removeBar(Player player){
        PacketUtils.sendPacket(player, PacketPlayOutBoss.createRemovePacket(bar.i()));
    }
    public void updateProgress(Player player, double progress){
        bar.setProgress(checkProgress(progress));
        PacketUtils.sendPacket(player, PacketPlayOutBoss.createUpdateProgressPacket(bar));
    }
    public void updateStyle(Player player, Style style){
        bar.a(style.a());
        PacketUtils.sendPacket(player, PacketPlayOutBoss.createUpdateStylePacket(bar));
        this.style = style;
    }
    public void updateStyle(Player player, Color color){
        bar.a(color.a());
        PacketUtils.sendPacket(player, PacketPlayOutBoss.createUpdateStylePacket(bar));
        this.color = color;
    }
    public void updateStyle(Player player, Color color, Style style){
        bar.a(color.a());
        bar.a(style.a());
        PacketUtils.sendPacket(player, PacketPlayOutBoss.createUpdateStylePacket(bar));
        this.style = style;
        this.color = color;
    }
    public void updateTitle(Player player, BaseComponent[] title){
        bar.a(IChatBaseComponent.ChatSerializer.a(ComponentSerializer.toString(title)));
        PacketUtils.sendPacket(player, PacketPlayOutBoss.createUpdateNamePacket(bar));
        this.title = title;
    }
    public void updateFlags(Player player, boolean value, Flag ...flags){
        setFlags(value, Arrays.asList(flags));
        PacketUtils.sendPacket(player, PacketPlayOutBoss.createUpdatePropertiesPacket(bar));
    }

    public BaseComponent[] getTitle() { return title; }
    public Color getColor() { return color; }
    public Style getStyle() { return style; }
    public double getProgress(){ return bar.getProgress(); }
    public boolean isFlagApplied(Flag f){
        switch (f){
            case CREATE_FOG -> { return bar.isCreateFog(); }
            case DARKEN_SKY -> { return bar.isDarkenSky(); }
            case PLAY_BOSS_MUSIC -> { return bar.isPlayMusic(); }
            default -> { return false; }
        }
    }
    public UUID getUUID(){ return bar.i(); }



    private float checkProgress(double p){
        if (p < 0) return 0;
        else return p > 1 ? 1 : (float) p;
    }
    private void setFlags(boolean v,List<Flag> f){
        if (f.contains(Flag.CREATE_FOG)) bar.setCreateFog(v);
        else if (f.contains(Flag.DARKEN_SKY)) bar.setDarkenSky(v);
        else if (f.contains(Flag.PLAY_BOSS_MUSIC)) bar.setPlayMusic(v);
    }

    public enum Color {
        BLUE(BossBattle.BarColor.b),
        GREEN(BossBattle.BarColor.d),
        PINK(BossBattle.BarColor.a),
        PURPLE(BossBattle.BarColor.f),
        RED(BossBattle.BarColor.c),
        WHITE(BossBattle.BarColor.g),
        YELLOW(BossBattle.BarColor.e)
        ;
        private final BossBattle.BarColor color;
        Color(BossBattle.BarColor color) { this.color = color; }
        private BossBattle.BarColor a() { return color; }
    }
    public enum Style {
        SOLID(BossBattle.BarStyle.a),
        SEGMENTED_6(BossBattle.BarStyle.b),
        SEGMENTED_10(BossBattle.BarStyle.c),
        SEGMENTED_12(BossBattle.BarStyle.d),
        SEGMENTED_20(BossBattle.BarStyle.e)
        ;

        private final BossBattle.BarStyle style;
        Style(BossBattle.BarStyle style) { this.style = style; }
        private BossBattle.BarStyle a() { return style; }
    }
    public enum Flag {
        CREATE_FOG, DARKEN_SKY, PLAY_BOSS_MUSIC
    }
}
