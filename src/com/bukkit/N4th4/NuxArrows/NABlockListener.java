package com.bukkit.N4th4.NuxArrows;

import org.bukkit.Material;
import org.bukkit.block.Dispenser;
import org.bukkit.event.block.BlockDispenseEvent;
import org.bukkit.event.block.BlockListener;
import org.bukkit.inventory.ItemStack;

public class NABlockListener extends BlockListener {
    public final NuxArrows plugin;

    public NABlockListener(NuxArrows instance) {
        plugin = instance;
    }

    public void onBlockDispense(BlockDispenseEvent event) {
        if (event.getItem().getType() == Material.ARROW && event.getBlock().getType() == Material.DISPENSER) {
            Dispenser dispenser = (Dispenser)event.getBlock().getState();
            ItemStack arrow = new ItemStack(Material.ARROW, 1);
            dispenser.getInventory().addItem(arrow);
            dispenser.update(true);
        }
    }
}
