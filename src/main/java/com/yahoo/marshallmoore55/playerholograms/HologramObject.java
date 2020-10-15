package com.yahoo.marshallmoore55.playerholograms;

import com.yahoo.marshallmoore55.playerholograms.Databases.HologramDatabase;
import com.yahoo.marshallmoore55.playerholograms.Main;
import com.yahoo.marshallmoore55.playerholograms.Util.ConversationPrompt;
import org.bukkit.ChatColor;
import org.bukkit.conversations.Conversation;
import org.bukkit.conversations.ConversationFactory;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;

import java.util.UUID;


public class HologramObject {
    public static String unrentedMessage = "Unowned " + ChatColor.RED + "Player Hologram";

    public HologramObject(Player player) {
        ArmorStand armorstand = (ArmorStand) player.getWorld().spawnEntity(player.getLocation(), EntityType.ARMOR_STAND);
        armorstand.setCustomName(unrentedMessage);
        armorstand.setCustomNameVisible(true);
        armorstand.setGravity(false);
        armorstand.setVisible(false);

        UUID id = armorstand.getUniqueId();

        HologramDatabase.addHolo(id);
    }


    public static void removeHolo(ArmorStand armorstand) {
        UUID armorstandUniqueId = armorstand.getUniqueId();
        HologramDatabase.removeHolo(armorstandUniqueId);
        armorstand.remove();
    }

    public static boolean isHolo(ArmorStand armorstand) {
        UUID armorstandUniqueId = armorstand.getUniqueId();
        return HologramDatabase.contains(armorstandUniqueId);

    }
    public static void rentHolo(Player player, ArmorStand armorstand) {
        UUID armorstandUniqueId = armorstand.getUniqueId();
        HologramDatabase.rentHolo(player, armorstandUniqueId);
        toRentedMessage(armorstand);
    }

    public static void unrentHolo(ArmorStand armorstand) {
        UUID armorstandUniqueId = armorstand.getUniqueId();
        HologramDatabase.unrentHolo(armorstandUniqueId);
        toUnrentedMessage(armorstand);
    }

    public static boolean isOwned(ArmorStand armorstand) {
        UUID armorstandUniqueId = armorstand.getUniqueId();
        return HologramDatabase.isOwned(armorstandUniqueId);
    }

    public static UUID getOwner(ArmorStand armorstand) {
        UUID armorstandUniqueId = armorstand.getUniqueId();
        return (UUID.fromString(HologramDatabase.getOwner(armorstandUniqueId)));
    }

    public static String getOwnerName(ArmorStand armorstand) {
        UUID armorstandUniqueId = armorstand.getUniqueId();
        return HologramDatabase.getOwnerName(armorstandUniqueId);
    }

    public static void toRentedMessage(ArmorStand armorstand) {
        String owner_name = getOwnerName(armorstand);
        armorstand.setCustomName(ChatColor.DARK_PURPLE + owner_name + "'s " + ChatColor.RED + "Player Hologram");
    }

    public static void toUnrentedMessage(ArmorStand armorstand) {
        armorstand.setCustomName(unrentedMessage);
    }

    public static void changeMessage(Player player, ArmorStand armorstand) {
        ConversationFactory cf = new ConversationFactory(Main.getInstance());
        ConversationPrompt cp = new ConversationPrompt(armorstand);
        Conversation conv = cf.withFirstPrompt(cp).withLocalEcho(false).buildConversation(player);
        conv.begin();
    }

    public static void setMessage(String input, ArmorStand armorstand) {
        String newMessage = ChatColor.translateAlternateColorCodes('&', input);
        armorstand.setCustomName(newMessage);
    }
}

