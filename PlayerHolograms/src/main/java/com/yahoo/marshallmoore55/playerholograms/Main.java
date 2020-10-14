package com.yahoo.marshallmoore55.playerholograms;

import com.yahoo.marshallmoore55.playerholograms.Commands.pholoCommand;
import com.yahoo.marshallmoore55.playerholograms.Events.JoinEvent;
import com.yahoo.marshallmoore55.playerholograms.Events.LeaveEvent;
import com.yahoo.marshallmoore55.playerholograms.Events.RightClickHologramEvent;
import com.yahoo.marshallmoore55.playerholograms.Util.HologramDatabase;
import com.yahoo.marshallmoore55.playerholograms.Util.PermissionsDatabase;
import com.zawmg.dev.database.Database;
import org.bukkit.permissions.PermissionAttachment;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.HashMap;
import java.util.UUID;

public final class Main extends JavaPlugin {

        private pholoCommand cmd1 =new pholoCommand();
        private Database database;
        private static Main instance;

        public HashMap<UUID, PermissionAttachment> playerPermissions = new HashMap<>();
        public HashMap<UUID, String> messages = new HashMap<>();

    @Override
    public void onEnable() {
        instance = this;
        saveDefaultConfig();
        this.database = super.getServer().getServicesManager().getRegistration(Database.class).getProvider();
        if (database == null) {
            System.out.println("Could not find applicable dev database api");
            super.setEnabled(false);
            return;
        }

        new HologramDatabase();
        new PermissionsDatabase();

        getCommand("pholo").setExecutor(cmd1);
        getServer().getPluginManager().registerEvents(new RightClickHologramEvent(), this);
        getServer().getPluginManager().registerEvents(new JoinEvent(), this);
        getServer().getPluginManager().registerEvents(new LeaveEvent(), this);
    }

    @Override
    public void onDisable() {
        messages.clear();
        playerPermissions.clear();
    }

    public static Main getInstance() {return instance;}

    public Database getDB() {return database;}
}
