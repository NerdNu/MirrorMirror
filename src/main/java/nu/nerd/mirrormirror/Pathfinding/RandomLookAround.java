package nu.nerd.mirrormirror.Pathfinding;


import nu.nerd.mirrormirror.MirrorMirror;
import nu.nerd.mirrormirror.NMSHelper;
import org.bukkit.entity.LivingEntity;

import java.lang.reflect.Constructor;


/**
 * The entity will look around randomly when not targeting something.
 */
public class RandomLookAround extends AbstractPathfinderGoal {


    public RandomLookAround(LivingEntity baseEntity) {
        super(baseEntity);
    }


    @SuppressWarnings("unchecked")
    public Object get() {
        try {
            Class insentient = NMSHelper.getNMSClassByName("EntityInsentient");
            Class c = NMSHelper.getNMSClassByName("PathfinderGoalRandomLookaround");
            Constructor constructor = c.getConstructor(insentient);
            return constructor.newInstance(getHandle());
        } catch (Exception ex) {
            MirrorMirror.logger().warning("Error constructing PathfinderGoalRandomLookaround");
            ex.printStackTrace();
            return null;
        }
    }


}
