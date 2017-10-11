package nu.nerd.mirrormirror.Pathfinding;


import nu.nerd.mirrormirror.MirrorMirror;
import nu.nerd.mirrormirror.NMSHelper;
import org.bukkit.entity.LivingEntity;

import java.lang.reflect.Constructor;


/**
 * Causes the entity to float in water.
 * Without this goal, they sink to the bottom and drown.
 */
public class FloatGoal extends AbstractPathfinderGoal {


    public FloatGoal(LivingEntity baseEntity) {
        super(baseEntity);
    }


    @SuppressWarnings("unchecked")
    public Object get() {
        try {
            Class insentient = NMSHelper.getNMSClassByName("EntityInsentient");
            Class c = NMSHelper.getNMSClassByName("PathfinderGoalFloat");
            Constructor constructor = c.getConstructor(insentient);
            return constructor.newInstance(getHandle());
        } catch (Exception ex) {
            MirrorMirror.logger().warning("Error constructing PathfinderGoalFloat");
            ex.printStackTrace();
            return null;
        }
    }


}
