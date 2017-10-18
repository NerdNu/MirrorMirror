package nu.nerd.mirrormirror.Pathfinding;

import nu.nerd.mirrormirror.MirrorMirror;
import nu.nerd.mirrormirror.NMSHelper;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;

import java.lang.reflect.Constructor;

/**
 * The entity will walk toward its target, but won't explicitly attack
 */
public class MoveTowardsTarget extends AbstractPathfinderGoal {


    private double speed;
    private float maxDistance;


    public MoveTowardsTarget(LivingEntity baseEntity, double speed, float maxDistance) {
        super(baseEntity);
        this.speed = speed;
        this.maxDistance = maxDistance;
    }


    @SuppressWarnings("unchecked")
    public Object get() {
        try {
            Class creature = NMSHelper.getNMSClassByName("EntityCreature");
            Class c = NMSHelper.getNMSClassByName("PathfinderGoalMoveTowardsTarget");
            Constructor constructor = c.getConstructor(creature, double.class, float.class);
            return constructor.newInstance(creature.cast(getHandle()), speed, maxDistance);
        } catch (Exception ex) {
            MirrorMirror.logger().warning("Error constructing PathfinderGoalMoveTowardsTarget");
            ex.printStackTrace();
            return null;
        }
    }


}
