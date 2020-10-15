package com.yahoo.marshallmoore55.playerholograms.Events;

import com.yahoo.marshallmoore55.playerholograms.Util.Permissions;
import com.yahoo.marshallmoore55.playerholograms.Databases.PermissionsDatabase;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import java.util.UUID;

public class JoinEvent implements Listener {

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent e) {
        Player player = e.getPlayer();
        UUID player_uuid = player.getUniqueId();
        if (PermissionsDatabase.isAdmin(player_uuid)) {
            Permissions perm = new Permissions();
            perm.setupAdmin(player);
        }
    }
}
