package nu.nerd.mirrormirror.Pathfinding;


import nu.nerd.mirrormirror.MirrorMirror;
import nu.nerd.mirrormirror.NMSHelper;
import org.bukkit.entity.LivingEntity;

import java.lang.reflect.Constructor;


/**
 * The entity will avoid entities of the specified class.
 * e.g. creepers running from cats
 */
public class AvoidTarget extends AbstractPathfinderGoal {


    private String avoidClass;
    private float threshold;
    private double d0;
    private double d1;


    /**
     * Constructor
     * d0 and d1 seem to be conditional speeds for fleeing
     * @param baseEntity the entity
     * @param threshold aggro distance
     * @param d0 conditional speed 11? (needs doc)
     * @param d1 conditional speed 2? (needs doc)
     */
    public AvoidTarget(LivingEntity baseEntity, String avoidClass, float threshold, double d0, double d1) {
        super(baseEntity);
        this.avoidClass = avoidClass;
        this.threshold = threshold;
        this.d0 = d0;
        this.d1 = d1;
    }


    @SuppressWarnings("unchecked")
    public Object get() {
        try {
            Class creature = NMSHelper.getNMSClassByName("EntityCreature");
            Class avoid = NMSHelper.getNMSClassByName(avoidClass);
            Class c = NMSHelper.getNMSClassByName("PathfinderGoalAvoidTarget");
            Constructor constructor = c.getConstructor(creature, Class.class, float.class, double.class, double.class);
            return constructor.newInstance(creature.cast(getHandle()), avoid, threshold, d0, d1);
        } catch (Exception ex) {
            MirrorMirror.logger().warning("Error constructing PathfinderGoalAvoidTarget");
            ex.printStackTrace();
            return null;
        }
    }


}
