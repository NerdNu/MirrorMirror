package nu.nerd.mirrormirror.Pathfinding;


import nu.nerd.mirrormirror.MirrorMirror;
import nu.nerd.mirrormirror.NMSHelper;
import org.bukkit.entity.LivingEntity;

import java.lang.reflect.Constructor;

/**
 * The entity will navigate a village
 */
public class MoveThroughVillage extends AbstractPathfinderGoal {


    private double speed;
    private boolean isNocturnal;


    /**
     * Constructor
     * @param baseEntity the entity
     * @param speed movement speed
     * @param isNocturnal if true, the entity won't do anything in the day
     */
    public MoveThroughVillage(LivingEntity baseEntity, double speed, boolean isNocturnal) {
        super(baseEntity);
        this.speed = speed;
        this.isNocturnal = isNocturnal;
    }


    @SuppressWarnings("unchecked")
    public Object get() {
        try {
            Class creature = NMSHelper.getNMSClassByName("EntityCreature");
            Class c = NMSHelper.getNMSClassByName("PathfinderGoalMoveThroughVillage");
            Constructor constructor = c.getConstructor(creature, double.class, boolean.class);
            return constructor.newInstance(getHandle(), speed, isNocturnal);
        } catch (Exception ex) {
            MirrorMirror.logger().warning("Error constructing PathfinderGoalMoveThroughVillage");
            ex.printStackTrace();
            return null;
        }
    }


}
