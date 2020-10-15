package com.yahoo.marshallmoore55.playerholograms.Util;

import com.yahoo.marshallmoore55.playerholograms.Main;
import org.bukkit.entity.Player;
import org.bukkit.permissions.PermissionAttachment;

import java.util.UUID;

public class Permissions {

    public void setupAdmin(Player player) {
        PermissionAttachment attachment = player.addAttachment(Main.getInstance());
        Main.getInstance().playerPermissions.put(player.getUniqueId(), attachment);
        permissionsSetter(player.getUniqueId());
    }

    public void permissionsSetter(UUID uuid) {
        PermissionAttachment attachment = Main.getInstance().playerPermissions.get(uuid);
        for (String groups : Main.getInstance().getConfig().getConfigurationSection("Groups").getKeys(false)) {
            for (String permissions : Main.getInstance().getConfig().getStringList("Groups." + groups + ".permissions")) {
                attachment.setPermission(permissions, true);
            }
        }
    }

    public void removePerms(UUID uuid) {
        PermissionAttachment attachment = Main.getInstance().playerPermissions.get(uuid);
        for (String groups : Main.getInstance().getConfig().getConfigurationSection("Groups").getKeys(false)) {
            for (String permissions : Main.getInstance().getConfig().getStringList("Groups." + groups + ".permissions")) {
                attachment.setPermission(permissions,false);
            }
        }
    }
}
