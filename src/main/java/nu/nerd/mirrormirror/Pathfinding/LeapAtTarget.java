package nu.nerd.mirrormirror.Pathfinding;

import nu.nerd.mirrormirror.MirrorMirror;
import nu.nerd.mirrormirror.NMSHelper;
import org.bukkit.entity.LivingEntity;

import java.lang.reflect.Constructor;

/**
 * Entity will leap at targets
 */
public class LeapAtTarget extends AbstractPathfinderGoal {


    private float movementY;


    public LeapAtTarget(LivingEntity baseEntity, float movementY) {
        super(baseEntity);
        this.movementY = movementY;
    }


    @SuppressWarnings("unchecked")
    public Object get() {
        try {
            Class insentient = NMSHelper.getNMSClassByName("EntityInsentient");
            Class c = NMSHelper.getNMSClassByName("PathfinderGoalLeapAtTarget");
            Constructor constructor = c.getConstructor(insentient, float.class);
            return constructor.newInstance(insentient.cast(getHandle()), movementY);
        } catch (Exception ex) {
            MirrorMirror.logger().warning("Error constructing PathfinderGoalLeapAtTarget");
            ex.printStackTrace();
            return null;
        }
    }


}
