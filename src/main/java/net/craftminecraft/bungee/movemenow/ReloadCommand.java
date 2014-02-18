package net.craftminecraft.bungee.movemenow;

import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.plugin.Command;

public class ReloadCommand extends Command {
    MoveMeNow plugin;

    public ReloadCommand(MoveMeNow plugin) {
        super("mmn", "movemenow.admin", "movemenow");
        this.plugin = plugin;
    }

    @Override
    public void execute(CommandSender sender, String[] args) {
        TextComponent message = new TextComponent();
        if (args.length != 1) {
            message.setText("/mmn reload");
            sender.sendMessage(message);
            return;
        }
        switch (args[0]) {
            case "reload":
                MoveMeNow.loadConfig();
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
