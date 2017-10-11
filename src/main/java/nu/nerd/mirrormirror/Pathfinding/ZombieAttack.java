package nu.nerd.mirrormirror.Pathfinding;


import nu.nerd.mirrormirror.MirrorMirror;
import nu.nerd.mirrormirror.NMSHelper;
import org.bukkit.entity.LivingEntity;

import java.lang.reflect.Constructor;


/**
 * Zombies use this instead of MeleeAttack.
 * This goal will only accept an EntityZombie.
 */
public class ZombieAttack extends AbstractPathfinderGoal {


    private double d0;
    private boolean flag;


    public ZombieAttack(LivingEntity baseEntity, double d0, boolean flag) {
        super(baseEntity);
        this.d0 = d0;
        this.flag = flag;
    }


    @SuppressWarnings("unchecked")
    public Object get() {
        try {
            Class zombie = NMSHelper.getNMSClassByName("EntityZombie");
            Class c = NMSHelper.getNMSClassByName("PathfinderGoalZombieAttack");
            Constructor constructor = c.getConstructor(zombie, double.class, boolean.class);
            return constructor.newInstance(getHandle(), d0, flag);
        } catch (Exception ex) {
            MirrorMirror.logger().warning("Error constructing PathfinderGoalZombieAttack");
            ex.printStackTrace();
            return null;
        }
    }


}
