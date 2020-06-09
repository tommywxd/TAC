package cc.tommyw.tac.checks.movement.speed;

import cc.tommyw.tac.TAC;
import cc.tommyw.tac.utils.Violations;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.potion.PotionEffectType;

import java.util.HashMap;

public class Speed1 implements Listener {

    @EventHandler
    public void playerMove(PlayerMoveEvent e) {
        if(e.getPlayer().hasPotionEffect(PotionEffectType.SPEED)) {
            return;
        }
        if(e.getFrom().getY() > e.getTo().getY()) {
            if(e.getPlayer().getVelocity().length() > 3.93) {
                Violations.addVL(e.getPlayer(), 1);
                Bukkit.broadcastMessage(e.getPlayer().getDisplayName() + ChatColor.RED + " might be using Speed! " + ChatColor.GRAY + ChatColor.ITALIC + "(( " + Violations.getVL(e.getPlayer()) + " ))" + " " + "(( Speed1 | Fall ))");
                return;
            }
        }

        //if(Math.round(e.getFrom().getY()) == Math.round(e.getTo().getY())) {
        //    if (e.getPlayer().getVelocity().length() > 0.94) {
        //        Bukkit.broadcastMessage(e.getPlayer().getDisplayName() + ChatColor.RED + " might be using Speed! " + ChatColor.GRAY + ChatColor.ITALIC + "(( Speed1 | Ground ))");
        //        return;
        //    }
        //}
    }
}