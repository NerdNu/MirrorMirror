package nu.nerd.mirrormirror.Pathfinding;

import nu.nerd.mirrormirror.MirrorMirror;
import nu.nerd.mirrormirror.NMSHelper;
import org.bukkit.entity.LivingEntity;
import com.google.common.base.Predicate;

import java.lang.reflect.Constructor;


public class RandomTargetNonTamed extends AbstractPathfinderGoal {


    private String target;
    private boolean checkSight;


    public RandomTargetNonTamed(LivingEntity baseEntity, String target, boolean checkSight) {
        super(baseEntity);
        this.target = target;
        this.checkSight = checkSight;
    }


    @SuppressWarnings("unchecked")
    public Object get() {
        try {
            Class tame = NMSHelper.getNMSClassByName("EntityTameableAnimal");
            Class c = NMSHelper.getNMSClassByName("PathfinderGoalRandomTargetNonTamed");
            Constructor constructor = c.getConstructor(tame, Class.class, boolean.class, Predicate.class);
            return constructor.newInstance(tame.cast(getHandle()), NMSHelper.getNMSClassByName(target), checkSight, null);
        } catch (Exception ex) {
            MirrorMirror.logger().warning("Error constructing PathfinderGoalRandomTargetNonTamed");
            ex.printStackTrace();
            return null;
        }
    }


}
