package com.yahoo.marshallmoore55.playerholograms.Menus;

import com.yahoo.marshallmoore55.playerholograms.Main;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class AdminMenu implements Listener {
    private Inventory inv;
    private ItemStack deleteButton;
    private ItemStack forceUnrentButton;
    private ItemStack rentButton;
    private ItemStack messageButton;

    public AdminMenu(Player player) {
        inv = Main.getInstance().getServer().createInventory(player, 9, ChatColor.BOLD + "Admin Menu");

        deleteButton = new ItemStack(Material.DIRT, 1);
        ItemMeta meta = deleteButton.getItemMeta();
        if (meta != null) {
            meta.setDisplayName(ChatColor.BLUE + "Delete Pholo");
        }
        deleteButton.setItemMeta(meta);

        forceUnrentButton = new ItemStack(Material.REDSTONE_BLOCK, 1);
        ItemMeta meta2 = forceUnrentButton.getItemMeta();
        if (meta2 != null) {
            meta2.setDisplayName(ChatColor.BLUE + "Force Unrent Pholo");
        }
        forceUnrentButton.setItemMeta(meta2);

        messageButton = new ItemStack(Material.DIAMOND_BLOCK,1);
        ItemMeta meta3 = messageButton.getItemMeta();
        if (meta3 != null) {
            meta3.setDisplayName(ChatColor.BLUE + "Change Display Name");
        }
        messageButton.setItemMeta(meta3);

        rentButton = new ItemStack(Material.STONE);
        ItemMeta meta4 = rentButton.getItemMeta();
        if (meta4 != null) {
            meta4.setDisplayName(ChatColor.BLUE + "Rent Pholo");
        }
        rentButton.setItemMeta(meta4);

        inv.addItem(deleteButton);
        inv.addItem(forceUnrentButton);
        inv.addItem(messageButton);
        inv.addItem(rentButton);
    }

    public void openAdminMenu(Player player, Inventory inv) {player.openInventory(inv); }

    public Inventory getInv() {return this.inv; }

    public ItemStack getDeleteButton() {return this.deleteButton; }

    public ItemStack getForceUnrentButton() {return this.forceUnrentButton; }

    public ItemStack getMessageButton() {return this.messageButton; }

    public ItemStack getRentButton() {return this.rentButton; }
}
