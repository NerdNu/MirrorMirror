package nu.nerd.mirrormirror.Pathfinding;


import nu.nerd.mirrormirror.MirrorMirror;
import nu.nerd.mirrormirror.NMSHelper;
import org.bukkit.entity.LivingEntity;

import java.lang.reflect.Constructor;


/**
 * Causes the entity to look at the nearest player
 */
public class LookAtPlayer extends AbstractPathfinderGoal {


    private String mobClass;


    public LookAtPlayer(LivingEntity baseEntity) {
        super(baseEntity);
        mobClass = "EntityHuman";
    }


    public LookAtPlayer(LivingEntity baseEntity, String mobClass) {
        super(baseEntity);
        this.mobClass = mobClass;
    }


    @SuppressWarnings("unchecked")
    public Object get() {
        try {
            Class insentient = NMSHelper.getNMSClassByName("EntityInsentient");
            Class c = NMSHelper.getNMSClassByName("PathfinderGoalLookAtPlayer");
            Constructor constructor = c.getConstructor(insentient, Class.class, float.class);
            return constructor.newInstance(insentient.cast(getHandle()), NMSHelper.getNMSClassByName(mobClass), 8f);
        } catch (Exception ex) {
            MirrorMirror.logger().warning("Error constructing PathfinderGoalLookAtPlayer");
            ex.printStackTrace();
            return null;
        }
    }


}
