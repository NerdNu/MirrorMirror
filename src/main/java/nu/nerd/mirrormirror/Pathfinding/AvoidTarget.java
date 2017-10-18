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
    private double farSpeed;
    private double nearSpeed;


    /**
     * Constructor
     * d0 and d1 seem to be conditional speeds for fleeing
     * @param baseEntity the entity
     * @param threshold aggro distance
     * @param farSpeed speed when the entity isn't close by
     * @param nearSpeed speed when the distance between the entitites is short
     */
    public AvoidTarget(LivingEntity baseEntity, String avoidClass, float threshold, double farSpeed, double nearSpeed) {
        super(baseEntity);
        this.avoidClass = avoidClass;
        this.threshold = threshold;
        this.farSpeed = farSpeed;
        this.nearSpeed = nearSpeed;
    }


    @SuppressWarnings("unchecked")
    public Object get() {
        try {
            Class creature = NMSHelper.getNMSClassByName("EntityCreature");
            Class avoid = NMSHelper.getNMSClassByName(avoidClass);
            Class c = NMSHelper.getNMSClassByName("PathfinderGoalAvoidTarget");
            Constructor constructor = c.getConstructor(creature, Class.class, float.class, double.class, double.class);
            return constructor.newInstance(creature.cast(getHandle()), avoid, threshold, farSpeed, nearSpeed);
        } catch (Exception ex) {
            MirrorMirror.logger().warning("Error constructing PathfinderGoalAvoidTarget");
            ex.printStackTrace();
            return null;
        }
    }


}
