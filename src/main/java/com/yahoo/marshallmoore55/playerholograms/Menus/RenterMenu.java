package com.yahoo.marshallmoore55.playerholograms.Menus;

import com.yahoo.marshallmoore55.playerholograms.Main;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class RenterMenu implements Listener {
    private Inventory inv;
    private ItemStack rentButton;

    public RenterMenu(Player player) {
        inv = Main.getInstance().getServer().createInventory(player, 9, ChatColor.BOLD + "Renter Menu");

        rentButton = new ItemStack(Material.COBBLESTONE);
        ItemMeta meta = rentButton.getItemMeta();
        if (meta != null) {
            meta.setDisplayName(ChatColor.BLUE + "Rent Pholo");
        }
        rentButton.setItemMeta(meta);

        inv.addItem(rentButton);
    }

    public void openRenterMenu(Player player, Inventory inv) {
        player.openInventory(inv);
    }

    public boolean isRenterMenu(Inventory i) {
        return (i.contains(rentButton));
    }

    public Inventory getInv() {
        return this.inv;
    }

    public ItemStack getRentButton() {
        return rentButton;
    }

}