package nu.nerd.mirrormirror.Pathfinding;


import nu.nerd.mirrormirror.MirrorMirror;
import nu.nerd.mirrormirror.NMSHelper;
import org.bukkit.entity.LivingEntity;

import java.lang.reflect.Constructor;


/**
 * Walk around randomly when lacking a target.
 */
public class RandomStrollLand extends AbstractPathfinderGoal {


    private double speed;


    public RandomStrollLand(LivingEntity baseEntity, double speed) {
        super(baseEntity);
        this.speed = speed;
    }


    @SuppressWarnings("unchecked")
    public Object get() {
        try {
            Class creature = NMSHelper.getNMSClassByName("EntityCreature");
            Class c = NMSHelper.getNMSClassByName("PathfinderGoalRandomStrollLand");
            Constructor constructor = c.getConstructor(creature, double.class);
            return constructor.newInstance(getHandle(), speed);
        } catch (Exception ex) {
            MirrorMirror.logger().warning("Error constructing PathfinderGoalRandomStrollLand");
            ex.printStackTrace();
            return null;
        }
    }


}
