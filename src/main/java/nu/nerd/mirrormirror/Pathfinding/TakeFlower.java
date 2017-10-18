package nu.nerd.mirrormirror.Pathfinding;

import nu.nerd.mirrormirror.MirrorMirror;
import nu.nerd.mirrormirror.NMSHelper;
import org.bukkit.entity.LivingEntity;

import java.lang.reflect.Constructor;


/**
 * Villager will take a flower offered by an Iron Golem
 */
public class TakeFlower extends AbstractPathfinderGoal {


    public TakeFlower(LivingEntity baseEntity) {
        super(baseEntity);
    }


    @SuppressWarnings("unchecked")
    public Object get() {
        try {
            Class villager = NMSHelper.getNMSClassByName("EntityVillager");
            Class c = NMSHelper.getNMSClassByName("PathfinderGoalTakeFlower");
            Constructor constructor = c.getConstructor(villager);
            return constructor.newInstance(villager.cast(getHandle()));
        } catch (Exception ex) {
            MirrorMirror.logger().warning("Error constructing PathfinderGoalTakeFlower");
            ex.printStackTrace();
            return null;
        }
    }


}
