package cc.tommyw.tac.utils.pockets;

import com.comphenix.protocol.PacketType;
import com.comphenix.protocol.events.PacketContainer;
import com.comphenix.protocol.events.PacketEvent;
import com.comphenix.protocol.wrappers.EnumWrappers;
import net.minecraft.server.v1_8_R3.World;
import org.bukkit.entity.Entity;
import org.bukkit.util.Vector;

public class WrapperPlayClientUseEntity extends AbstractPacket {
    public static final PacketType TYPE = PacketType.Play.Client.USE_ENTITY;

    public WrapperPlayClientUseEntity() {
        super(new PacketContainer(TYPE), TYPE);
        handle.getModifier().writeDefaults();
    }

    public WrapperPlayClientUseEntity(PacketContainer packet) {
        super(packet, TYPE);
    }

    /**
     * Retrieve entity ID of the target.
     *
     * @return The current entity ID
     */
    public int getTargetID() {
        return handle.getIntegers().read(0);
    }

    /**
     * Retrieve the entity that was targeted.
     *
     * @param world - the current world of the entity.
     * @return The targeted entity.
     */
    public Entity getTarget(World world) {
        return handle.getEntityModifier((org.bukkit.World) world).read(0);
    }

    /**
     * Retrieve the entity that was targeted.
     *
     * @param event - the packet event.
     * @return The targeted entity.
     */
    public Entity getTarget(PacketEvent event) {
        return getTarget((PacketEvent) event.getPlayer().getWorld());
    }

    /**
     * Set entity ID of the target.
     *
     * @param value - new value.
     */
    public void setTargetID(int value) {
        handle.getIntegers().write(0, value);
    }

    /**
     * Retrieve Type.
     *
     * @return The current Type
     */
    public EnumWrappers.EntityUseAction getType() {
        return handle.getEntityUseActions().read(0);
    }

    /**
     * Set Type.
     *
     * @param value - new value.
     */
    public void setType(EnumWrappers.EntityUseAction value) {
        handle.getEntityUseActions().write(0, value);
    }

    public Vector getTargetVector() {
        return handle.getVectors().read(0);
    }

    /**
     * Set the target vector.
     *
     * @param value - new value.
     */
    public void setTargetVector(Vector value) {
        handle.getVectors().write(0, value);
    }
}