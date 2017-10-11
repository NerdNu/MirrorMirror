package nu.nerd.mirrormirror.Pathfinding;

import nu.nerd.mirrormirror.MirrorMirror;
import nu.nerd.mirrormirror.NMSHelper;
import org.bukkit.entity.LivingEntity;

import java.lang.reflect.Constructor;


public class RestrictSun extends AbstractPathfinderGoal {


    public RestrictSun(LivingEntity baseEntity) {
        super(baseEntity);
    }


    @SuppressWarnings("unchecked")
    public Object get() {
        try {
            Class creature = NMSHelper.getNMSClassByName("EntityCreature");
            Class c = NMSHelper.getNMSClassByName("PathfinderGoalRestrictSun");
            Constructor constructor = c.getConstructor(creature);
            return constructor.newInstance(creature.cast(getHandle()));
        } catch (Exception ex) {
            MirrorMirror.logger().warning("Error constructing PathfinderGoalRestrictSun");
            ex.printStackTrace();
            return null;
        }
    }


}
