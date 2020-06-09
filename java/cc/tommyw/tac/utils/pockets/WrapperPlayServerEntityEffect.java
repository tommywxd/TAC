package cc.tommyw.tac.utils.pockets;

import com.comphenix.protocol.PacketType;
import com.comphenix.protocol.events.PacketContainer;
import com.comphenix.protocol.events.PacketEvent;
import net.minecraft.server.v1_8_R3.World;
import org.bukkit.entity.Entity;

public class WrapperPlayServerEntityEffect extends AbstractPacket {
    public static final PacketType TYPE = PacketType.Play.Server.ENTITY_EFFECT;

    public WrapperPlayServerEntityEffect() {
        super(new PacketContainer(TYPE), TYPE);
        handle.getModifier().writeDefaults();
    }

    public WrapperPlayServerEntityEffect(PacketContainer packet) {
        super(packet, TYPE);
    }

    /**
     * Retrieve Entity ID.
     * <p>
     * Notes: entity's ID
     *
     * @return The current Entity ID
     */
    public int getEntityID() {
        return handle.getIntegers().read(0);
    }

    /**
     * Set Entity ID.
     *
     * @param value - new value.
     */
    public void setEntityID(int value) {
        handle.getIntegers().write(0, value);
    }

    /**
     * Retrieve the entity of the painting that will be spawned.
     *
     * @param world - the current world of the entity.
     * @return The spawned entity.
     */
    public Entity getEntity(World world) {
        return handle.getEntityModifier((org.bukkit.World) world).read(0);
    }

    /**
     * Retrieve the entity of the painting that will be spawned.
     *
     * @param event - the packet event.
     * @return The spawned entity.
     */
    public Entity getEntity(PacketEvent event) {
        return getEntity((PacketEvent) event.getPlayer().getWorld());
    }

    /**
     * Retrieve Effect ID.
     * <p>
     * Notes: see [[1]]
     *
     * @return The current Effect ID
     */
    public byte getEffectID() {
        return handle.getBytes().read(0);
    }

    /**
     * Set Effect ID.
     *
     * @param value - new value.
     */
    public void setEffectID(byte value) {
        handle.getBytes().write(0, (byte) (value & 255));
    }

    /**
     * Retrieve Amplifier.
     *
     * @return The current Amplifier
     */
    public byte getAmplifier() {
        return handle.getBytes().read(1);
    }

    /**
     * Set Amplifier.
     *
     * @param value - new value.
     */
    public void setAmplifier(byte value) {
        handle.getBytes().write(1, (byte) (value & 255));
    }

    /**
     * Retrieve Duration.
     *
     * @return The current Duration
     */
    public int getDuration() {
        return handle.getIntegers().read(1);
    }

    /**
     * Set Duration.
     *
     * @param value - new value.
     */
    public void setDuration(int value) {
        handle.getIntegers().write(1, value);
    }

    /**
     * Retrieve Hide Particles.
     *
     * @return The current Hide Particles
     */
    public boolean getHideParticles() {
        return handle.getBytes().read(2) == 0;
    }

    /**
     * Set Hide Particles.
     *
     * @param value - new value.
     */
    public void setHideParticles(boolean value) {
        handle.getBytes().write(2, (byte) (value ? 0 : 1));
    }

}
