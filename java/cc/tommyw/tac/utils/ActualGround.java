package cc.tommyw.tac.utils;

import org.bukkit.Effect;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffectType;

import java.util.Arrays;
import java.util.List;

public class ActualGround {
    public static List<Material> halfBlockTypes = Arrays.asList(new Material[] {Material.ACACIA_STAIRS, Material.BIRCH_WOOD_STAIRS, Material.BRICK_STAIRS, Material.COBBLESTONE_STAIRS,
            Material.DARK_OAK_STAIRS, Material.JUNGLE_WOOD_STAIRS, Material.NETHER_BRICK_STAIRS, Material.QUARTZ_STAIRS, Material.RED_SANDSTONE_STAIRS,
            Material.SANDSTONE_STAIRS, Material.SMOOTH_STAIRS, Material.SPRUCE_WOOD_STAIRS, Material.WOOD_STAIRS,
            Material.STONE_SLAB2, Material.DOUBLE_STONE_SLAB2});

    public static boolean isPlayerOnHalf(Player p) {
        return (!p.isFlying() && (p.getLocation().getY() + "").endsWith(".5")) ? true
                : halfBlockTypes.contains(p.getLocation().getBlock().getType());
    }

    public static boolean ActualGround(Player p) {
        Location nloc = new Location(p.getLocation().getWorld(), p.getLocation().getX(), p.getLocation().getY() - 1, p.getLocation().getZ());
        Location nloc2 = new Location(p.getLocation().getWorld(), p.getLocation().getX(), p.getLocation().getY(), p.getLocation().getZ());
        Location nloc3 = new Location(p.getLocation().getWorld(), p.getLocation().getX(), p.getLocation().getY() - 2, p.getLocation().getZ());
        p.getLocation().getWorld();
        if(nloc.getBlock().getType().equals(Material.AIR)) {
            if(p.hasPotionEffect(PotionEffectType.JUMP)) {
                return false;
            }
            if (p.getVelocity().getX() > 2) {
                return false;
            }
            if (p.getVelocity().getZ() > 2) {
                return false;
            }
            if(p.getVelocity().getY() > -0.0684000015258789) {
                return false;
            }
            if(!nloc3.getBlock().getType().equals(Material.AIR)) {
                return false;
            }
            if(nloc.getBlock().getType().equals(Material.WATER)) {
                return false;
            }
            if(p.isFlying() == true) {
                return false;
            }
            if(isPlayerOnHalf(p)) {
                return false;
            }
            return true;
        }
        return false;
    }
}
