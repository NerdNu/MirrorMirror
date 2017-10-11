package nu.nerd.mirrormirror.Pathfinding;


import nu.nerd.mirrormirror.MirrorMirror;
import nu.nerd.mirrormirror.NMSHelper;
import org.bukkit.entity.LivingEntity;

import java.lang.reflect.Constructor;


/**
 * Causes the entity to use its melee attack on its applicable targets
 */
public class MeleeAttack extends AbstractPathfinderGoal {


    private double d0;
    private boolean flag;


    public MeleeAttack(LivingEntity baseEntity, double d0, boolean flag) {
        super(baseEntity);
        this.d0 = d0;
        this.flag = flag;
    }


    @SuppressWarnings("unchecked")
    public Object get() {
        try {
            Class creature = NMSHelper.getNMSClassByName("EntityCreature");
            Class c = NMSHelper.getNMSClassByName("PathfinderGoalMeleeAttack");
            Constructor constructor = c.getConstructor(creature, double.class, boolean.class);
            return constructor.newInstance(getHandle(), d0, flag);
        } catch (Exception ex) {
            MirrorMirror.logger().warning("Error constructing PathfinderGoalMeleeAttack");
            ex.printStackTrace();
            return null;
        }
    }


}
