package com.yahoo.marshallmoore55.playerholograms.Commands;

import com.miguelmeep.dev.util.UUIDFetcher;
import com.yahoo.marshallmoore55.playerholograms.Main;
import com.yahoo.marshallmoore55.playerholograms.Util.Permissions;
import com.yahoo.marshallmoore55.playerholograms.HologramObject;

import com.yahoo.marshallmoore55.playerholograms.Databases.PermissionsDatabase;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import org.bukkit.entity.Player;

import java.util.UUID;


public class pholoCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player) {
            Player player = (Player) sender;
            if (command.getName().equalsIgnoreCase("pholo")) {
                if (args.length == 1) {
                        if (args[0].equalsIgnoreCase("new")) {
                            if (player.hasPermission("pholo.new")) {
                                new HologramObject(player);
                                return true;
                        }
                    }
                } else if (args.length == 2) {
                    if (args[0].equalsIgnoreCase("admin")) {
                        if (player.hasPermission("add.admin")) {
                            Permissions perm = new Permissions();
                            if (Main.getInstance().getServer().getPlayer(args[1]) != null) {
                                Player newAdmin = Main.getInstance().getServer().getPlayer(args[1]);
                                if (newAdmin != null) {
                                    perm.setupAdmin(newAdmin);
                                    if (!PermissionsDatabase.isAdmin(newAdmin.getUniqueId())) {
                                        PermissionsDatabase.addAdmin(newAdmin.getUniqueId());
                                    } else {
                                        PermissionsDatabase.removeAdmin(newAdmin.getUniqueId());
                                        perm.removePerms(newAdmin.getUniqueId());
                                    }
                                }
                            } else {
                                UUID newAdminId = UUIDFetcher.getUUIDOf(args[1]);
                                if (!PermissionsDatabase.isAdmin(newAdminId)) {
                                    PermissionsDatabase.addAdmin(newAdminId);
                                } else {
                                    PermissionsDatabase.removeAdmin(newAdminId);
                                }
                            }
                            return true;
                        }
                    }
                }
            }
        }
        return false;
    }
}