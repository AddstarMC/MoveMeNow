package net.craftminecraft.bungee.movemenow;

import com.google.common.collect.ObjectArrays;
import java.util.ArrayList;
import java.util.List;

import net.md_5.bungee.api.ChatColor;

public class MainConfig {
    public String mode = "blacklist";
    public String servername = "lobby";
    public List<String> list = new ArrayList<String>() {
        {
            add("ban");
            add("kick");
        }
    };
    
    public boolean sendmovemsg = true;
    public List<String> movemsg = new ArrayList<String>() {
        {
            add("&3[MoveMeNow] &4You have been moved : %kickmsg%");
        }
    };
    
    public String[] parsemovemsg(String kickmsg) {
        String[] msg = getMoveMsg();
        String[] msgs = new String[msg.length];
        for (String i : msg) {
            msgs = ObjectArrays.concat(msgs,ChatColor.translateAlternateColorCodes('&',i).replaceAll("%kickmsg%", kickmsg));
        }
        return msgs;
    }
    
    public String[] getMoveMsg() {
        String[] msgs = new String[movemsg.size()];
        for (String i : movemsg) {
            msgs = ObjectArrays.concat(msgs, i.split("\\\\n"), String.class);
        }
        return msgs;
    }
}
