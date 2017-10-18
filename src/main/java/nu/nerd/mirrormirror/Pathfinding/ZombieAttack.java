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


    private double speed;
    private boolean longMemory;


    public ZombieAttack(LivingEntity baseEntity, double speed, boolean longMemory) {
        super(baseEntity);
        this.speed = speed;
        this.longMemory = longMemory;
    }


    @SuppressWarnings("unchecked")
    public Object get() {
        try {
            Class zombie = NMSHelper.getNMSClassByName("EntityZombie");
            Class c = NMSHelper.getNMSClassByName("PathfinderGoalZombieAttack");
            Constructor constructor = c.getConstructor(zombie, double.class, boolean.class);
            return constructor.newInstance(getHandle(), speed, longMemory);
        } catch (Exception ex) {
            MirrorMirror.logger().warning("Error constructing PathfinderGoalZombieAttack");
            ex.printStackTrace();
            return null;
        }
    }


}
