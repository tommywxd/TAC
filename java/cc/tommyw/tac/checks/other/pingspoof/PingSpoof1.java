package cc.tommyw.tac.checks.other.pingspoof;

import net.minecraft.server.v1_8_R3.PacketLoginOutDisconnect;
import net.minecraft.server.v1_8_R3.PacketPlayInKeepAlive;
import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

public class PingSpoof1 implements Listener {
    int isnotpingspoofing = 0;
    int eventsent = 0;

    @EventHandler
    public void pongspoofing(PlayerMoveEvent e) {
        if(isnotpingspoofing == 0) {
            Bukkit.broadcastMessage("pong spoofer");
        }
    }

    @EventHandler
    public void fourteenpingspoofers(PacketPlayInKeepAlive e) {
        if(isnotpingspoofing == 0) {
            isnotpingspoofing = 1;
        } else {
            isnotpingspoofing = 0;
        }
    }
}
