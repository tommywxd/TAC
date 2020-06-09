package cc.tommyw.tac;

import cc.tommyw.tac.checks.combat.autoclicker.AutoClicker1;
import cc.tommyw.tac.checks.combat.killaura.KillAura1;
import cc.tommyw.tac.checks.combat.reach.Reach1;
import cc.tommyw.tac.checks.movement.antiknockback.AntiKnockback1;
import cc.tommyw.tac.checks.movement.fly.Fly1;
import cc.tommyw.tac.checks.movement.fly.Fly2;
import cc.tommyw.tac.checks.movement.nofall.NoFall1;
import cc.tommyw.tac.checks.movement.scaffold.Scaffold1;
import cc.tommyw.tac.checks.movement.speed.Speed1;
import cc.tommyw.tac.utils.Violations;
import cc.tommyw.tac.utils.pockets.WrapperPlayClientUseEntity;
import com.comphenix.protocol.ProtocolLibrary;
import com.comphenix.protocol.events.PacketAdapter;
import com.comphenix.protocol.events.PacketEvent;
import com.comphenix.protocol.wrappers.EnumWrappers;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.text.NumberFormat;
import java.util.HashMap;
import java.util.Random;
import java.util.UUID;

public final class TAC extends JavaPlugin {

	@Override
	public void onEnable() {
		int minute = (int) 1200L;
		this.getServer().getScheduler().scheduleSyncRepeatingTask(this, new Runnable() {
			public void run() {
				for(Player p : Bukkit.getOnlinePlayers()) {
					Violations.resetVL(p);
				}
			}
		}, 0L, minute * 1);

		this.getServer().getPluginManager().registerEvents(new Fly1(), this);
		this.getServer().getPluginManager().registerEvents(new Fly2(), this);
		this.getServer().getPluginManager().registerEvents(new AutoClicker1(), this);
		this.getServer().getPluginManager().registerEvents(new NoFall1(), this);
		this.getServer().getPluginManager().registerEvents(new Speed1(), this);
		this.getServer().getPluginManager().registerEvents(new Reach1(), this);
		this.getServer().getPluginManager().registerEvents(new Scaffold1(), this);
		this.getServer().getPluginManager().registerEvents(new KillAura1(), this);
		this.getServer().getPluginManager().registerEvents(new AntiKnockback1(this), this);
		//this.getServer().getPluginManager().registerEvents(new PingSpoof1(), this);
		//this.getServer().getPluginManager().registerEvents(new FastLadder1(), this);
	}

	@Override
	public void onDisable() {

	}
	
	@Override
	public boolean onCommand(CommandSender sender, Command command,
			String label, String[] args) {		
		
		return false;
	}
	
}
