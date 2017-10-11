package nu.nerd.mirrormirror.Pathfinding;

import nu.nerd.mirrormirror.MirrorMirror;
import nu.nerd.mirrormirror.NMSHelper;
import org.bukkit.entity.LivingEntity;

import java.lang.reflect.Constructor;


/**
 * The entity will target entities of the specified NMS class.
 * When a target is acquired, the MeleeAttack or BowShoot goals will attack the target.
 */
public class NearestAttackableTarget extends AbstractPathfinderGoal {


    private String target;


    /**
     *
     * @param baseEntity The entity this AI will apply to
     * @param target The short class name of the NMS entity this will target. e.g. "EntityHuman" or "EntityZombie"
     */
    public NearestAttackableTarget(LivingEntity baseEntity, String target) {
        super(baseEntity);
        this.target = target;
    }


    @SuppressWarnings("unchecked")
    public Object get() {
        try {
            Class creature = NMSHelper.getNMSClassByName("EntityCreature");
            Class c = NMSHelper.getNMSClassByName("PathfinderGoalNearestAttackableTarget");
            Constructor constructor = c.getConstructor(creature, Class.class, boolean.class);
            return constructor.newInstance(creature.cast(getHandle()), NMSHelper.getNMSClassByName(target), true);
        } catch (Exception ex) {
            MirrorMirror.logger().warning("Error constructing PathfinderGoalNearestAttackableTarget");
            ex.printStackTrace();
            return null;
        }
    }


}
