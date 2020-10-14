package com.yahoo.marshallmoore55.playerholograms;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class OwnerMenu implements Listener{
    private Inventory inv;
    private ItemStack messageButton;
    private ItemStack unrentButton;

    public OwnerMenu(Player player) {
        inv = Main.getInstance().getServer().createInventory(player, 9, ChatColor.BOLD + "Owner Menu");

        messageButton = new ItemStack(Material.DIAMOND,1);
        ItemMeta meta = messageButton.getItemMeta();
        meta.setDisplayName(ChatColor.BLUE + "Change Display Name");
        messageButton.setItemMeta(meta);

        unrentButton = new ItemStack(Material.OBSIDIAN, 1);
        ItemMeta meta2 = unrentButton.getItemMeta();
        meta2.setDisplayName(ChatColor.BLUE + "Unrent Pholo");
        unrentButton.setItemMeta(meta2);

        inv.addItem(messageButton);
        inv.addItem(unrentButton);

    }

    public void openOwnerMenu(Player player, Inventory inv) {
        player.openInventory(inv);
    }

    public boolean isOwnerMenu(Inventory i) {
        if (i.contains(messageButton)) {
            return true;
        }else {
            return false;
        }
    }
    public Inventory getInv() {
        return this.inv;
    }

    public ItemStack getMessageButton() {
        return messageButton;
    }

    public ItemStack getUnrentButton() {
        return unrentButton;
    }
}

