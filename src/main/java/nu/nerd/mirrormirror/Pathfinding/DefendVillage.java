package nu.nerd.mirrormirror.Pathfinding;

import nu.nerd.mirrormirror.MirrorMirror;
import nu.nerd.mirrormirror.NMSHelper;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;

import java.lang.reflect.Constructor;

/**
 * An iron golem will defend a village from hostile mobs
 */
public class DefendVillage extends AbstractPathfinderGoal {


    public DefendVillage(LivingEntity baseEntity) {
        super(baseEntity);
    }


    @SuppressWarnings("unchecked")
    public Object get() {
        try {
            if (!baseEntity.getType().equals(EntityType.IRON_GOLEM)) {
                return null;
            }
            Class golem = NMSHelper.getNMSClassByName("EntityIronGolem");
            Class c = NMSHelper.getNMSClassByName("PathfinderGoalDefendVillage");
            Constructor constructor = c.getConstructor(golem);
            return constructor.newInstance(golem.cast(getHandle()));
        } catch (Exception ex) {
            MirrorMirror.logger().warning("Error constructing PathfinderGoalDefendVillage");
            ex.printStackTrace();
            return null;
        }
    }


}
