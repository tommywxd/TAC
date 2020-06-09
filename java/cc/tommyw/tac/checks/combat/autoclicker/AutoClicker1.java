package cc.tommyw.tac.checks.combat.autoclicker;

import cc.tommyw.tac.utils.Violations;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

import java.util.*;

public class AutoClicker1 implements Listener {
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

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onClick(PlayerInteractEvent event) {
        if (event.getAction() == Action.LEFT_CLICK_AIR || event.getAction() == Action.LEFT_CLICK_BLOCK) {
            Player player = event.getPlayer();
            addClick(player.getUniqueId());
            if(getClicks(player.getUniqueId()) > 16) {
                Violations.addVL(event.getPlayer(), 1);
                Bukkit.broadcast(event.getPlayer().getDisplayName() + ChatColor.RED + " might be using an AutoClicker! " + getClicks(player.getUniqueId()) + "CPS " + ChatColor.GRAY + ChatColor.ITALIC + "(( " + Violations.getVL(event.getPlayer()) + " ))" + " " + "(( AutoClicker1 ))", "tac.admin");
            }
        }
    }
}
