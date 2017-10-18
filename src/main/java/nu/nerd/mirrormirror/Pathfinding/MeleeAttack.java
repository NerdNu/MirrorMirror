package nu.nerd.mirrormirror.Pathfinding;


import nu.nerd.mirrormirror.MirrorMirror;
import nu.nerd.mirrormirror.NMSHelper;
import org.bukkit.entity.LivingEntity;

import java.lang.reflect.Constructor;


/**
 * Causes the entity to use its melee attack on its applicable targets
 */
public class MeleeAttack extends AbstractPathfinderGoal {


    private double speed;
    private boolean longMemory;


    /**
     * Constructor
     * @param baseEntity the entity
     * @param speed the speed the mob will approach its target
     * @param longMemory remain hostile longer, even when a clear path is unavailable
     */
    public MeleeAttack(LivingEntity baseEntity, double speed, boolean longMemory) {
        super(baseEntity);
        this.speed = speed;
        this.longMemory = longMemory;
    }


    @SuppressWarnings("unchecked")
    public Object get() {
        try {
            Class creature = NMSHelper.getNMSClassByName("EntityCreature");
            Class c = NMSHelper.getNMSClassByName("PathfinderGoalMeleeAttack");
            Constructor constructor = c.getConstructor(creature, double.class, boolean.class);
            return constructor.newInstance(getHandle(), speed, longMemory);
        } catch (Exception ex) {
            MirrorMirror.logger().warning("Error constructing PathfinderGoalMeleeAttack");
            ex.printStackTrace();
            return null;
        }
    }


}
