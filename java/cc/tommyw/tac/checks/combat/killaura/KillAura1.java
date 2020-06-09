package cc.tommyw.tac.checks.combat.killaura;

import cc.tommyw.tac.utils.Violations;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;

import java.util.*;

public class KillAura1 implements Listener {

    private static Map<UUID, List<Long>> swings = new HashMap<>();
    private static Map<UUID, List<Long>> hits = new HashMap<>();

    public static void addSwing(UUID uuid) {
        List<Long> list = null;
        if (swings.containsKey(uuid)) {
            list = swings.get(uuid);
        } else {
            list = new ArrayList<>();
        }
        list.add(Long.valueOf(System.currentTimeMillis()));
        swings.put(uuid, list);
    }

    public static int getSwings(UUID uuid) {
        Iterator<Long> iterator = swings.get(uuid).iterator();
        while (iterator.hasNext()) {
            if (((Long) iterator.next()).longValue() < System.currentTimeMillis() - 1000L) {
                iterator.remove();
            }
        }
        return swings.get(uuid).size();
    }

    public static void addHit(UUID uuid) {
        List<Long> list = null;
        if (hits.containsKey(uuid)) {
            list = hits.get(uuid);
        } else {
            list = new ArrayList<>();
        }
        list.add(Long.valueOf(System.currentTimeMillis()));
        hits.put(uuid, list);
    }

    public static int getHits(UUID uuid) {
        Iterator<Long> iterator = hits.get(uuid).iterator();
        while (iterator.hasNext()) {
            if (((Long) iterator.next()).longValue() < System.currentTimeMillis() - 1000L) {
                iterator.remove();
            }
        }
        return hits.get(uuid).size();
    }

    @EventHandler
    public void onInteract(PlayerInteractEvent e) {
        Action action = e.getAction();
        if(action == Action.LEFT_CLICK_AIR || action == Action.LEFT_CLICK_BLOCK) {
            addSwing(e.getPlayer().getUniqueId());
        }
    }

    @EventHandler
    public void onHit(EntityDamageByEntityEvent e) {
        addHit(e.getDamager().getUniqueId());
        if(getHits(e.getDamager().getUniqueId()) == getSwings(e.getDamager().getUniqueId())) {
            Violations.addVL((Player) e.getDamager(), 1);
            Bukkit.broadcast(((Player) e.getDamager()).getDisplayName() + ChatColor.RED + " might be using KillAura!" + ChatColor.GRAY + ChatColor.ITALIC + " (( Accuracy )) " + "(( " + Violations.getVL((Player) e.getDamager()) + " ))" + " " + "(( KillAura1 ))", "tac.admin");
        }
    }
}
