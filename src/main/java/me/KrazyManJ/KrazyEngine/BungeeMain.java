package me.KrazyManJ.KrazyEngine;

import me.KrazyManJ.KrazyEngine.Any.Text.Gradient;
import net.md_5.bungee.api.ServerPing;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.event.ProxyPingEvent;
import net.md_5.bungee.api.plugin.Listener;
import net.md_5.bungee.api.plugin.Plugin;
import net.md_5.bungee.event.EventHandler;

@SuppressWarnings("unused")
public final class BungeeMain extends Plugin implements Listener {

    @Override
    public void onEnable() {
        super.onEnable();
        getProxy().getPluginManager().registerListener(this,this);
    }

    @Override
    public void onDisable() {
        super.onDisable();
    }

    @EventHandler
    public void on(ProxyPingEvent e){
        ServerPing ping = e.getResponse();
        ping.setDescriptionComponent(new TextComponent(TextComponent.fromLegacyText(
                Gradient.rgb("Lorem","#ff0000","#ffff00")
                +"\n"
                +Gradient.rgb("Test &lstring &ngradient&r test","#ff0000","#ffff00")
        )));
        e.setResponse(ping);
    }
}
