package cc.tommyw.tac.checks.movement.fastladder;

import cc.tommyw.tac.utils.Violations;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

import java.util.*;

public class FastLadder1 implements Listener {

    private static Map<UUID, List<Long>> clicks = new HashMap<>();

    public static void addClick(UUID uuid) {
        List<Long> list = null;
        if (clicks.containsKey(uuid)) {
            list = clicks.get(uuid);
        } else {
            list = new ArrayList<>();
        }
        list.add(Long.valueOf(System.currentTimeMillis()));
        clicks.put(uuid, list);
    }

    public static int getClicks(UUID uuid) {
        Iterator<Long> iterator = clicks.get(uuid).iterator();
        while (iterator.hasNext()) {
            if (((Long) iterator.next()).longValue() < System.currentTimeMillis() - 1000L) {
                iterator.remove();
            }
        }
        return clicks.get(uuid).size();
    }

    @EventHandler
    public void playerMove(PlayerMoveEvent e) {
        //e.getPlayer().sendMessage(e.getFrom().getBlock().toString() + " | " + e.getTo().toString() + " | " + e.getPlayer().getVelocity().getY());
        //if (e.getFrom().getBlock().equals(Material.LADDER) && e.getTo().getBlock().equals(Material.LADDER)) {
        if(Math.round(e.getFrom().getY()) < e.getTo().getY()) {
            addClick(e.getPlayer().getUniqueId());
            if (getClicks(e.getPlayer().getUniqueId()) > 15) {
                Violations.addVL(e.getPlayer(), 1);
                Bukkit.broadcast(e.getPlayer().getDisplayName() + ChatColor.RED + " might be using FastLadder! " + ChatColor.GRAY + ChatColor.ITALIC + "(( " + Violations.getVL(e.getPlayer()) + " ))" + " " + "(( FastLadder1 ))", "tac.admin");
            }
        }
        //}
    }
}
