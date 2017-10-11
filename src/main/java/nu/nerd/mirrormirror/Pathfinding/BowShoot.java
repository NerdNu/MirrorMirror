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


    private double d0;
    private int i;
    private float f;


    public BowShoot(LivingEntity baseEntity, double d0, int i, float f) {
        super(baseEntity);
        this.d0 = d0;
        this.i = i;
        this.f = f;
    }


    @SuppressWarnings("unchecked")
    public Object get() {
        try {
            Class skeleton = NMSHelper.getNMSClassByName("EntitySkeletonAbstract");
            Class c = NMSHelper.getNMSClassByName("PathfinderGoalBowShoot");
            Constructor constructor = c.getConstructors()[0];
            return constructor.newInstance(skeleton.cast(getHandle()), d0, i, f);
        } catch (Exception ex) {
            MirrorMirror.logger().warning("Error constructing PathfinderGoalBowShoot");
            ex.printStackTrace();
            return null;
        }
    }


}
