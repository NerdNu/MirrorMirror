package nu.nerd.mirrormirror.Pathfinding;


import nu.nerd.mirrormirror.MirrorMirror;
import nu.nerd.mirrormirror.NMSHelper;
import org.bukkit.entity.LivingEntity;

import java.lang.reflect.Constructor;

/**
 * Creeper swell goal
 */
public class Swell extends AbstractPathfinderGoal {


    public Swell(LivingEntity baseEntity) {
        super(baseEntity);
    }


    @SuppressWarnings("unchecked")
    public Object get() {
        try {
            Class creeper = NMSHelper.getNMSClassByName("EntityCreeper");
            Class c = NMSHelper.getNMSClassByName("PathfinderGoalSwell");
            Constructor constructor = c.getConstructor(creeper);
            return constructor.newInstance(creeper.cast(getHandle()));
        } catch (Exception ex) {
            MirrorMirror.logger().warning("Error constructing PathfinderGoalSwell");
            ex.printStackTrace();
            return null;
        }
    }


}
