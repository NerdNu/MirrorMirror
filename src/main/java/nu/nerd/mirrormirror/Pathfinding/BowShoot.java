package nu.nerd.mirrormirror.Pathfinding;


import nu.nerd.mirrormirror.MirrorMirror;
import nu.nerd.mirrormirror.NMSHelper;
import org.bukkit.entity.LivingEntity;

import java.lang.reflect.Constructor;


/**
 * This goal is used to cause skeletons to shoot at targets they've acquired
 * through NearestAttackableTarget.
 */
public class BowShoot extends AbstractPathfinderGoal {


    private double speedMultiplier;
    private int delay;
    private float maxDistance;


    /**
     * Constructor
     * @param baseEntity the entity
     * @param speedMultiplier increase movement speed while attacking
     * @param delay minimum delay between shots
     * @param maxDistance maximum distance to attack from
     */
    public BowShoot(LivingEntity baseEntity, double speedMultiplier, int delay, float maxDistance) {
        super(baseEntity);
        this.speedMultiplier = speedMultiplier;
        this.delay = delay;
        this.maxDistance = maxDistance;
    }


    @SuppressWarnings("unchecked")
    public Object get() {
        try {
            Class skeleton = NMSHelper.getNMSClassByName("EntitySkeletonAbstract");
            Class c = NMSHelper.getNMSClassByName("PathfinderGoalBowShoot");
            Constructor constructor = c.getConstructors()[0];
            return constructor.newInstance(skeleton.cast(getHandle()), speedMultiplier, delay, maxDistance);
        } catch (Exception ex) {
            MirrorMirror.logger().warning("Error constructing PathfinderGoalBowShoot");
            ex.printStackTrace();
            return null;
        }
    }


}
