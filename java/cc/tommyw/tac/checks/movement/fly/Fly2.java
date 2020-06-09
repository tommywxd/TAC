package cc.tommyw.tac.checks.movement.fly;

import cc.tommyw.tac.utils.Violations;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerKickEvent;

public class Fly2 implements Listener {
    @EventHandler
    public void detectfly(PlayerKickEvent e) {
        if(e.getReason().equalsIgnoreCase("Flying is not enabled on this server")) {
            e.setCancelled(true);
            e.getPlayer().kickPlayer("Fly.");
            Violations.addVL(e.getPlayer(), 1);
            Bukkit.broadcast(e.getPlayer().getDisplayName() + ChatColor.RED + " might be using Fly! " + ChatColor.GRAY + ChatColor.ITALIC + "(( " + Violations.getVL(e.getPlayer()) + " ))" + " " + "(( Fly2 ))", "tac.admin");
        }
    }
}
