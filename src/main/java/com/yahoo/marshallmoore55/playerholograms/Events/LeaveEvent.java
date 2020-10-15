package com.yahoo.marshallmoore55.playerholograms.Events;


import com.yahoo.marshallmoore55.playerholograms.Main;
import com.yahoo.marshallmoore55.playerholograms.Util.Permissions;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

import java.util.UUID;


public class LeaveEvent implements Listener {

    @EventHandler
    public void onPlayerLeave(PlayerQuitEvent e) {
        Player player = e.getPlayer();
        UUID player_uuid = player.getUniqueId();
        Permissions perm = new Permissions();
        perm.removePerms(player_uuid);
        Main.getInstance().playerPermissions.remove(player_uuid);
        Main.getInstance().messages.remove(player_uuid);
    }
}
