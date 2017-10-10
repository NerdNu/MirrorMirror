package nu.nerd.mirrormirror.Pathfinding;

import nu.nerd.mirrormirror.MirrorMirror;
import nu.nerd.mirrormirror.NMSHelper;
import org.bukkit.entity.LivingEntity;

import java.lang.reflect.Constructor;


public class TargetNearestPlayer extends AbstractPathfinderGoal {


    public TargetNearestPlayer(LivingEntity baseEntity) {
        super(baseEntity);
    }


    @SuppressWarnings("unchecked")
    public Object get() {
        try {
            Class insentient = NMSHelper.getNMSClassByName("EntityInsentient");
            Class c = NMSHelper.getNMSClassByName("PathfinderGoalTargetNearestPlayer");
            Constructor constructor = c.getConstructor(insentient);
            return constructor.newInstance(insentient.cast(getHandle()));
        } catch (Exception ex) {
            MirrorMirror.logger().warning("Error constructing PathfinderGoalTargetNearestPlayer");
            ex.printStackTrace();
            return null;
        }
    }


}
