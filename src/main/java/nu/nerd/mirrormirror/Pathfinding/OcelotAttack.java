package nu.nerd.mirrormirror.Pathfinding;

import nu.nerd.mirrormirror.MirrorMirror;
import nu.nerd.mirrormirror.NMSHelper;
import org.bukkit.entity.LivingEntity;

import java.lang.reflect.Constructor;


/**
 * Attack used by ocelots
 */
public class OcelotAttack extends AbstractPathfinderGoal {


    public OcelotAttack(LivingEntity baseEntity) {
        super(baseEntity);
    }


    @SuppressWarnings("unchecked")
    public Object get() {
        try {
            Class insentient = NMSHelper.getNMSClassByName("EntityInsentient");
            Class c = NMSHelper.getNMSClassByName("PathfinderGoalOcelotAttack");
            Constructor constructor = c.getConstructor(insentient);
            return constructor.newInstance(insentient.cast(getHandle()));
        } catch (Exception ex) {
            MirrorMirror.logger().warning("Error constructing PathfinderGoalOcelotAttack");
            ex.printStackTrace();
            return null;
        }
    }


}
