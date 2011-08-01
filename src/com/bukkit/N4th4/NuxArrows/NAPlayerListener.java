package com.bukkit.N4th4.NuxArrows;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerListener;
import org.bukkit.inventory.ItemStack;

public class NAPlayerListener extends PlayerListener {
    public final NuxArrows plugin;

    public NAPlayerListener(NuxArrows instance) {
        plugin = instance;
    }

    public void onPlayerInteract(PlayerInteractEvent event) {
        Player player = event.getPlayer();
        if (player.getItemInHand().getType() == Material.BOW && player.getInventory().contains(Material.ARROW) && plugin.permissions.has(player, "nuxarrow.infinite")) {
            ItemStack arrow = new ItemStack(Material.ARROW, 1);
            player.getInventory().addItem(arrow);
            player.updateInventory();
        }
    }
}
