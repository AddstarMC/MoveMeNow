package net.craftminecraft.bungee.movemenow;

import java.util.Iterator;
import java.util.List;

import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.chat.BaseComponent;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.config.ServerInfo;

import net.md_5.bungee.api.plugin.Listener;
import net.md_5.bungee.api.event.ServerKickEvent;
import net.md_5.bungee.api.AbstractReconnectHandler;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.event.EventHandler;

public class PlayerListener implements Listener {

    MoveMeNow plugin;

    public PlayerListener(MoveMeNow plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onServerKickEvent(ServerKickEvent ev) {
        ServerInfo kickedFrom;

        if (ev.getPlayer().getServer() != null)
            kickedFrom = ev.getPlayer().getServer().getInfo();
        else if (this.plugin.getProxy().getReconnectHandler() != null) // If first server and recohandler
            kickedFrom = this.plugin.getProxy().getReconnectHandler().getServer(ev.getPlayer());
        else {  // If first server and no recohandler
            kickedFrom = AbstractReconnectHandler.getForcedHost(ev.getPlayer().getPendingConnection());
            if (kickedFrom == null) // Can still be null if vhost is null...
                kickedFrom = ProxyServer.getInstance().getServerInfo(ev.getPlayer().getPendingConnection().getListener().getDefaultServer());
        }

        ServerInfo kickTo;

        if (MoveMeNow.getConfig().getString("servername").equals("reconnect"))
            kickTo = plugin.getProxy().getReconnectHandler().getServer(ev.getPlayer());
        else
            kickTo = this.plugin.getProxy().getServerInfo(MoveMeNow.getConfig().getString("servername"));

        if (kickedFrom != null && kickedFrom.equals(kickTo))
            return;

        String reason = BaseComponent.toLegacyText(ev.getKickReasonComponent());
        String[] moveMsg = MoveMeNow.getConfig().getString("message").replace("%kickmsg%", reason).split("\n");

        List<String> keywords = MoveMeNow.getConfig().getStringList("list");
        if (MoveMeNow.getConfig().getString("mode").equals("whitelist")) {
            for (String keyword : keywords)
                if (reason.contains(keyword)) {
                    ev.setCancelled(true);
                    ev.setCancelServer(kickTo);
                    if (!(moveMsg.length == 1 && moveMsg[0].equals("")))
                        for (String line : moveMsg)
                            ev.getPlayer().sendMessage(TextComponent.fromLegacyText(
                                    ChatColor.translateAlternateColorCodes('&' ,line)));
                    break; // no need to keep this up !
                }
        } else {
            for (String keyword : keywords)
                if (reason.contains(keyword))
                    return;
            ev.setCancelled(true);
            ev.setCancelServer(kickTo);
            if (!(moveMsg.length == 1 && moveMsg[0].equals("")))
                for (String line : moveMsg)
                    ev.getPlayer().sendMessage(TextComponent.fromLegacyText(
                            ChatColor.translateAlternateColorCodes('&', line)));
        }
    }
}
