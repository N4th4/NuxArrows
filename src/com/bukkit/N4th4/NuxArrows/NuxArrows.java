package com.bukkit.N4th4.NuxArrows;

import java.util.HashMap;

import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.Event.Priority;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import com.nijiko.permissions.PermissionHandler;
import com.nijikokun.bukkit.Permissions.Permissions;

public class NuxArrows extends JavaPlugin {
    private final HashMap<Player, Boolean> debugees       = new HashMap<Player, Boolean>();
    private final NABlockListener          blockListener  = new NABlockListener(this);
    private final NAPlayerListener         playerListener = new NAPlayerListener(this);
    public PermissionHandler               permissions    = null;

    public NuxArrows() {
        NALogger.initialize();
    }

    public void onEnable() {
        setupPermissions();

        PluginManager pm = getServer().getPluginManager();
        pm.registerEvent(Event.Type.BLOCK_DISPENSE, blockListener, Priority.Normal, this);
        pm.registerEvent(Event.Type.PLAYER_INTERACT, playerListener, Priority.Normal, this);

        PluginDescriptionFile pdfFile = this.getDescription();
        NALogger.info(pdfFile.getName() + " version " + pdfFile.getVersion() + " is enabled!");
    }

    public void onDisable() {
    }

    private void setupPermissions() {
        if (permissions != null) {
            return;
        }

        Plugin permissionsPlugin = this.getServer().getPluginManager().getPlugin("Permissions");

        if (permissionsPlugin == null) {
            NALogger.severe("Permissions not found");
            return;
        }

        permissions = ((Permissions) permissionsPlugin).getHandler();
    }

    public boolean isDebugging(final Player player) {
        if (debugees.containsKey(player)) {
            return debugees.get(player);
        } else {
            return false;
        }
    }

    public void setDebugging(final Player player, final boolean value) {
        debugees.put(player, value);
    }
}
