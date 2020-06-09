package cc.tommyw.tac.checks.movement.antiknockback;

import cc.tommyw.tac.TAC;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;

public class AntiKnockback1 implements Listener {
    private final TAC plugin;

    public AntiKnockback1(TAC plugin) {
        this.plugin = plugin;
    }

    Location loc1;
    Location loc2;

    @EventHandler
    public void AntiKB(final EntityDamageEvent e) {
        if (e.getEntityType().equals(EntityType.PLAYER)) {
            loc1 = new Location(e.getEntity().getWorld(), e.getEntity().getLocation().getBlockY(), e.getEntity().getLocation().getBlockX(), e
                    .getEntity().getLocation().getBlockZ());

            Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(plugin, new Runnable() {
                public void run() {

                    loc2 = new Location(e.getEntity().getWorld(), e.getEntity().getLocation().getBlockY(), e.getEntity().getLocation().getBlockX(), e
                            .getEntity().getLocation().getBlockZ());

                    if (loc1 == loc2) {
                        Bukkit.broadcast(((Player) e.getEntity()).getDisplayName() + ChatColor.RED + " might be using AntiKnockback! " + ChatColor.GRAY + ChatColor.ITALIC +  "(( AntiKnockback1 ))", "tac.admin");
                    }

                }
            }, 10);
        }
    }
}
