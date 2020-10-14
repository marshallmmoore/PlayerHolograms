package com.yahoo.marshallmoore55.playerholograms.Events;

import com.yahoo.marshallmoore55.playerholograms.AdminMenu;
import com.yahoo.marshallmoore55.playerholograms.OwnerMenu;
import com.yahoo.marshallmoore55.playerholograms.RenterMenu;
import org.bukkit.ChatColor;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

import com.yahoo.marshallmoore55.playerholograms.Util.HologramObject;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerInteractAtEntityEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;


public class RightClickHologramEvent implements Listener {
        private ArmorStand armorstand;

        @EventHandler
        public void onPlayerRightClick(PlayerInteractAtEntityEvent e) {
                if (e.getRightClicked() instanceof ArmorStand) {
                        armorstand = (ArmorStand) e.getRightClicked();
                        Player player = e.getPlayer();
                        if (HologramObject.isHolo(armorstand)) {
                                if (player.hasPermission("adminmenu.open")) {
                                        AdminMenu adminMenu = new AdminMenu(player);
                                        adminMenu.openAdminMenu(player, adminMenu.getInv());
                                } else if(!HologramObject.isOwned(armorstand)) {
                                        RenterMenu renterMenu = new RenterMenu(player);
                                        renterMenu.openRenterMenu(player, renterMenu.getInv());
                                } else if (HologramObject.getOwner(armorstand).toString().equals(player.getUniqueId().toString())) {
                                        OwnerMenu ownerMenu = new OwnerMenu(player);
                                        ownerMenu.openOwnerMenu(player, ownerMenu.getInv());
                                }
                        }
                }
        }

        @EventHandler
        public void onInventoryClick(InventoryClickEvent e) {
                Player player = (Player) e.getWhoClicked();
                ClickType clickType = e.getClick();
                ItemStack clickedItem = e.getCurrentItem();
                Inventory open = e.getClickedInventory();
                if (clickedItem.equals(new RenterMenu(player).getRentButton()) && clickType.isLeftClick()) {
                        HologramObject.rentHolo(player, armorstand);
                        e.setCancelled(true);
                        player.closeInventory();
                        OwnerMenu menu = new OwnerMenu(player);
                        menu.openOwnerMenu(player, menu.getInv());
                } else if ((clickedItem.equals(new OwnerMenu(player).getMessageButton()) || clickedItem.equals(new AdminMenu(player).getMessageButton())) && clickType.isLeftClick()) {
                        e.setCancelled(true);
                        player.closeInventory();
                        HologramObject.changeMessage(player, armorstand);
                } else if (clickedItem.equals(new OwnerMenu(player).getUnrentButton()) && clickType.isLeftClick()) {
                        HologramObject.unrentHolo(armorstand);
                        e.setCancelled(true);
                        player.closeInventory();
                        RenterMenu menu = new RenterMenu(player);
                        menu.openRenterMenu(player, menu.getInv());
                } else if (clickedItem.equals(new AdminMenu(player).getDeleteButton()) && clickType.isLeftClick()) {
                        HologramObject.removeHolo(armorstand);
                        e.setCancelled(true);
                        player.closeInventory();
                } else if (clickedItem.equals(new AdminMenu(player).getForceUnrentButton()) && clickType.isLeftClick()) {
                        HologramObject.unrentHolo(armorstand);
                        e.setCancelled(true);
                } else if (clickedItem.equals(new AdminMenu(player).getRentButton()) && clickType.isLeftClick()) {
                        if (HologramObject.isOwned(armorstand)) {
                                e.setCancelled(true);
                                player.closeInventory();
                                player.sendMessage(ChatColor.RED + "That Player Hologram is already owned!");
                        } else {
                                HologramObject.rentHolo(player, armorstand);
                                e.setCancelled(true);
                        }
                }
        }
}




