package nu.nerd.mirrormirror.Pathfinding;


import nu.nerd.mirrormirror.MirrorMirror;
import nu.nerd.mirrormirror.NMSHelper;
import org.bukkit.entity.LivingEntity;

import java.lang.reflect.Constructor;

/**
 * Entity will attempt to hide from the sun.
 */
public class FleeSun extends AbstractPathfinderGoal {


    private double speed;


    /**
     * Constructor
     * @param baseEntity the entity
     * @param speed the speed the entity will run at
     */
    public FleeSun(LivingEntity baseEntity, double speed) {
        super(baseEntity);
        this.speed = speed;
    }


    @SuppressWarnings("unchecked")
    public Object get() {
        try {
            Class creature = NMSHelper.getNMSClassByName("EntityCreature");
            Class c = NMSHelper.getNMSClassByName("PathfinderGoalFleeSun");
            Constructor constructor = c.getConstructor(creature, double.class);
            return constructor.newInstance(creature.cast(getHandle()), speed);
        } catch (Exception ex) {
            MirrorMirror.logger().warning("Error constructing PathfinderGoalFleeSun");
            ex.printStackTrace();
            return null;
        }
    }


}
