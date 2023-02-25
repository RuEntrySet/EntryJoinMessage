package ru.entryset.join.messages.events;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import ru.entryset.join.messages.main.Main;

public class Events implements Listener {

    @EventHandler
    public void onJoin(PlayerJoinEvent e){
        Player player = e.getPlayer();
        String group = Main.perms.getPrimaryGroup(player);
        for(String src : Main.config.getConfigurationSection("settings.groups").getKeys(true)){
            if(group.toLowerCase().equalsIgnoreCase(src.toLowerCase())){
                for(String str : Main.config.getStringList("settings.groups." + src)){
                    Main.messager.sendMessage(player, str);
                }
                break;
            }
        }
    }
}
