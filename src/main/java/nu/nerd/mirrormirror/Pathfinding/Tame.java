package nu.nerd.mirrormirror.Pathfinding;

import nu.nerd.mirrormirror.MirrorMirror;
import nu.nerd.mirrormirror.NMSHelper;
import org.bukkit.entity.LivingEntity;

import java.lang.reflect.Constructor;


/**
 * Taming interactions
 */
public class Tame extends AbstractPathfinderGoal {


    private double d0;


    public Tame(LivingEntity baseEntity, double d0) {
        super(baseEntity);
        this.d0 = d0;
    }


    @SuppressWarnings("unchecked")
    public Object get() {
        try {
            Class horse = NMSHelper.getNMSClassByName("EntityHorseAbstract");
            Class c = NMSHelper.getNMSClassByName("PathfinderGoalTame");
            Constructor constructor = c.getConstructor(horse, double.class);
            return constructor.newInstance(horse.cast(getHandle()), d0);
        } catch (Exception ex) {
            MirrorMirror.logger().warning("Error constructing PathfinderGoalTame");
            ex.printStackTrace();
            return null;
        }
    }


}
