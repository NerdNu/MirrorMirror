package nu.nerd.mirrormirror.Pathfinding;

import nu.nerd.mirrormirror.MirrorMirror;
import nu.nerd.mirrormirror.NMSHelper;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;

import java.lang.reflect.Constructor;

/**
 * Villager will look at the player trading with it
 */
public class LookAtTradingPlayer extends AbstractPathfinderGoal {


    public LookAtTradingPlayer(LivingEntity baseEntity) {
        super(baseEntity);
    }


    @SuppressWarnings("unchecked")
    public Object get() {
        try {
            if (!baseEntity.getType().equals(EntityType.VILLAGER)) {
                return null;
            }
            Class villager = NMSHelper.getNMSClassByName("EntityVillager");
            Class c = NMSHelper.getNMSClassByName("PathfinderGoalLookAtTradingPlayer");
            Constructor constructor = c.getConstructor(villager);
            return constructor.newInstance(villager.cast(getHandle()));
        } catch (Exception ex) {
            MirrorMirror.logger().warning("Error constructing PathfinderGoalLookAtTradingPlayer");
            ex.printStackTrace();
            return null;
        }
    }


}
