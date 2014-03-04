package io.snw.tutorial;

import io.snw.tutorial.ServerTutorial;
import io.snw.tutorial.Tutorial;
import io.snw.tutorial.api.EndTutorialEvent;
import io.snw.tutorial.data.Getters;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

public class EndTutorial {


    private ServerTutorial plugin;

    public EndTutorial(ServerTutorial plugin) {
        this.plugin = plugin;
    }
    private Getters getters;
    
    public void endTutorial(final Player player) {
        final String name = player.getName();
        Tutorial tutorial = this.getters.getCurrentTutorial(name);
        player.sendMessage(ChatColor.translateAlternateColorCodes('&', tutorial.getEndMessage()));
        player.closeInventory();
        player.getInventory().clear();
        player.setAllowFlight(plugin.getFlight(name));
        player.setFlying(false);
        plugin.removeFlight(name);
        player.teleport(plugin.getFirstLoc(name));
        plugin.cleanFirstLoc(name);
        plugin.removeFromTutorial(name);
        new BukkitRunnable() {

            @Override
            public void run() {
                for (Player online : plugin.getServer().getOnlinePlayers()) {
                    online.showPlayer(player);
                    player.showPlayer(online);
                }
                player.getInventory().setContents(plugin.getInventory(name));
                plugin.cleanInventory(name);
            }
        }.runTaskLater(plugin, 20L);
        EndTutorialEvent event = new EndTutorialEvent(player, tutorial);
        Bukkit.getServer().getPluginManager().callEvent(event);
    }

    public void reloadEndTutorial(final Player player) {
        final String name = player.getName();
        Tutorial tutorial = this.getters.getCurrentTutorial(name);
        player.sendMessage(ChatColor.translateAlternateColorCodes('&', tutorial.getEndMessage()));
        player.closeInventory();
        player.getInventory().clear();
        player.setAllowFlight(plugin.getFlight(name));
        player.setFlying(false);
        plugin.removeFlight(name);
        player.teleport(plugin.getFirstLoc(name));
        plugin.cleanFirstLoc(name);
        plugin.removeFromTutorial(name);
        new BukkitRunnable() {

            @Override
            public void run() {
                for (Player online : plugin.getServer().getOnlinePlayers()) {
                    online.showPlayer(player);
                    player.showPlayer(online);
                }
                player.getInventory().setContents(plugin.getInventory(name));
                plugin.cleanInventory(name);
            }
        }.runTaskLater(plugin, 20L);
    }    
}
