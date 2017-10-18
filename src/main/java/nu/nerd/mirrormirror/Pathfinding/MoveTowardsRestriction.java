package nu.nerd.mirrormirror.Pathfinding;


import nu.nerd.mirrormirror.MirrorMirror;
import nu.nerd.mirrormirror.NMSHelper;
import org.bukkit.entity.LivingEntity;

import java.lang.reflect.Constructor;


/**
 * Causes an entity to move back to its home position.
 */
public class MoveTowardsRestriction extends AbstractPathfinderGoal {


    private double speed;


    /**
     * Constructor
     * @param baseEntity entity
     * @param speed movement speed
     */
    public MoveTowardsRestriction(LivingEntity baseEntity, double speed) {
        super(baseEntity);
        this.speed = speed;
    }


    @SuppressWarnings("unchecked")
    public Object get() {
        try {
            Class creature = NMSHelper.getNMSClassByName("EntityCreature");
            Class c = NMSHelper.getNMSClassByName("PathfinderGoalMoveTowardsRestriction");
            Constructor constructor = c.getConstructor(creature, double.class);
            return constructor.newInstance(getHandle(), speed);
        } catch (Exception ex) {
            MirrorMirror.logger().warning("Error constructing PathfinderGoalMoveTowardsRestriction");
            ex.printStackTrace();
            return null;
        }
    }


}
