package net.craftminecraft.bungee.movemenow;

import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.plugin.Command;

public class ReloadCommand extends Command {
    MoveMeNow plugin;

    public ReloadCommand(MoveMeNow plugin) {
        super("mmn", "movemenow.admin");
        this.plugin = plugin;
    }

    @Override
    public void execute(CommandSender sender, String[] args) {
        if (args.length != 1) {
            sender.sendMessage(new TextComponent("Please use /mmn reload."));
        }
        switch (args[0]) {
            case "reload":
                plugin.loadConfig();
                message.setColor(ChatColor.GREEN);
                message.setText("[MoveMeNow] Config reloaded");
                sender.sendMessage(message);
                break;
            default:
                message.setText("/mmn reload");
                sender.sendMessage(message);
                break;
        }
    }
}