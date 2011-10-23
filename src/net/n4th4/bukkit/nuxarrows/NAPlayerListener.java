package net.n4th4.bukkit.nuxarrows;

import org.bukkit.Material;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerListener;
import org.bukkit.event.player.PlayerPickupItemEvent;
import org.bukkit.inventory.ItemStack;

public class NAPlayerListener extends PlayerListener {
    public final NuxArrows plugin;

    public NAPlayerListener(NuxArrows instance) {
        plugin = instance;
    }
    
    public void onPlayerPickupItem(PlayerPickupItemEvent event) {
        if (event.getItem().getItemStack().getType() != Material.ARROW || event.getItem().getNearbyEntities(0, 0, 0).size() == 0) {
            return;
        }
        
        Entity arrow = event.getItem().getNearbyEntities(0, 0, 0).get(0);
        if (arrow instanceof Arrow) {
            if (((Arrow) arrow).getShooter() == null) {
                event.setCancelled(true);
            }
        }
    }

    public void onPlayerInteract(PlayerInteractEvent event) {
        Player player = event.getPlayer();
        if (player.getItemInHand().getType() == Material.BOW && player.getInventory().contains(Material.ARROW) && player.hasPermission("nuxarrow.infinite") && event.getAction() == Action.RIGHT_CLICK_AIR) {
            ItemStack arrow = new ItemStack(Material.ARROW, 1);
            player.getInventory().addItem(arrow);
        }
    }
}
