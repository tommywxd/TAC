package cc.tommyw.tac.checks.combat.reach;

import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

public class Reach1 implements Listener {
    @EventHandler
    public void damagebyentity(EntityDamageByEntityEvent e) {
        if(e.getDamager().getType().equals(EntityType.PLAYER)) {
            Player damager = (Player) e.getDamager();
            if(damager.getLocation().distanceSquared(e.getEntity().getLocation()) > 22) {
                e.setCancelled(true);
                damager.sendMessage("Reach [EXPERIMENTAL]");
            }
        }
    }
}
