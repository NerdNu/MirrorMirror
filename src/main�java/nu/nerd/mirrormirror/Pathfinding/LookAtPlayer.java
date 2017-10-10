package nu.nerd.mirrormirror.Pathfinding;


import nu.nerd.mirrormirror.MirrorMirror;
import nu.nerd.mirrormirror.NMSHelper;
import org.bukkit.entity.LivingEntity;

import java.lang.reflect.Constructor;

public class LookAtPlayer extends AbstractPathfinderGoal {


    public LookAtPlayer(LivingEntity baseEntity) {
        super(baseEntity);
    }


    @SuppressWarnings("unchecked")
    public Object get() {
        try {
            Class insentient = NMSHelper.getNMSClassByName("EntityInsentient");
            Class c = NMSHelper.getNMSClassByName("PathfinderGoalLookAtPlayer");
            Constructor constructor = c.getConstructor(insentient, Class.class, float.class);
            return constructor.newInstance(insentient.cast(getHandle()), NMSHelper.getNMSClassByName("EntityHuman"), 8f);
        } catch (Exception ex) {
            MirrorMirror.logger().warning("Error constructing PathfinderGoalLookAtPlayer");
            ex.printStackTrace();
            return null;
        }
    }


}
