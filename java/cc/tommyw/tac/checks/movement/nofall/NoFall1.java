package cc.tommyw.tac.checks.movement.nofall;

import cc.tommyw.tac.utils.Violations;
import org.bukkit.*;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.util.Vector;

public class NoFall1 implements Listener {
    @EventHandler
    public void onMove(PlayerMoveEvent e) {
        Player p = e.getPlayer();
        Location from = e.getFrom().clone();
        Location to = e.getTo().clone();

        Vector vec = to.toVector();
        double i = vec.distance(from.toVector());

        if (i == 0.0D) {
            return;
        }

        if (p.getGameMode().equals(GameMode.CREATIVE) | p.getGameMode().equals(GameMode.SPECTATOR)) {
            return;
        }

        if (p.getVehicle() != null) {
            return;
        }

        if (from.getY() < to.getY()) {
            return;
        }

        if ((p.getFallDistance() == 0.0F) && (i > 0.90D && (p.isOnGround()))) {
            //e.setCancelled(true);
            Violations.addVL(e.getPlayer(), 1);
            Bukkit.broadcast(e.getPlayer().getDisplayName() + ChatColor.RED + " might be using NoFall! " + ChatColor.GRAY + ChatColor.ITALIC + "(( " + Violations.getVL(e.getPlayer()) + " ))" + " " + "(( NoFall1 ))", "tac.admin");
        }
    }
}
