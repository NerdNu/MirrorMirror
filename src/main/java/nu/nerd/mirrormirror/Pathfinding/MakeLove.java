package nu.nerd.mirrormirror.Pathfinding;

import nu.nerd.mirrormirror.MirrorMirror;
import nu.nerd.mirrormirror.NMSHelper;
import org.bukkit.entity.LivingEntity;

import java.lang.reflect.Constructor;

/**
 * Villager sex
 */
public class MakeLove extends AbstractPathfinderGoal {


    public MakeLove(LivingEntity baseEntity) {
        super(baseEntity);
    }


    @SuppressWarnings("unchecked")
    public Object get() {
        try {
            Class villager = NMSHelper.getNMSClassByName("EntityVillager");
            Class c = NMSHelper.getNMSClassByName("PathfinderGoalMakeLove");
            Constructor constructor = c.getConstructor(villager);
            return constructor.newInstance(villager.cast(getHandle()));
        } catch (Exception ex) {
            MirrorMirror.logger().warning("Error constructing PathfinderGoalMakeLove");
            ex.printStackTrace();
            return null;
        }
    }


}
