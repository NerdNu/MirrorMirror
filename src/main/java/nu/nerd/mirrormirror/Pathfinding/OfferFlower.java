package nu.nerd.mirrormirror.Pathfinding;

import nu.nerd.mirrormirror.MirrorMirror;
import nu.nerd.mirrormirror.NMSHelper;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;

import java.lang.reflect.Constructor;


/**
 * An Iron Golem can offer a flower to villagers
 */
public class OfferFlower extends AbstractPathfinderGoal {


    public OfferFlower(LivingEntity baseEntity) {
        super(baseEntity);
    }


    @SuppressWarnings("unchecked")
    public Object get() {
        try {
            if (!baseEntity.getType().equals(EntityType.IRON_GOLEM)) {
                return null;
            }
            Class golem = NMSHelper.getNMSClassByName("EntityIronGolem");
            Class c = NMSHelper.getNMSClassByName("PathfinderGoalOfferFlower");
            Constructor constructor = c.getConstructor(golem);
            return constructor.newInstance(golem.cast(getHandle()));
        } catch (Exception ex) {
            MirrorMirror.logger().warning("Error constructing PathfinderGoalOfferFlower");
            ex.printStackTrace();
            return null;
        }
    }


}
