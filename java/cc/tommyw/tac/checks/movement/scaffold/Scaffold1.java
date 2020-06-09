package cc.tommyw.tac.checks.movement.scaffold;

import cc.tommyw.tac.utils.Violations;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.player.PlayerMoveEvent;

import java.util.*;

public class Scaffold1 implements Listener {
    private static Map<UUID, List<Long>> blocks = new HashMap<>();
    private static Map<UUID, List<Long>> snaps = new HashMap<>();

    public static void addBlock(UUID uuid) {
        List<Long> list = null;
        if (blocks.containsKey(uuid)) {
            list = blocks.get(uuid);
        } else {
            list = new ArrayList<>();
        }
        list.add(Long.valueOf(System.currentTimeMillis()));
        blocks.put(uuid, list);
    }

    public static int getBlocks(UUID uuid) {
        Iterator<Long> iterator = blocks.get(uuid).iterator();
        while (iterator.hasNext()) {
            if (((Long) iterator.next()).longValue() < System.currentTimeMillis() - 1000L) {
                iterator.remove();
            }
        }
        return blocks.get(uuid).size();
    }

    public static void addSnap(UUID uuid) {
        List<Long> list = null;
        if (snaps.containsKey(uuid)) {
            list = snaps.get(uuid);
        } else {
            list = new ArrayList<>();
        }
        list.add(Long.valueOf(System.currentTimeMillis()));
        snaps.put(uuid, list);
    }

    public static int getSnaps(UUID uuid) {
        Iterator<Long> iterator = snaps.get(uuid).iterator();
        while (iterator.hasNext()) {
            if (((Long) iterator.next()).longValue() < System.currentTimeMillis() - 500L) {
                iterator.remove();
            }
        }
        return snaps.get(uuid).size();
    }

    @EventHandler
    public void playerSnap(PlayerMoveEvent e) {
        if(e.getFrom().getYaw() < e.getTo().getYaw()) {
            addSnap(e.getPlayer().getUniqueId());
        }
    }

    @EventHandler
    public void blockEvent(BlockPlaceEvent e) {
        addBlock(e.getPlayer().getUniqueId());
        if(getSnaps(e.getPlayer().getUniqueId()) > 4) {
            if (getBlocks(e.getPlayer().getUniqueId()) > 7) {
                Violations.addVL(e.getPlayer(), 1);
                Bukkit.broadcast(e.getPlayer().getDisplayName() + ChatColor.RED + " might be using Scaffold! " + getBlocks(e.getPlayer().getUniqueId()) + "BPS " + ChatColor.GRAY + ChatColor.ITALIC + "(( " + Violations.getVL(e.getPlayer()) + " ))" + " " + "(( Scaffold1 ))", "tac.admin");
            }
        }
    }
}
