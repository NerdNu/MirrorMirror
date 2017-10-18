package nu.nerd.mirrormirror.Pathfinding;

import nu.nerd.mirrormirror.MirrorMirror;
import nu.nerd.mirrormirror.NMSHelper;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;

import java.lang.reflect.Constructor;


/**
 * Ocelot jumps onto chests and furnaces
 */
public class JumpOnBlock extends AbstractPathfinderGoal {


    private double speed;


    public JumpOnBlock(LivingEntity baseEntity, double speed) {
        super(baseEntity);
        this.speed = speed;
    }


    @SuppressWarnings("unchecked")
    public Object get() {
        try {
            if (!baseEntity.getType().equals(EntityType.OCELOT)) {
                return null;
            }
            Class ocelot = NMSHelper.getNMSClassByName("EntityOcelot");
            Class c = NMSHelper.getNMSClassByName("PathfinderGoalJumpOnBlock");
            Constructor constructor = c.getConstructor(ocelot, double.class);
            return constructor.newInstance(ocelot.cast(getHandle()), speed);
        } catch (Exception ex) {
            MirrorMirror.logger().warning("Error constructing PathfinderGoalJumpOnBlock");
            ex.printStackTrace();
            return null;
        }
    }


}
