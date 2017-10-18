package nu.nerd.mirrormirror.Pathfinding;

import nu.nerd.mirrormirror.MirrorMirror;
import nu.nerd.mirrormirror.NMSHelper;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Tameable;

import java.lang.reflect.Constructor;


/**
 * Tameable entities follow their owner
 */
public class FollowOwner extends AbstractPathfinderGoal {


    private double speed;
    private float minDist;
    private float maxDist;


    public FollowOwner(LivingEntity baseEntity, double speed, float minDist, float maxDist) {
        super(baseEntity);
        this.speed = speed;
        this.minDist = minDist;
        this.maxDist = maxDist;
    }


    @SuppressWarnings("unchecked")
    public Object get() {
        try {
            if (!(baseEntity instanceof Tameable)) {
                return null;
            }
            Class tame = NMSHelper.getNMSClassByName("EntityTameableAnimal");
            Class c = NMSHelper.getNMSClassByName("PathfinderGoalFollowOwner");
            Constructor constructor = c.getConstructor(tame, double.class, float.class, float.class);
            return constructor.newInstance(tame.cast(getHandle()), speed, minDist, maxDist);
        } catch (Exception ex) {
            MirrorMirror.logger().warning("Error constructing PathfinderGoalFollowOwner");
            ex.printStackTrace();
            return null;
        }
    }


}
