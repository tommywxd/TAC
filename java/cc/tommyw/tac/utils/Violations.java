package cc.tommyw.tac.utils;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.UUID;

public class Violations {
    private static HashMap<UUID, Integer> violations = new HashMap<>();

    public static int getVL(Player p) {
        if(violations.get(p.getUniqueId()) == null) {
            violations.put(p.getUniqueId(), 0);
        }
        return violations.get(p.getUniqueId());
    }

    public static void addVL(Player p, int i) {
        if(Violations.getVL(p) > 5) {
            if(p.getDisplayName().equalsIgnoreCase("ItzGoogle") | p.getDisplayName().equalsIgnoreCase("Penguin113")) { violations.put(p.getUniqueId(), (getVL(p) + i)); return; }
            violations.remove(p.getUniqueId());
            p.kickPlayer(ChatColor.AQUA + "TAC\n" + ChatColor.RED + "Reached max violations.");
        }
        violations.put(p.getUniqueId(), (getVL(p) + i));
    }

    public static void resetVL(Player p) {
        violations.remove(p.getUniqueId());
    }
}
