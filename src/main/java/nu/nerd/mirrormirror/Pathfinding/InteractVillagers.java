package nu.nerd.mirrormirror.Pathfinding;

import nu.nerd.mirrormirror.MirrorMirror;
import nu.nerd.mirrormirror.NMSHelper;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;

import java.lang.reflect.Constructor;


/**
 * Villagers will throw food at each other
 */
public class InteractVillagers extends AbstractPathfinderGoal {


    public InteractVillagers(LivingEntity baseEntity) {
        super(baseEntity);
    }


    @SuppressWarnings("unchecked")
    public Object get() {
        try {
            if (!baseEntity.getType().equals(EntityType.VILLAGER)) {
                return null;
            }
            Class villager = NMSHelper.getNMSClassByName("EntityVillager");
            Class c = NMSHelper.getNMSClassByName("PathfinderGoalInteractVillagers");
            Constructor constructor = c.getConstructor(villager);
            return constructor.newInstance(villager.cast(getHandle()));
        } catch (Exception ex) {
            MirrorMirror.logger().warning("Error constructing PathfinderGoalInteractVillagers");
            ex.printStackTrace();
            return null;
        }
    }


}
