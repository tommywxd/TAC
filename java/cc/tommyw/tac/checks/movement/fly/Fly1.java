package cc.tommyw.tac.checks.movement.fly;

import cc.tommyw.tac.utils.ActualGround;
import cc.tommyw.tac.utils.Violations;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.potion.PotionEffectType;

public class Fly1 implements Listener {
    @EventHandler
    public void PMEFlyCheck(PlayerMoveEvent e) {
        Player player = e.getPlayer();
        //player.sendMessage(player.getVelocity().getY() + " | " + player.getVelocity().getY() + " | " + player.getVelocity().getZ() + " | " + ActualGround.ActualGround(e.getPlayer()) + " | " + e.getPlayer().isOnGround());
        if(e.getFrom().getY() > e.getTo().getY()) {
            return;
        }
        if(e.getFrom().getY() < e.getTo().getY()) {
            return;
        }
        if(ActualGround.ActualGround(e.getPlayer()) && !e.getPlayer().isOnGround()) {
            if(e.getPlayer().getGameMode().equals(GameMode.CREATIVE) | e.getPlayer().getGameMode().equals(GameMode.SPECTATOR)) return;
            //e.setCancelled(true);
            Violations.addVL(e.getPlayer(), 1);
            Bukkit.broadcast(e.getPlayer().getDisplayName() + ChatColor.RED + " might be using Fly! " + ChatColor.GRAY + ChatColor.ITALIC + "(( " + Violations.getVL(e.getPlayer()) + " ))" + " " + "(( Fly1 ))", "tac.admin");
        }
    }
}
